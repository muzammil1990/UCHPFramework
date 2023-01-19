/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';
  import ITranslateService = angular.translate.ITranslateService;
  /**
   *
   *
   * @class ListClaimJobsNotFinishedController
   */
  class ListClaimJobsNotFinishedController {
    /**
     *
     *
     * @private
     * @type {Array < StandardClaimSearchResultModel >}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private claimSearchResults: Array < StandardClaimSearchResultModel >;
    /**
     *
     *
     * @private
     * @type {Array < StandardClaimSearchResultModel >}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private selectedRows: Array < StandardClaimSearchResultModel >;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private gridOptions: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private gridApi: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private tabClaimSearchResultsSubscription: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private notfinishedTabEnterSubscription: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private claimDeleteSubscription: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private openSelectedClaimJobsSubscription: any;
    /**
     *
     *
     * @private
     * @type {Number}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private activeTab: Number;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private listofjobsDocsDownload: any;
    /**
     *
     *
     * @private
     * @type {String}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private selectedCompany: String;
    /**
     *
     *
     * @private
     * @type {Number}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private tabjobtype: Number;
    /**
     *
     *
     * @private
     * @type {Array < any >}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private selectCompanies: Array < any >;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private companyChangeSubscription: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private typeOfConcernCellTemplateHelper: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private typeOfConcernCellTemplate: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private deleteColumnTemplate: any;
    /**
     *
     *
     * @private
     * @type {Number}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private typeOfConcern: Number;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private importerCellTemplate: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private importerCellTemplateHelper: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private repairingDealerCellTemplateHelper: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private repairingDealerCellTemplate: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private claimStatusIconBG: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private claimStatusIconUpperStack: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private claimStatusIconLowerStack: any;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private typeOfClaimTemplate: any;
    /**
     *
     *
     * @private
     * @type {String}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private sort: String = null;
    /**
     *
     *
     * @private
     * @type {Boolean}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private descendingSortOrder: Boolean = null;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private paginationOptions: any;
    /**
     *
     *
     * @private
     * @type {Boolean}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private showTable: Boolean = false;
    /**
     *
     *
     * @private
     * @type {string}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private dealerLabel: string;
    /**
     *
     *
     * @private
     * @type {string}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private importerLabel: string;
    /**
     *
     *
     * @private
     * @type {string}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private chassiLabel: string;
    /**
     *
     *
     * @private
     * @type {Boolean}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private loading: Boolean = true;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private search: any;
    /**
     *
     *
     * @private
     * @type {User}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private user: User;
    /**
     *
     *
     * @private
     * @type {string}
     * @memberOf ListClaimJobsNotFinishedController
     */
    private dispatcherAccessor: string;

    /**
     *
     *
     * @static
     *
     * @memberOf ListClaimJobsNotFinishedController
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
      '$translate',
      'localStorageService',
      'uiGridConstants'
    ];

    /**
     * Creates an instance of ListClaimJobsNotFinishedController.
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
     * @param {ITranslateService} $translate
     * @param {any} localStorageService
     *
     * @memberOf ListClaimJobsNotFinishedController
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
                private $translate: ITranslateService,
                private localStorageService
    ) {
      var that = this;
      that.listofjobsDocsDownload = [];
      that.selectedRows = [];
      that.tabjobtype = 4;
      that.dispatcherAccessor = 'dispatcher';
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
      that.notfinishedTabEnterSubscription = that.DispatcherService
        .subscribe('listclaimjobs:TabEnterNotFinished', that.tabEnterHandler.bind(this));
      that.openSelectedClaimJobsSubscription = that.DispatcherService
        .subscribe('listclaimjobs:OpenSelectedClaimJobsNotFinished', that.openSelectedClaimJobsHandler.bind(this));
      if (!that.DispatcherService[that.dispatcherAccessor].subscriptions['/']['listclaimjobs:claimDeleted']) {
        that.claimDeleteSubscription = that.DispatcherService
          .subscribe('listclaimjobs:claimDeleted', that.claimDeleteHandler.bind(this));
      }

      that.SelectCompaniesStore.getSelectCompanies().then(function (selectCompanies: Array < {} >) {
        if (selectCompanies !== null && selectCompanies !== undefined) {
          that.selectCompanies = selectCompanies;
        }
      });

      /**
       * Get initial company (used in translation keys)
       * */
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      that.dealerLabel = that.$translate.instant('LABEL.DEALER.' + that.selectedCompany);
      that.importerLabel = that.$translate.instant('LABEL.IMPORTER.' + that.selectedCompany);
      that.chassiLabel = that.$translate.instant('LABEL.CHASSI.' + that.selectedCompany);
      /**
       * Listen to company change
       * */
      that.companyChangeSubscription = that.DispatcherService
        .subscribe('change:CompanyChanged', that.CompanyChangeHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.companyChangeSubscription.unsubscribe();
        that.tabClaimSearchResultsSubscription.unsubscribe();
        that.notfinishedTabEnterSubscription.unsubscribe();
        that.openSelectedClaimJobsSubscription.unsubscribe();
        if (that.DispatcherService[that.dispatcherAccessor].subscriptions['/']['listclaimjobs:claimDeleted']) {
          that.claimDeleteSubscription.unsubscribe();
        }
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
      that.deleteColumnTemplate = ['<div class="text-left ui-grid-cell-contents remove-btn-grid-wrapper"',
        'uib-tooltip="Delete row" tooltip-placement="left">',
        '<button class="btn btn-sm btn-danger delete-button"',
        'mwl-confirm',
        'title=\'{{"DELETE" | translate }}\'',
        'message=\'{{"DELETE.ITEM.QUESTION" | translate }}\'',
        'confirm-text=\'{{"YES" | translate }}\'',
        'cancel-text=\'{{"NO" | translate }}\'',
        'placement="left"',
        'on-confirm="grid.appScope.ctrl.deleteGridRow(row)"',
        'on-cancel=""',
        'confirm-button-type="danger"',
        'cancel-button-type="default">',
        '<i class="fa fa-trash"></i>',
        '</button></div>'].join(' ');
      that.claimSearchResults = [];
      that.paginationOptions = {
        pageNumber: 1,
        pageSize: that.localStorageService.get('pageSize') || 10,
        sort: that.sort
      };
      that.gridOptions = {
        columnDefs: [
          {
            cellTemplate: `<div class="cellAlignCenter" 
            title="{{ 'claimJobStateId.' + row.entity.claimJobStateId | translate }}"><span class="fa-stack fa-2x">
              ${that.claimStatusIconUpperStack}${that.claimStatusIconBG}${that.claimStatusIconLowerStack}
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
            type: 'boolean',
            width: 120
          }, {
            cellTemplate: that.importerCellTemplate,
            displayName: that.importerLabel,
            enableFiltering: false,
            headerTooltip: true,
            field: 'repairingImporter',
            name: 'Importer',
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
            '<a href ng-click="grid.appScope.ctrl.openSelectedClaimJobs(row)">{{row.entity.refNo}} - {{row.entity.jobNo}}</a>' +
            '</div>',
            displayName: 'Ref. No. - Job. No.',
            enableFiltering: false,
            field: 'refNo,jobNo',
            headerTooltip: true,
            name: 'RefJob',
            type: 'boolean'
          }, {
            cellTemplate: that.typeOfClaimTemplate,
            displayName: 'Type',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'typeOfClaim'
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
            '{{ "claimJobStateId." + row.entity.claimJobStateId | translate }}</span></div>',
            displayName: 'Status',
            enableFiltering: false,
            field: 'claimJobStateId',
            headerTooltip: true,
            type: 'boolean'
          }, {
            cellTemplate: '<div class="text-left ui-grid-cell-contents">{{row.entity.chassisSeries}}' +
            '<span ng-if="row.entity.chassisSeries.length > 0"> - {{row.entity.chassisNo}}</span></div>',
            displayName: that.chassiLabel,
            enableFiltering: false,
            field: 'chassisSeries,chassisNo',
            headerTooltip: true,
            type: 'boolean',
            width: 90
          }, {
            cellTooltip: false,
            displayName: 'Marketing Type',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'marketType',
            headerTooltip: true
          }, {
            cellTooltip: false,
            displayName: 'Repair order number',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'repairOrderNo',
            headerTooltip: true
          }, {
            cellTemplate: '<div class="text-right ui-grid-cell-contents">{{row.entity.grandTotal | number:2}}</div>',
            displayName: 'Grand total (GBP)',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: false,
            field: 'grandTotal',
            headerTooltip: true
          }, {
            allowCellFocus: false,
            cellTemplate: that.deleteColumnTemplate,
            enableFiltering: false,
            enableSorting: false,
            headerTooltip: 'Delete',
            name: ' ',
            width: 30
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
          rows.forEach(function (row) {
            that.setSelectedClaimJobs(row);
          });
          that.setButtons();
        });
        that.gridApi.selection.on.rowSelectionChanged(null, function (row) {
          that.setSelectedClaimJobs(row);
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
     * @memberOf ListClaimJobsNotFinishedController
     */
    selectAllrows(checked) {
      // TODO: toggle select all rows property for adding to request,
      // when api added by backend
      if (checked) {
        this.gridApi.selection.selectAllRows();
      } else {
        this.gridApi.selection.clearSelectedRows();
      }
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsNotFinishedController
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
     * @param {any} row
     * @param {any} single
     *
     * @memberOf ListClaimJobsNotFinishedController
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
     * @param {any} row
     *
     * @memberOf ListClaimJobsNotFinishedController
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
        that.listofjobsDocsDownload.splice(index, 1);
        that.selectedRows.splice(index, 1);
      }
    }

    /**
     *
     *
     * @param {any} row
     *
     * @memberOf ListClaimJobsNotFinishedController
     */
    deleteGridRow(row) {
      let index = this.claimSearchResults.indexOf(row.entity);
      this.TabClaimSearchResultStore.deleteCurrentClaimJob({
        companyNo: row.entity.companyNo,
        claimHeaderId: row.entity.claimHeaderId,
        claimJobId: row.entity.claimJobId,
        index: index
      });
    }

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf ListClaimJobsNotFinishedController
     */
    claimDeleteHandler(data) {
      var message = '';
      var title = '';
      // TODO: translations for messages
      if (data.success) {
        message = 'Row deleted';
        title = 'Success';
        this.NotificationService.sendSuccess(message, title);
        if (data.index) {
          this.claimSearchResults.splice(data.index, 1);
        }
      } else {
        message = 'Row could not be deleted';
        title = 'Error';
        this.NotificationService.sendError(message, title);
      }
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsNotFinishedController
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
     * @memberOf ListClaimJobsNotFinishedController
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
     * @memberOf ListClaimJobsNotFinishedController
     */
    setButtons() {
      this.DispatcherService.publish('listclaimjobs:EnableButtons', {
        list: this.listofjobsDocsDownload.length > 0,
        tab: this.activeTab,
        name: 'NotFinished'
      });
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsNotFinishedController
     */
    openSelectedClaimJobsHandler() {
      this.openSelectedClaimJobs(null);
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsNotFinishedController
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
     * @memberOf ListClaimJobsNotFinishedController
     */
    CompanyChangeHandler(company): any {
      this.selectedCompany = company;
    }

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf ListClaimJobsNotFinishedController
     */
    tabClaimSearchResultHandler(data) {
      var that = this;
      that.activeTab = data.search ? 3 : 0;
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
          that.gridOptions.data = that.claimSearchResults;
          that.gridOptions.totalItems = count;
          that.selectPreviouslySelectedRows();
        }
      }
    }
  }

  angular.module('uchpClientAngularApp').component('listClaimJobsNotFinished', {
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
      '$translate',
      'localStorageService',
      'uiGridConstants',
      ListClaimJobsNotFinishedController
    ],
    controllerAs: 'ctrl',
    bindings: {
      search: '<'
    },
    templateUrl: 'components/claim/claims/list-claim-jobs/list-claim-jobs-list/list-claim-jobs-not' +
    'finished/list-claim-jobs-notfinished.html'
  });
}
