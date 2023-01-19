/// <reference path="../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';
  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class PrintCreditListController {
    private creditingDocuments: Array<CreditDocumentModel>;
    private brandlist: Array < BrandModel >;
    private gridOptions: any;
    private gridApi: any;
    private importerCellTemplateHelper: string;
    private importerCellTemplate: string;
    private searchCreditDocumentsSubscription: any;
    private searchCreditDocumentsClearSubscription: any;
    private brandListResultsSubscription: any;
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
      'CreditDocumentStore',
      'BrandStore',
      '$log',
      'SelectCompaniesStore',
      'DispatcherService',
      '$scope',
      'NotificationService',
      'localStorageService',
      '$translate',
      'uiGridConstants'
    ];
    constructor(private CreditDocumentStore: ICreditDocumentStore,
                private BrandStore: IBrandStore,
                private $log: ng.ILogService,
                private SelectCompaniesStore: ISelectCompaniesStore,
                private DispatcherService: IDispatcherService,
                private $scope: ng.IScope,
                private NotificationService: INotificationService,
                private localStorageService,
                private $translate: ng.translate.ITranslateService) {
      var that = this;
      // Subscribe 'printcreditdocument:SearchCreditDocuments' from CreditDocumentStore
      that.searchCreditDocumentsSubscription = that.DispatcherService.subscribe('printcreditdocument:SearchCreditDocuments',
        this.creditDocumentsResultHandler.bind(this));
      that.searchCreditDocumentsClearSubscription = that.DispatcherService.subscribe('printcreditdocument:SearchCreditDocumentsClear',
        this.creditDocumentsClearHandler.bind(this));
      that.brandListResultsSubscription = that.DispatcherService.subscribe('GetBrandList:BrandList',
        this.brandListsResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.searchCreditDocumentsClearSubscription.unsubscribe();
        that.searchCreditDocumentsSubscription.unsubscribe();
        that.brandListResultsSubscription.unsubscribe();
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

      that.importerCellTemplateHelper = `{{ row.entity.importerNo }} 
          <span ng-if='row.entity.importerName.length > 0'> - </span> {{ row.entity.importerName }}`;
      that.importerCellTemplate = `<div class="text-left ui-grid-cell-contents">{{ row.entity.importerNo }} 
          <span ng-if='row.entity.importerName.length > 0'> - </span>  {{ row.entity.importerName }}</div>`;

      that.gridOptions = {
        columnDefs: [
          {
            enableFiltering: false,
            enableHiding: false,
            headerTooltip: true,
            field: 'companyName',
            name: 'Company',
            width: 150
          }, {
            cellTemplate: that.importerCellTemplate,
            headerTooltip: true,
            displayName: that.importerLabel,
            enableFiltering: false,
            enableHiding: false,
            field: 'importerNo',
            name: 'Importer',
            width: 250
          }, {
            cellTemplate: '<div class="text-left ui-grid-cell-contents"> {{ row.entity.dealerNo }} ' +
            '<span ng-if="row.entity.dealerName.length > 0"> - </span> {{ row.entity.dealerName }}</div>',
            headerTooltip: true,
            displayName: that.dealerLabel,
            enableFiltering: false,
            enableHiding: false,
            field: 'dealerNo',
            name: 'dealer',
            width: 250
          }, {
            cellTemplate: '<div class="text-left ui-grid-cell-contents">{{ "make." + row.entity.make | translate }}</div>',
            headerTooltip: true,
            enableFiltering: false,
            enableHiding: false,
            field: 'make',
            name: 'Brand',
            width: 70
          }, {
            enableFiltering: false,
            headerTooltip: true,
            enableHiding: false,
            field: 'creditDocumentId',
            name: 'Credit document no.',
            width: 150
          }, {
            cellFilter: 'date:"dd/MM/yyyy"',
            headerTooltip: true,
            enableFiltering: false,
            enableHiding: false,
            field: 'creditingDate',
            name: 'Crediting date',
            width: 120
          }, {
            cellTemplate: '<div class="text-left ui-grid-cell-contents">{{ row.entity.creditingDocumentTypeDesc }}</div>',
            headerTooltip: true,
            enableFiltering: false,
            enableHiding: false,
            field: 'creditingDocumentTypeId',
            name: 'Credit Document Type'
          }, {
            enableFiltering: false,
            headerTooltip: true,
            enableHiding: false,
            field: 'creditOccId',
            name: 'Credit run id',
            width: 100
          }, {
            cellFilter: 'filename',
            headerTooltip: true,
            cellTemplate: '<div class="text-center ui-grid-cell-contents downloadIconAlignCenter" ' +
            'ng-click="grid.appScope.ctrl.getCreditDocument(row.entity.creditingDocumentId, row.entity.mimeType)">' +
            '<i class="print-download-file fa {{ grid.appScope.ctrl.getFileIco(row.entity.mimeType) }} grid-icon" ' +
            'title="Download file" aria-hidden="true"></i></div>',
            enableFiltering: false,
            enableHiding: false,
            field: 'mimeType',
            name: '',
            width: 50
          }
        ],
        data: 'ctrl.creditingDocuments',
        enableColumnMenus: false,
        enableColumnResize: true,
        enableFiltering: true,
        headerRowHeight: 55,
        multiSelect: false,
        onRegisterApi: function (gridApi) {
          that.gridApi = gridApi;
          that.gridApi.core.on.sortChanged(that.$scope, function (grid, sortColumns) {
            if (sortColumns.length === 0) {
              that.sort = null;
            } else {
              that.loading = true;
              // TODO: backend should harmonize props, eg creditOccId should be CREDIT_OCC_ID
              var sortColumn = sortColumns[0].field;
              that.toggleSort();
            }
            that.DispatcherService.publish('printcreditdocument:SortCreditDocuments', {
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
            that.DispatcherService.publish('printcreditdocument:SwitchPageCreditDocuments', {
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

    getCreditDocument(creditDocumentId: string, mimeType: string) {
      if (creditDocumentId) {
        this.CreditDocumentStore.getCreditDocument(creditDocumentId, mimeType);
      }
    }

    getFileIco(type: string) {
      return (type === 'PDF' || type === 'application/pdf') ? 'fa-file-pdf-o' : 'fa-file-excel-o';
    }

    // Subscribe Handler function for 'printcreditdocument:SearchCreditDocuments'
    creditDocumentsResultHandler(data) {
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
          var documents: Array<CreditDocumentModel> = data.creditingDocuments;
          var count = data.metadata ? data.metadata.count : data.creditingDocuments.length;
          that.gridOptions.totalItems = count;
          that.loading = false;
          that.showTable = true;
          that.creditingDocuments = documents;
        }
      }
    }

    // Subscribe Handler function for 'printcreditdocument:SearchCreditDocumentsClear'
    creditDocumentsClearHandler() {
      this.creditingDocuments = [];
    }

    brandListsResultHandler(data) {
      if (data.BrandListResults.length > 0) {
        this.brandlist = data.BrandListResults;
      }
    }
  }

  angular.module('uchpClientAngularApp')
    .component('printCreditDocumentList', {
    controller: [
      'CreditDocumentStore',
      'BrandStore',
      '$log',
      'SelectCompaniesStore',
      'DispatcherService',
      '$scope',
      'NotificationService',
      'localStorageService',
      '$translate',
      'uiGridConstants',
      PrintCreditListController
    ],
    controllerAs: 'ctrl',
    templateUrl: 'components/crediting/print-credit-document/print-credit-document-list/print-credit-document-list.html'
  });
}
