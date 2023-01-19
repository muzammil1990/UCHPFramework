/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';
  import ITranslateService = angular.translate.ITranslateService;
  class ListClaimJobsAppealStatusController {
    private claimSearchResults: Array < StandardClaimSearchResultModel >;
    private selectedRows: Array < StandardClaimSearchResultModel >;
    private gridOptions: any;
    private gridApi: any;
    private tabClaimSearchResultsSubscription: any;
    private appealStatusTabEnterSubscription: any;
    private openSelectedClaimJobsSubscription: any;
    private activeTab: Number;
    private tabjobtype: Number;
    private listofjobsDocsDownload: any;
    private selectedCompany: String;
    private selectCompanies: Array < any >;
    private companyChangeSubscription: any;
    private typeOfConcernCellTemplateHelper: any;
    private typeOfConcernCellTemplate: any;
    private typeOfConcern: Number;
    private importerCellTemplate: any;
    private importerCellTemplateHelper: any;
    private repairingDealerCellTemplateHelper: any;
    private repairingDealerCellTemplate: any;
    private claimStatusIconBG: any;
    private claimStatusIconUpperStack: any;
    private claimStatusIconLowerStack: any;
    private typeOfClaimTemplate: any;
    private sort: String = null;
    private descendingSortOrder: Boolean = null;
    private paginationOptions: any;
    private showTable: Boolean = false;
    private dealerLabel: string;
    private importerLabel: string;
    private loading: Boolean = true;
    private search: any;
    private user: User;
    private allSelected: boolean = false;

    /**
     *
     *
     * @static
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    static $inject = [
      'SelectCompaniesStore',
      'TabClaimSearchResultStore',
      'MiDocumentStore',
      'PrintDeliveryNoteDocumentStore',
      '$log',
      'DispatcherService',
      '$scope',
      '$state',
      'UserStore',
      'NotificationService',
      'localStorageService',
      '$translate',
      'uiGridConstants'
    ];

    /**
     * Creates an instance of ListClaimJobsAppealStatusController.
     *
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {ITabClaimSearchResultStore} TabClaimSearchResultStore
     * @param {IMiDocumentStore} MiDocumentStore
     * @param {IPrintDeliveryNoteDocumentStore} PrintDeliveryNoteDocumentStore
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} DispatcherService
     * @param {ng.IScope} $scope
     * @param {*} $state
     * @param {IUserStore} UserStore
     * @param {INotificationService} NotificationService
     * @param {any} localStorageService
     * @param {ITranslateService} $translate
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    constructor(private SelectCompaniesStore: ISelectCompaniesStore,
                private TabClaimSearchResultStore: ITabClaimSearchResultStore,
                private MiDocumentStore: IMiDocumentStore,
                private PrintDeliveryNoteDocumentStore: IPrintDeliveryNoteDocumentStore,
                private $log: ng.ILogService,
                private DispatcherService: IDispatcherService,
                private $scope: ng.IScope,
                private $state: any,
                private UserStore: IUserStore,
                private NotificationService: INotificationService,
                private localStorageService,
                private $translate: ITranslateService
    ) {
      var that = this;
      that.listofjobsDocsDownload = [];
      that.selectedRows = [];
      that.tabjobtype = 8;
      if (!that.search) {
        that
          .UserStore
          .getUser()
          .then(function (userFromStore) {
            if (userFromStore !== null && userFromStore !== undefined) {
              that.user = userFromStore;
              if (that.user.typeOfConcernList.length > 1) {
                that.typeOfConcern = -1;
              } else {
                that.typeOfConcern = that.user.typeOfConcernList[0];
              }
            } else {
              // TODO: is this correct?
              that.typeOfConcern = 1;
            }
            that.TabClaimSearchResultStore.getTabClaimSearchResult(that.tabjobtype, that.typeOfConcern, {});
          });
      }
      // TODO: refactor as these are subscribed twice with search tab content!
      that.tabClaimSearchResultsSubscription = that.DispatcherService
        .subscribe('listclaimjobs:getTabClaimSearchResult' + that.tabjobtype, that.tabClaimSearchResultHandler.bind(this));
      that.appealStatusTabEnterSubscription = that.DispatcherService
        .subscribe('listclaimjobs:TabEnterAppealStatus', that.tabEnterHandler.bind(this));
      that.openSelectedClaimJobsSubscription = that.DispatcherService
        .subscribe('listclaimjobs:OpenSelectedClaimJobsAppealStatus', that.openSelectedClaimJobsHandler.bind(this));

      that.SelectCompaniesStore.getSelectCompanies().then(function (selectCompanies: Array < {} >) {
        if (selectCompanies !== null && selectCompanies !== undefined) {
          that.selectCompanies = selectCompanies;
        }
      });
      /**
       * Get initial company (used in translation keys)
       *
       * */
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      that.dealerLabel = that.$translate.instant('LABEL.DEALER.' + that.selectedCompany);
      that.importerLabel = that.$translate.instant('LABEL.IMPORTER.' + that.selectedCompany);
      /**
       * Listen to company change
       * */
      that.companyChangeSubscription = that.DispatcherService
        .subscribe('change:CompanyChanged', that.CompanyChangeHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.companyChangeSubscription.unsubscribe();
        that.tabClaimSearchResultsSubscription.unsubscribe();
        that.appealStatusTabEnterSubscription.unsubscribe();
        that.openSelectedClaimJobsSubscription.unsubscribe();
      });
      that.typeOfConcernCellTemplateHelper = `{{ 'TYPEOFCONCERN_.' + row.entity.typeOfConcern | translate }}`;
      that.typeOfConcernCellTemplate = `<div uib-tooltip="${that.typeOfConcernCellTemplateHelper}" tooltip-placement="right" 
        class="text-left ui-grid-cell-contents">{{ 'TYPEOFCONCERN_.' + row.entity.typeOfConcern | translate }}</div>`;
      that.importerCellTemplateHelper = `{{ row.entity.repairingImporter }} - {{ row.entity.repairingImporterName }}`;
      that.importerCellTemplate = `<div uib-tooltip="${that.importerCellTemplateHelper}" tooltip-placement="right" 
        class="text-left ui-grid-cell-contents">{{ row.entity.repairingImporter }} 
        <span ng-if="row.entity.repairingImporterName.length > 0"> - </span>
        {{ row.entity.repairingImporterName }}</div>`;
      that.repairingDealerCellTemplateHelper = `{{ row.entity.repairingDealer }} - {{ row.entity.repairingDealerName }}`;
      that.repairingDealerCellTemplate = `<div uib-tooltip="${that.repairingDealerCellTemplateHelper}" tooltip-placement="right" 
          class="text-left ui-grid-cell-contents"> {{ row.entity.repairingDealer }}
          <span ng-if="row.entity.repairingDealerName.length > 0"> - </span>
           {{ row.entity.repairingDealerName }}</div>`;
      that.claimStatusIconBG = `<i class=\'{{"claimJobStateIcon." + row.entity.claimJobStateId | translate }} \'></i>`;
      that.claimStatusIconUpperStack = `<i class=\'{{ "claimJobStateIcon." + row.entity.claimJobStateId + "_1" | translate}}\'></i>`;
      that.claimStatusIconLowerStack = `<i class=\'{{ "claimJobStateIcon." + row.entity.claimJobStateId + "_2" | translate }} \'></i>`;
      that.typeOfClaimTemplate = `<div uib-tooltip=\'{{ "typeOfClaim." + row.entity.typeOfClaim | translate }}\' tooltip-placement="right" 
          class="text-left ui-grid-cell-contents">{{ "typeOfClaim." + row.entity.typeOfClaim | translate }}</div>`;
      that.claimSearchResults = [];
      that.paginationOptions = {
        pageNumber: 1,
        pageSize: that.localStorageService.get('pageSize') || 10,
        sort: that.sort
      };
      that.gridOptions = {
        columnDefs: [
          {
            cellTemplate: `<div class="cellAlignCenter" title="{{ 'claimJobStateId.' + row.entity.claimJobStateId | translate }}">
            <span class="fa-stack fa-2x">${that.claimStatusIconUpperStack}${that.claimStatusIconBG}${that.claimStatusIconLowerStack}
            </span></div>`,
            displayName: '',
            enableFiltering: false,
            field: 'claimJobStateId',
            width: 40
          }, {
            cellTemplate: that.typeOfConcernCellTemplate,
            displayName: 'Type of concern',
            enableFiltering: false,
            name: 'typeOfConcern',
            field: 'typeOfConcern',
            type: 'boolean'
          }, {
            cellTemplate: that.importerCellTemplate,
            displayName: that.importerLabel,
            enableFiltering: false,
            headerTooltip: true,
            name: 'Importer',
            field: 'repairingImporter',
            type: 'boolean'
          }, {
            cellTemplate: that.repairingDealerCellTemplate,
            displayName: that.dealerLabel,
            enableFiltering: false,
            field: 'repairingDealer',
            headerTooltip: true,
            type: 'boolean'
          }, {
            cellTemplate: '<div class="text-left ui-grid-cell-contents">' +
            '<a href ng-click="grid.appScope.ctrl.openSelectedClaimJobs(row)">' +
            '{{ row.entity.refNo }} - {{ row.entity.jobNo }}</a></div>',
            displayName: 'Ref. No. - Job. No.',
            enableFiltering: false,
            field: 'refNo,jobNo',
            name: 'RefJob',
            type: 'boolean'
          }, {
            cellTemplate: that.typeOfClaimTemplate,
            displayName: 'Type',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'typeOfClaim',
            headerTooltip: true
          }, {
            cellFilter: 'date:"dd/MM/yyyy"',
            displayName: 'Registration date',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'registrationDate',
            headerTooltip: true
          }, {
            cellTemplate: '<div class="text-left ui-grid-cell-contents">' +
            '<span class=\'{{ "claimJobStateTextColor." + row.entity.claimJobStateId | translate }} \'>' +
            '{{ "claimJobStateId." + row.entity.claimJobStateId | translate }}</span' +
            '></div>',
            displayName: 'Status',
            enableFiltering: false,
            field: 'claimJobStateId',
            headerTooltip: true,
            type: 'boolean'
          }, {
            cellTemplate: '<div class="text-left ui-grid-cell-contents">{{row.entity.chassisSeries}}' +
            '<span ng-if="row.entity.chassisSeries.length > 0"> - {{row.entity.chassisNo}}</span></div>',
            displayName: 'Chassis Id',
            enableFiltering: false,
            headerTooltip: true,
            field: 'chassisSeries,chassisNo',
            name: 'chassis',
            type: 'boolean'
          }, {
            cellTemplate: '<div class="text-left ui-grid-cell-contents">' +
            '{{ "appealStateID." + row.entity.appealStatus | translate }}</div>',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'appealStatus',
            headerTooltip: true
          }, {
            cellTooltip: false,
            displayName: 'Marketing Type',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'marketType',
            headerTooltip: true
          }, {
            cellTemplate: '<div class="text-right ui-grid-cell-contents">{{ row.entity.grandTotal | number: 2 }}</div>',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: false,
            field: 'grandTotal',
            headerTooltip: true
          }
        ],
        enableColumnMenus: false,
        enableColumnResize: true,
        enableFiltering: true,
        enableFullRowSelection: false,
        enablePaging: true,
        enableRowSelection: true,
        enableSelectAll: true,
        headerRowHeight: 50,
        multiSelect: true,
        paginationPageSize: that.localStorageService.get('pageSize') || 10,
        paginationPageSizes: [
          10, 25, 50
        ],
        rowEditWaitInterval: -1,
        rowHeight: 30,
        selectionRowHeaderWidth: 25,
        showFooter: true,
        useExternalPagination: true,
        useExternalSorting: true
      };

      /**
       *
       *
       * @param {any} gridApi
       */
      that.gridOptions.onRegisterApi = function (gridApi) {
        that.gridApi = gridApi;
        that.gridApi.selection.on.rowSelectionChangedBatch(null, function (rows) {
          // if allSelected=true we should not add individual rows
          if (!that.allSelected) {
            rows.forEach(function (row) {
              that.setSelectedClaimJobs(row);
            });
          }
          that.setButtons();
        });
        that.gridApi.selection.on.rowSelectionChanged(null, function (row) {
          if (!that.allSelected) {
            that.setSelectedClaimJobs(row);
          }
          that.setButtons();
        });
        that.gridApi.core.on.sortChanged(that.$scope, function (grid, sortColumns) {
          that.loading = true;
          if (sortColumns.length === 0) {
            that.sort = null;
          } else {
            var sortColumn = sortColumns[0].field;
            that.toggleSort();
          }
          that.TabClaimSearchResultStore.getTabClaimSearchResult(that.tabjobtype, that.typeOfConcern, {
            'pageNumber': that.paginationOptions.pageNumber,
            'pageSize': that.paginationOptions.pageSize,
            'sort': that.descendingSortOrder,
            'sortColumn': sortColumn
          });
        });
        that.gridApi.pagination.on.paginationChanged(that.$scope, function (newPage, pageSize) {
          that.loading = true;
          that.paginationOptions.pageNumber = newPage;
          that.paginationOptions.pageSize = pageSize;
          that.localStorageService.set('pageSize', pageSize);
          that.TabClaimSearchResultStore.getTabClaimSearchResult(that.tabjobtype, that.typeOfConcern, {
            'pageNumber': that.paginationOptions.pageNumber,
            'pageSize': that.paginationOptions.pageSize
          });
        });
      };
    }

    /**
     *
     *
     * @param {any} checked
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    selectAllrows(checked) {
      // TODO: toggle select all rows property for adding to request,
      // when api added by backend
      if (checked) {
        this.gridApi.selection.selectAllRows();
        this.allSelected = true;
      } else {
        this.gridApi.selection.clearSelectedRows();
        this.allSelected = false;
      }
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    selectPreviouslySelectedRows() {
      var that = this;
      let indexes = [];
      if (that.gridApi && that.gridApi.grid && that.gridApi.grid.rows) {
        _.each(that.claimSearchResults, function(row: any, i){
          _.each(that.listofjobsDocsDownload, function(job: any) {
            if (job === row.claimJobId) {
              indexes.push({
                i: i,
                id: job
              });
            }
          });
        });
        // we need to store a local array here, because the selectionchanged event
        // is triggering a reset of that.selectedClaimJobs
        _.each(indexes, function(index) {
          if (that.gridOptions.data[index.i].claimJobId === index.id) {
            that.gridApi.grid.modifyRows(that.gridOptions.data);
            that.gridApi.selection.selectRow(that.gridOptions.data[index.i]);
          }
        });
      }
    }

    /**
     *
     *
     * @param {any} row
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    setSelectedClaimJobs(row) {
      var that = this;
      if (row.isSelected) {
        if (that.listofjobsDocsDownload.indexOf(row.entity.claimJobId) === -1) {
          that.selectedRows.push(row.entity);
          that.listofjobsDocsDownload.push(row.entity.claimJobId);
        }
      } else {
        var index = that.listofjobsDocsDownload.indexOf(row.entity.claimJobId);
        that.selectedRows.splice(index, 1);
        that.listofjobsDocsDownload.splice(index, 1);
      }
      console.log(that.listofjobsDocsDownload);
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    toggleSort() {
      if (!this.sort || this.sort === 'asc') {
        this.descendingSortOrder = true;
        this.sort = 'desc';
      } else if (this.sort === 'desc') {
        this.descendingSortOrder = false;
        this.sort = 'asc';
      }
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    clearAllSelectedRows() {
      this.listofjobsDocsDownload = [];
      if (this.gridApi && this.gridApi.grid && this.gridApi.grid.rows) {
        this.gridApi.grid.rows.forEach(function(row: any){
          row.isSelected = false;
        });
      }
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    setButtons() {
      this.DispatcherService.publish('listclaimjobs:EnableButtons', {
        list: this.listofjobsDocsDownload.length > 0 || this.allSelected,
        tab: this.activeTab,
        name: 'AppealStatus'
      });
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    openSelectedClaimJobsHandler() {
      this.openSelectedClaimJobs(null);
    }

    /**
     *
     * @param {any} row
     * @param {any} single
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    openSelectedClaimJobs(row) {
      var that = this;
      var rowToShow;
      if (row) {
        rowToShow = {
          companyNo: row.entity.companyNo,
          claimHeaderId: row.entity.claimHeaderId,
          claimJobId: row.entity.claimJobId,
          selectedRows: that.selectedRows
        };
      } else {
        rowToShow = that.selectedRows[0];
      }
      that.$state.go('app.claim-job', {
        companyNo: rowToShow.companyNo,
        claimRepairHeaderId: rowToShow.claimHeaderId,
        claimJobId: rowToShow.claimJobId,
        selectedRows: that.selectedRows
      });
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    tabEnterHandler() {
      this.clearAllSelectedRows();
      this.setButtons();
    }

    /**
     *
     *
     * @param {any} company
     * @returns {*}
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    CompanyChangeHandler(company): any {
      this.selectedCompany = company;
    }

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf ListClaimJobsAppealStatusController
     */
    tabClaimSearchResultHandler(data) {
      var that = this;
      that.activeTab = data.search ? 3 : 2;
      if (data.search) {
        that.DispatcherService
          .publish('listclaimjobs:showSearchTab', {
            tab: that.activeTab
          });
      }
      if (data.result && angular.isArray(data.result.claimSearchResults)) {
        if (data.result.claimSearchResults.length < 1) {
          that.NotificationService.trigger({
            'data': {
              'primaryCode': '199999',
              'secondaryCode': 0
            }
          });
          that.loading = false;
          that.showTable = false;
          that.claimSearchResults = [];
          that.gridOptions.data = that.claimSearchResults;
        } else {
          var documents: Array < StandardClaimSearchResultModel > = data.result.claimSearchResults;
          var count = data.result.metadata ? data.result.metadata.count : data.result.claimSearchResults.length;
          that.loading = false;
          that.showTable = true;
          that.claimSearchResults = documents;
          that.gridOptions.totalItems = count;
          that.gridOptions.data = that.claimSearchResults;
          that.selectPreviouslySelectedRows();
        }
      }
    }
  }

  angular.module('uchpClientAngularApp').component('listClaimJobsAppealStatus', {
    controller: [
      'SelectCompaniesStore',
      'TabClaimSearchResultStore',
      'MiDocumentStore',
      'PrintDeliveryNoteDocumentStore',
      '$log',
      'DispatcherService',
      '$scope',
      '$state',
      'UserStore',
      'NotificationService',
      'localStorageService',
      '$translate',
      'uiGridConstants',
      ListClaimJobsAppealStatusController
    ],
    controllerAs: 'ctrl',
    bindings: {
      search: '<'
    },
    templateUrl: 'components/claim/claims/list-claim-jobs/list-claim-jobs-list/list-claim-jobs-app' +
    'ealstatus/list-claim-jobs-appealstatus.html'
  });
}
