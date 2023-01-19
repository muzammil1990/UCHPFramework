/// <reference path="../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';
  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class PrintOutstandingClaimSummaryListController {
    private creditingDocuments: Array<CreditDocumentModel>;
    private gridOptions: any;
    private gridApi: any;
    private searchOutstandingClaimSummaryDocsSubscription: any;
    private selectedCompany: String;
    private sort: String = null;
    private descendingSortOrder: Boolean = null;
    private paginationOptions: any;
    private showTable: Boolean = false;
    private loading: Boolean = false;
    private dealerLabel: string;
    private importerLabel: string;
    private dateLabel: string;

    static $inject = [
      'OutstandingClaimSummaryDocumentStore',
      '$log',
      'SelectCompaniesStore',
      'DispatcherService',
      'NotificationService',
      '$scope',
      'localStorageService',
      '$translate',
      'uiGridConstants'
    ];
    /**
     * Creates an instance of PrintOutstandingClaimSummaryListController.
     * 
     * @param {IOutstandingClaimSummaryDocumentStore} OutstandingClaimSummaryDocumentStore
     * @param {ng.ILogService} $log
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {IDispatcherService} DispatcherService
     * @param {INotificationService} NotificationService
     * @param {ng.IScope} $scope
     * @param {any} localStorageService
     * @param {ng.translate.ITranslateService} $translate
     * 
     * @memberOf PrintOutstandingClaimSummaryListController
     */
    constructor(private OutstandingClaimSummaryDocumentStore: IOutstandingClaimSummaryDocumentStore,
                private $log: ng.ILogService,
                private SelectCompaniesStore: ISelectCompaniesStore,
                private DispatcherService: IDispatcherService,
                private NotificationService: INotificationService,
                private $scope: ng.IScope,
                private localStorageService,
                private $translate: ng.translate.ITranslateService) {
      var that = this;
      //Subscribe 'outstandingclaimsummarydocument:OutstandingClaimSummaryDocuments' from OutstandingClaimSummaryDocumentStore
      that.searchOutstandingClaimSummaryDocsSubscription =
        that.DispatcherService.subscribe('outstandingclaimsummarydocument:OutstandingClaimSummaryDocuments',
          this.outstandingClaimSummaryDocumentsResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.searchOutstandingClaimSummaryDocsSubscription.unsubscribe();
      });
      /**
       * Get initial company (used in translation keys)
       * */
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      that.dealerLabel = that.$translate.instant('LABEL.DEALER.' + that.selectedCompany);
      that.importerLabel = that.$translate.instant('LABEL.IMPORTER.' + that.selectedCompany);
      that.dateLabel = that.$translate.instant('CREATION_DATE');
      that.$log.debug('ListDocumentsController Initialized');
      that.creditingDocuments = [];
      that.paginationOptions = {
        pageNumber: 1,
        pageSize: that.localStorageService.get('pageSize') || 10,
        sort: that.sort
      };
      that.gridOptions = {
        columnDefs: [
          {
            enableFiltering: false,
            enableHiding: false,
            field: 'companyName',
            name: 'Company'
          }, {
            cellTemplate: '<div class="cellAlignLeft"> {{ row.entity.importerNo }} ' +
            '<span ng-if="row.entity.importerName.length > 0"> - </span> {{ row.entity.importerName }} </div>',
            displayName: that.importerLabel,
            enableFiltering: false,
            enableHiding: false,
            field: 'importerName',
            name: 'Importer'
          }, {
            cellTemplate: '<div class="cellAlignLeft"> {{ row.entity.dealerNo }} ' +
            '<span ng-if="row.entity.dealerName.length > 0"> - </span> {{ row.entity.dealerName }}<' +
            '/div>',
            displayName: that.dealerLabel,
            enableFiltering: false,
            enableHiding: false,
            field: 'dealerName',
            name: 'dealer'
          }, {
            cellTemplate: '<div class="cellAlignLeft">{{ "make." + row.entity.make | translate }}</div>',
            enableFiltering: false,
            enableHiding: false,
            field: 'make',
            name: 'Brand'
          }, {
            cellFilter: 'date:"dd/MM/yyyy"',
            displayName: that.dateLabel,
            enableFiltering: false,
            enableHiding: false,
            field: 'modifyTime'
          }, {
            cellFilter: 'filename',
            cellTemplate: '<div class="text-center ui-grid-cell-contents downloadIconAlignCenter" ' +
            'ng-click="grid.appScope.ctrl.getOutstandingClaimSummaryDocument(row.entity.creditingDocumentId, row.entity.mimeType)">' +
            '<i class="print-download-file fa {{ grid.appScope.ctrl.getFileIco(row.entity.mimeType) }} grid-icon" ' +
            'title="Download file" aria-hidden="true"></i></div>',
            enableFiltering: false,
            // TODO: should we enable sorting on mimeType??
            enableSorting: false,
            enableHiding: false,
            field: 'mimeType',
            name: 'Download',
            width: 90
          }
        ],
        data: 'ctrl.creditingDocuments',
        enableColumnMenus: false,
        enableColumnResize: true,
        enableFiltering: true,
        enablePaging: true,
        headerRowHeight: 55,
        multiSelect: false,
        onRegisterApi: function (gridApi) {
          that.gridApi = gridApi;
          that.gridApi.core.on.sortChanged(that.$scope, function (grid, sortColumns) {
            that.loading = true;
            if (sortColumns.length === 0) {
              that.sort = null;
            } else {
              // TODO: backend should harmonize props, eg creditOccId should be CREDIT_OCC_ID
              var sortColumn = sortColumns[0].field;
              that.toggleSort();
            }
            that.DispatcherService.publish('printoutstandingclaim:SortCreditDocuments', {
              'pageNumber': that.paginationOptions.pageNumber,
              'pageSize': that.paginationOptions.pageSize,
              'sort': that.descendingSortOrder,
              'sortColumn': sortColumn // CREDIT_DOCUMENT_ID
            });
          });
          that.gridApi.pagination.on.paginationChanged(that.$scope, function (newPage, pageSize) {
            that.loading = true;
            that.paginationOptions.pageNumber = newPage;
            that.paginationOptions.pageSize = pageSize;
            that.localStorageService.set('pageSize', pageSize);
            that.DispatcherService.publish('printoutstandingclaim:SwitchPageCreditDocuments', {
              'pageNumber': that.paginationOptions.pageNumber,
              'pageSize': that.paginationOptions.pageSize
            });
          });
        },
        paginationPageSizes: [
          10, 25, 50
        ],
        rowHeight: 29,
        paginationPageSize: that.localStorageService.get('pageSize') || 10,
        showFooter: false,
        useExternalPagination: true,
        useExternalSorting: true
      };
    }

    toggleSort() {
      if (!this.sort || this.sort === 'asc') {
        this.descendingSortOrder = true;
        this.sort = 'desc';
      } else if (this.sort === 'desc') {
        this.descendingSortOrder = false;
        this.sort = 'asc';
      }
    }

    getFileIco(type: string) {
      return (type === 'PDF' || type === 'application/pdf') ? 'fa-file-pdf-o' : 'fa-file-excel-o';
    }

    getOutstandingClaimSummaryDocument(creditingDocumentId: string, mimeType: string) {
      if (creditingDocumentId !== null) {
        this.OutstandingClaimSummaryDocumentStore.getOutstandingClaimSummaryDocument(creditingDocumentId, mimeType);
      }
    }
    /**
     * Subscribe Handler function for 'outstandingclaimsummarydocument:OutstandingClaimSummaryDocuments'
     * @param data
       */
    outstandingClaimSummaryDocumentsResultHandler(data) {
      var that = this;
      // console.log('Data ::: ' + data); console.log('Data creditDocumentsSearchResults ::: ' + data.creditDocumentsSearchResults.length);
      if (data && angular.isArray(data.creditingDocuments)) {
        if (data.creditingDocuments.length < 1) {
          that.NotificationService.trigger({
            'data': {
              'primaryCode': '199999',
              'secondaryCode': 0
            }
          });
          that.creditingDocuments = [];
          that.showTable = false;
          that.loading = false;
        } else {
          var documents = data.creditingDocuments;
          var count = data.metadata ? data.metadata.count : data.creditingDocuments.length;
          that.gridOptions.totalItems = count;
          that.loading = false;
          that.showTable = true;
          that.gridOptions.paginationPageSizes = that.gridOptions.paginationPageSizes.filter(item => item < count);
          that.creditingDocuments = documents;
        }
      }
    }
  }

  angular
    .module('uchpClientAngularApp')
    .component('printOutstandingClaimSummaryList', {
      controller: [
        'OutstandingClaimSummaryDocumentStore',
        '$log',
        'SelectCompaniesStore',
        'DispatcherService',
        'NotificationService',
        '$scope',
        'localStorageService',
        '$translate',
        'uiGridConstants',
        PrintOutstandingClaimSummaryListController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/crediting/print-outstanding-claim-summary/' +
      'print-outstanding-claim-summary-list/print-outstanding-claim-summary-list.html'
    });
}
