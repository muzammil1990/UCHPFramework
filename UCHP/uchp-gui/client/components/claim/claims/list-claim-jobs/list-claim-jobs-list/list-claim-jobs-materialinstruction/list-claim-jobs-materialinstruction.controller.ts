/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';

  class ListClaimJobsMaterialInstructionController {
    private claimSearchResults: Array < StandardClaimSearchResultModel >;
    private gridOptions: any;
    private gridApi: any;
    private tabClaimSearchResultsSubscription: any;
    private listofjobsDocsDownload: any;
    private selectedCompany: String;
    private activeTab: Number;
    private tabjobtype: Number;
    private selectCompanies: Array < any >;
    private companyChangeSubscription: any;
    private listPrintMiDocumentsSubscription: any;
    private listPrintDeliverNotesSubscription: any;
    private openSelectedClaimJobsSubscription: any;
    private materialTabEnterSubscription: any;
    private claimJobCompanyNos: Array < any >;
    private claimHeaderIds: Array < any >;
    private claimJobIds: Array < any >;
    private selectedRows: Array < any >;
    private typeOfConcernCellTemplateHelper: any;
    private typeOfConcernCellTemplate: any;
    private typeOfConcern: Number;
    private printCreditedStatusIconBG: any;
    private printCreditedStatusIconUpperStack: any;
    private printCreditedStatusIconLowerStack: any;
    private printCreditedStatusIconCellTemplate: any;
    private typeOfClaimTemplate: any;
    private sort: String = null;
    private descendingSortOrder: Boolean = null;
    private paginationOptions: any;
    private showTable: Boolean = false;
    private dealerLabel: string;
    private loading: Boolean = true;
    private search: any;
    private user: User;

    /**
     *
     *
     * @static
     *
     * @memberOf ListClaimJobsMaterialInstructionController
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
      'uiGridConstants',
      '$sanitize',
      '$sce'
    ];

    /**
     * Creates an instance of ListClaimJobsMaterialInstructionController.
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
     * @param {ng.translate.ITranslateService} $translate
     *
     * @memberOf ListClaimJobsMaterialInstructionController
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
                private $translate: ng.translate.ITranslateService,
                private $sanitize: any,
                private $sce: any
    ) {
      var that = this;
      that.tabjobtype = 2;
      that.listofjobsDocsDownload = {};
      that.claimJobCompanyNos = [];
      that.claimJobIds = [];
      that.claimHeaderIds = [];
      that.claimSearchResults = [];
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
      that.listPrintMiDocumentsSubscription = that.DispatcherService
        .subscribe('listclaimjobs:PrintMiDocuments', that.listPrintMiDocumentsHandler.bind(this));
      that.listPrintDeliverNotesSubscription = that.DispatcherService
        .subscribe('listclaimjobs:PrintDeliverNotes', that.listPrintDeliverNotesHandler.bind(this));
      that.openSelectedClaimJobsSubscription = that.DispatcherService
        .subscribe('listclaimjobs:OpenSelectedClaimJobsMaterial', that.openSelectedClaimJobsHandler.bind(this));
      that.materialTabEnterSubscription = that.DispatcherService
        .subscribe('listclaimjobs:TabEnterMaterial', that.tabEnterHandler.bind(this));

      that.SelectCompaniesStore.getSelectCompanies().then(function (selectCompanies: Array < {} >) {
        if (selectCompanies !== null && selectCompanies !== undefined) {
          that.selectCompanies = selectCompanies;
        }
      });
      /**
       * Get initial company (used in translation keys)
       * */
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      that.dealerLabel = that.$translate.instant('LABEL.DEALER.' + that.selectedCompany) + ' No.';
      /**
       * Listen to company change
       * */
      that.companyChangeSubscription = that.DispatcherService.subscribe('change:CompanyChanged', that.CompanyChangeHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.tabClaimSearchResultsSubscription.unsubscribe();
        that.companyChangeSubscription.unsubscribe();
        that.listPrintMiDocumentsSubscription.unsubscribe();
        that.listPrintDeliverNotesSubscription.unsubscribe();
        that.materialTabEnterSubscription.unsubscribe();
        that.openSelectedClaimJobsSubscription.unsubscribe();
      });
      that.typeOfConcernCellTemplateHelper = `{{ 'TYPEOFCONCERN_.' + row.entity.typeOfConcern | translate }}`;
      that.typeOfConcernCellTemplate = `<div uib-tooltip="${that.typeOfConcernCellTemplateHelper}" tooltip-placement="right" 
        class="text-left ui-grid-cell-contents">{{ 'TYPEOFCONCERN_.' + row.entity.typeOfConcern | translate }}</div>`;

      that.printCreditedStatusIconBG = `<i class=\'{{"printStatusCredited." + printStatusCreditedIcon | translate }} \'></i>`;
      that.printCreditedStatusIconUpperStack =
        `<i class=\'{{ "printStatusCredited." +  printStatusCreditedIcon + "_1" | translate}}\'></i>`;
      that.printCreditedStatusIconLowerStack =
        `<i class=\'{{ "printStatusCredited." +  printStatusCreditedIcon + "_2" | translate }} \'></i>`;
      that.printCreditedStatusIconCellTemplate =
        `<div uib-tooltip=">This material intruction is running old. Please print!</div>`;

        that.typeOfClaimTemplate =
          `<div uib-tooltip=\'{{ "typeOfClaim." + row.entity.typeOfClaim | translate }}\' tooltip-placement="right" 
          class="text-left ui-grid-cell-contents">{{ "typeOfClaim." + row.entity.typeOfClaim | translate }}</div>`;
      that.paginationOptions = {
        pageNumber: 1,
        pageSize: that.localStorageService.get('pageSize') || 10,
        sort: that.sort
      };
      that.gridOptions = {
        columnDefs: [
          {
            cellTemplate: `<div class="img-wrapper" style="width: 100px !important;" 
              ng-bind-html="grid.appScope.ctrl.returnIcon(row) | sce"></div>`,
            displayName: '',
            enableFiltering: false,
            field: 'remindDealerToPrint',
            width: 40
          }, {
            cellTemplate: that.typeOfConcernCellTemplate,
            displayName: 'Type of concern',
            enableFiltering: false,
            name: 'typeOfConcern',
            type: 'boolean'
          }, {
            displayName: that.dealerLabel,
            enableFiltering: false,
            field: 'repairingDealer',
            headerTooltip: true,
            type: 'boolean'
          }, {
            displayName: 'Repair Order No.',
            enableFiltering: false,
            field: 'repairOrderNo',
            headerTooltip: true,
            name: 'Repair Order No',
            type: 'boolean'
          }, {
            cellTemplate: '<div class="text-left ui-grid-cell-contents">' +
            '<a href ng-click="grid.appScope.ctrl.openSelectedClaimJobs(row)">' +
            '{{ row.entity.refNo }} - {{ row.entity.jobNo }}</a></div>',
            displayName: 'Ref. No. - Job. No.',
            enableFiltering: false,
            headerTooltip: true,
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
            displayName: 'MI name',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'miName',
            headerTooltip: true
          }, {
            cellTemplate: '<div class="text-left ui-grid-cell-contents">{{ "miType." + row.entity.miType | ' +
            'translate }}</div>',
            displayName: 'MI type',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'miType',
            headerTooltip: true
          }, {
            cellFilter: 'date:"dd/MM/yyyy hh:mm"',
            displayName: 'MI date',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'miDate',
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
            allowCellFocus: false,
            cellTemplate: `<div class="remove-btn-grid-wrapper" ng-bind-html="grid.appScope.ctrl.returnMaterial(row) | sce">
            </div>`,
            enableFiltering: false,
            enableSorting: false,
            name: 'Return material',
            width: 120
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
        showFooter: false,
        useExternalPagination: true,
        useExternalSorting: true
      };

      /**
       *  UI Grid Handlers
       *  miType 1 represents type : Send
       *  MI type must be ‘send’ and claim job must be in status credited/crediting ongoing
       *  TODO: Extract common part with returnMaterial
       * @param {any} gridApi
       */
      that.gridOptions.onRegisterApi = function (gridApi) {
        that.gridApi = gridApi;
        that.gridApi.selection.on.rowSelectionChangedBatch(null, function () {
          that.setDownLoadClaimJobs();
        });
        that.gridApi.selection.on.rowSelectionChanged(null, function (gridRow) {
          if ( (parseInt(gridRow.entity.miType, 10) === 1 && parseInt(gridRow.entity.claimJobStateId, 10) === 12) ||
            (parseInt(gridRow.entity.miType, 10) === 1 && parseInt(gridRow.entity.claimJobStateId, 10) === 13) ) {
            that.NotificationService.trigger({
              'data': {
                'primaryCode': '700000',
                'secondaryCode': 0
              }
            });
            gridRow.isSelected = false;
          } else {
            that.setDownLoadClaimJobs();
          }



        });

        that.gridApi.core.on.sortChanged(that.$scope, function (grid, sortColumns) {
          that.loading = true;
          if (sortColumns.length === 0) {
            that.sort = null;
          } else {
            var sortColumn = sortColumns[0].field;
            that.toggleSort();
          }
          that.TabClaimSearchResultStore.getTabClaimSearchResult(that.tabjobtype, that.typeOfConcern,  {
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
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    setDownLoadClaimJobs() {
      var that = this;
      that.selectedRows = that.gridApi.selection.getSelectedRows();
      that.claimJobIds = [];
      that.claimHeaderIds = [];
      that.claimJobCompanyNos = [];
      _.each(that.selectedRows, function(job) {
        that.claimJobIds.push(job.claimJobId);
        that.claimHeaderIds.push(job.claimHeaderId);
        that.claimJobCompanyNos.push(job.companyNo);
      });
      that.listofjobsDocsDownload = {
        claimHeaderIds: that.claimHeaderIds.toString(),
        claimJobIds: that.claimJobIds.toString(),
        claimJobCompanyNos: that.claimJobCompanyNos.toString()
      };
      console.log('docs: ' + JSON.stringify(that.listofjobsDocsDownload));
      that.setButtons();
    }

    /**
     *
     *
     * @param {any} checked
     *
     * @memberOf ListClaimJobsMaterialInstructionController
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
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    toggleSort() {
      var that = this;
      if (!that.sort || that.sort === 'asc') {
        that.descendingSortOrder = true;
        that.sort = 'desc';
      } else if (that.sort === 'desc') {
        that.descendingSortOrder = false;
        that.sort = 'asc';
      }
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    selectPreviouslySelectedRows() {
      var that = this;
      let indexes = [];
      if (that.gridApi && that.gridApi.grid && that.gridApi.grid.rows) {
        _.each(that.gridApi.grid.rows, function(row: any, i){
          _.each(that.selectedRows, function(job: any) {
            if (job === row.claimJobId) {
              indexes.push({
                i: i,
                id: job
              });
            }
          });
        });
        // we need to store a local array here, because the selectionchanged event
        // is triggering a reset of that.selectedRows
        _.each(indexes, function(index) {
          if (that.gridOptions.data[index.i].claimJobId === index.id) {
            that.gridApi.grid.modifyRows(that.gridOptions.data);
            that.gridApi.selection.selectRow(that.gridOptions.data[index.i]);
          }
        });
      }
    }

    /**
     * Clear Rows
     *
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    clearAllSelectedRows() {
      var that = this;
      that.selectedRows = [];
      that.claimJobIds = [];
      that.claimHeaderIds = [];
      that.claimJobCompanyNos = [];
      if (that.gridApi && that.gridApi.grid && that.gridApi.grid.rows) {
        that.gridApi.selection.clearSelectedRows();
      }
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    openSelectedClaimJobsHandler() {
      this.openSelectedClaimJobs(null);
    }
    /**
     *
     * @param {any} row
     * @param {any} single
     *
     * @memberOf ListClaimJobsMaterialInstructionController
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
     * Disabling ReturnMaterial Checkbox
     * @param {any} row
     * @param {any} single
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    returnMaterial(row) {
      if (
        (parseInt(row.entity.miType, 10) === 1 && parseInt(row.entity.claimJobStateId, 10) === 13)
        // || (parseInt(row.entity.miType, 10) === 1 && parseInt(row.entity.claimJobStateId, 10) === 12)
      ) {
        return '<input title="This claim job is being credited and can therefore not to be returned at this time." ' +
          'type="checkbox" disabled="disabled" aria-label="Left Align" />';
      } else if (
        (parseInt(row.entity.miType, 10) === 2) || (parseInt(row.entity.miType, 10) === 3)
      ) {
        return '<input title="This material intruction of this claim job is not type send." disabled="disabled" ' +
          'type="checkbox" disabled="disabled" aria-label="Left Align" />';
      } else {
        return '<input type="checkbox" aria-label="Left Align" />';
      }



    }

    /**
     * Disabling ReturnMaterial Checkbox
     * @param {any} row
     * @param {any} single
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    returnIcon(row) {
      if (row.entity.remindDealerToPrint) {
        return '<div class="printStatusIconWrapper" title="This material intruction is running old. Please print!" >' +
          '<i class=\'fa fa-print fa-stack-2x blue_color\'></i>' +
          '<i class=\'fa fa-exclamation fa-stack-2x red_color fa-thirdIcon-exclamation\'></i></div>';
      } else {
        return '';
      }
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    listPrintMiDocumentsHandler() {
      var that = this;
      that.$log.debug('List of Print MI documents::: ' + that.listofjobsDocsDownload);
      that.MiDocumentStore.getPrintMIDocuments(that.listofjobsDocsDownload);
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    listPrintDeliverNotesHandler() {
      var that = this;
      that.$log.debug('List of Print Delivery Note documents::: ' + that.listofjobsDocsDownload);
      that.PrintDeliveryNoteDocumentStore.getPrintDeliveryNoteDocuments(that.listofjobsDocsDownload);
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    tabEnterHandler() {
      this.clearAllSelectedRows();
      this.setButtons();
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    setButtons() {
      this.DispatcherService.publish('listclaimjobs:EnableButtons', {
        list: this.selectedRows.length > 0,
        mi: this.selectedRows.length > 0,
        tab: this.activeTab,
        name: 'Material'
      });
    }

    /**
     *
     *
     * @param {any} company
     * @returns {*}
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    CompanyChangeHandler(company): any {
      this.selectedCompany = company;
    }

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf ListClaimJobsMaterialInstructionController
     */
    tabClaimSearchResultHandler(data) {
      var that = this;
      that.activeTab = data.search ? 3 : 1;
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

  angular.module('uchpClientAngularApp').component('listClaimJobsMaterialInstruction', {
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
      '$sanitize',
      '$sce',
      ListClaimJobsMaterialInstructionController
    ],
    controllerAs: 'ctrl',
    templateUrl: 'components/claim/claims/list-claim-jobs/list-claim-jobs-list/list-claim-jobs-mat' +
    'erialinstruction/list-claim-jobs-materialinstruction.html'
  });
}
