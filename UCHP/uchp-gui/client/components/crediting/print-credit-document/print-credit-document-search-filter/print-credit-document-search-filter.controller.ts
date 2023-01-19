/// <reference path="../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

var targetService = 'printCreditDocument';

  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class PrintCreditDocumentSearchFilterController {
    private CACHE_KEY_PREFIX = 'PrintCreditDocument.form';
    private contenttypes: Array < ContentTypeModel >;
    private brandlist: Array < BrandModel >;
    private creditDocuments: Array < CreditDocumentModel >;
    private creditDocumentsData: Object = null;
    private creditDocumentForm: any;
    private sortCreditDocumentsSubscription: any;
    private switchPageCreditDocumentsSubscription: any;
    private cachedCreditDocumentsSubscription: any;
    private selectedCompany: String;
    private selectCompanies: Array < any >;
    private gridState: any;
    private DealerPattern: any;

    /**
     *
     *
     * @static
     *
     * @memberOf PrintCreditDocumentSearchFilterController
     */
    static $inject = [
      'ContentTypeStore',
      'BrandStore',
      'SelectCompaniesStore',
      'PrintCreditDocumentStore',
      'CacheService',
      '$log',
      'DispatcherService',
      'DateUtilService',
      '$scope'
    ];

    /**
     * Creates an instance of PrintCreditDocumentSearchFilterController.
     *
     * @param {IContentTypeStore} ContentTypeStore
     * @param {IBrandStore} BrandStore
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {ICreditDocumentStore} CreditDocumentStore
     * @param {ICacheService} CacheService
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} DispatcherService
     * @param {IDateUtilService} DateUtilService
     * @param {ng.IScope} $scope
     *
     * @memberOf PrintCreditDocumentSearchFilterController
     */
    constructor(
      private ContentTypeStore: IContentTypeStore,
      private BrandStore: IBrandStore,
      private SelectCompaniesStore: ISelectCompaniesStore,
      private CreditDocumentStore: ICreditDocumentStore,
      private CacheService: ICacheService,
      private $log: ng.ILogService,
      private DispatcherService: IDispatcherService,
      private DateUtilService: IDateUtilService,
      private $scope: ng.IScope
    ) {

      var that = this;
      that
        .ContentTypeStore
        .getContentTypes()
        .then(function (CTdata: Array < ContentTypeModel >) {
          CTdata.unshift({
            'key': '',
            'displayText': 'Both'
          });
          that.contenttypes = CTdata;
        });
      that
        .BrandStore
        .getBrandList(targetService)
        .then(function (BrandList: Array < BrandModel >) {
          that.brandlist = BrandList;
        });
      that
        .SelectCompaniesStore
        .getSelectCompanies()
        .then(function (selectCompanies: Array < {} >) {
        if (selectCompanies !== null && selectCompanies !== undefined) {
          that.selectCompanies = selectCompanies;
        }
      });
      that.sortCreditDocumentsSubscription = that.DispatcherService.subscribe('printcreditdocument:SortCreditDocuments',
        that.sortCreditDocumentsHandler.bind(this));

      that.cachedCreditDocumentsSubscription = that.DispatcherService.subscribe('printcreditdocument:SearchCreditDocumentsCached',
        that.cachedCreditDocumentsHandler.bind(this));

      that.switchPageCreditDocumentsSubscription = that.DispatcherService.subscribe('printcreditdocument:SwitchPageCreditDocuments',
        that.switchPageCreditDocumentsSortHandler.bind(this));

      /**
       * Get initial company (used in translation keys)
       * */
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      // that.DealerPattern = that.selectedCompany !== '4' ? /^\d+$/ : /^[a-zA-Z0-9]+$/;
      that.DealerPattern = /^[a-zA-Z0-9]+$/;

      that.$scope.$on('$stateChangeStart', function () {
        that.sortCreditDocumentsSubscription.unsubscribe();
        that.cachedCreditDocumentsSubscription.unsubscribe();
        that.switchPageCreditDocumentsSubscription.unsubscribe();
        that.CacheService.put(that.CACHE_KEY_PREFIX, {
          form: that.creditDocumentForm,
          gridState: that.gridState
        });
      });
      // Look for cached data and recreate state, else init empty
      if (that.CacheService.get(that.CACHE_KEY_PREFIX)) {
        var cachedData = that.CacheService.get(that.CACHE_KEY_PREFIX);
        that.gridState = cachedData.gridState;
        that.creditDocumentForm = cachedData.form;
      } else {
        that.creditDocumentForm = {
          'companyno': this.selectedCompany,
          'importerno': null,
          'dealerno': null,
          'creditdocumentno': null,
          'selectedbrandkeyval': '-1',
          'creditingdatefrom': null,
          'creditingdateto': null,
          'selectmimetypekeyval': ''
        };
      }
      // Look for cached result and recreate state, else init empty
      if (that.CacheService.get('CreditDocumentStore.creditingDocuments')) {
        var gridState = that.gridState;
        that.SearchCreditDocuments(gridState.pageSize, gridState.pageNumber, gridState.sort, gridState.orderBy, false);
      } else {
        that.creditDocuments = [];
      }
    }

    /**
     *
     *
     *
     * @memberOf PrintCreditDocumentSearchFilterController
     */
    datePickerOptions = this.DateUtilService.getDatePickerOptions();

    /**
     *
     *
     *
     * @memberOf PrintCreditDocumentSearchFilterController
     */
    OnlyNumbers = /^\d+$/;

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf PrintCreditDocumentSearchFilterController
     */
    sortCreditDocumentsHandler(data) {
      this.SearchCreditDocuments(data.pageSize, data.pageNumber, data.sort, data.sortColumn, true);
    }

    // TODO: refactor to solve this weird rendering race condition
    /**
     *
     *
     * @param {any} data
     *
     * @memberOf PrintCreditDocumentSearchFilterController
     */
    cachedCreditDocumentsHandler(data) {
      var that = this;
      setTimeout(function () {
        that.callBack(data);
      }, 300);
    }

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf PrintCreditDocumentSearchFilterController
     */
    callBack(data) {
      this.DispatcherService.publish('printcreditdocument:SearchCreditDocuments', data);
    }

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf PrintCreditDocumentSearchFilterController
     */
    switchPageCreditDocumentsSortHandler(data) {
      this.SearchCreditDocuments(data.pageSize, data.pageNumber, null, null, true);
    }

    /**
     *
     *
     * @param {any} pageSize
     * @param {any} pageNumber
     * @param {any} sort
     * @param {any} orderBy
     * @param {any} noCache
     *
     * @memberOf PrintCreditDocumentSearchFilterController
     */
    SearchCreditDocuments(pageSize, pageNumber, sort, orderBy, noCache) {
      var that = this;
      that.gridState = {
        pageSize: pageSize,
        pageNumber: pageNumber,
        sort: sort,
        orderBy: orderBy
      };
      that.creditDocumentsData = {
        importerNo: that.creditDocumentForm.importerno,
        dealerNo: that.creditDocumentForm.dealerno,
        creditDocNo: that.creditDocumentForm.creditdocumentno,
        make: that.creditDocumentForm.selectedbrandkeyval,
        creditingDateFrom: that.DateUtilService.DateParse(that.creditDocumentForm.creditingdatefrom),
        creditingDateTo: that.DateUtilService.DateParse(that.creditDocumentForm.creditingdateto),
        mimeType: that.creditDocumentForm.selectmimetypekeyval,
        descendingSortOrder: sort,
        numberOfRows: pageSize || 10,
        startIndex: ((pageNumber - 1) * pageSize) + 1 || 1,
        orderBy: orderBy || null
      };
      that.$log.debug(that.creditDocumentsData);
      that.CreditDocumentStore.getCreditDocuments(that.creditDocumentsData, noCache);
    }
  }

  angular
    .module('uchpClientAngularApp')
    .component('printCreditDocumentSearchFilter', {
      templateUrl : 'components/crediting/print-credit-document/' +
      'print-credit-document-search-filter/print-credit-document-search-filter.html',
      controller  : [
        'ContentTypeStore',
        'BrandStore',
        'SelectCompaniesStore',
        'CreditDocumentStore',
        'CacheService',
        '$log',
        'DispatcherService',
        'DateUtilService',
        '$scope',
        PrintCreditDocumentSearchFilterController
      ],
      controllerAs: 'ctrl'
    });
}
