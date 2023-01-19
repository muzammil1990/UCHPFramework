/// <reference path="../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';
  var targetService = 'printCreditDocument';

  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class PrintOutstandingClaimSummarySearchFilterController {
    private brandlists: Array <BrandModel>;
    private sortCreditDocumentsSubscription: any;
    private switchPageCreditDocumentsSubscription: any;
    private searchData: Object = null;
    private selectedCompany: String;
    private selectCompanies: Array < any >;
    private DealerPattern: any;

    /**
     *
     *
     * @static
     *
     * @memberOf PrintOutstandingClaimSummarySearchFilterController
     */
    static $inject = [
      'BrandStore',
      'OutstandingClaimSummaryDocumentStore',
      'SelectCompaniesStore',
      '$log',
      'DispatcherService',
      'DateUtilService',
      '$scope'
    ];

    /**
     * Creates an instance of PrintOutstandingClaimSummarySearchFilterController.
     *
     * @param {IBrandStore} BrandStore
     * @param {IOutstandingClaimSummaryDocumentStore} OutstandingClaimSummaryDocumentStore
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} DispatcherService
     * @param {IDateUtilService} DateUtilService
     * @param {ng.IScope} $scope
     *
     * @memberOf PrintOutstandingClaimSummarySearchFilterController
     */
    constructor(
      private BrandStore: IBrandStore,
      private OutstandingClaimSummaryDocumentStore: IOutstandingClaimSummaryDocumentStore,
      private SelectCompaniesStore: ISelectCompaniesStore,
      private $log: ng.ILogService,
      private DispatcherService: IDispatcherService,
      private DateUtilService: IDateUtilService,
      private $scope: ng.IScope
    ) {

      var that = this;

      that
        .BrandStore
        .getBrandList(targetService)
        .then(function (BrandList: Array < BrandModel >) {
          that.brandlists = BrandList;
        });
      /**
       * Get initial company (used in translation keys)
       * */
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      that.DealerPattern = /^[a-zA-Z0-9]+$/;
      that
        .SelectCompaniesStore
        .getSelectCompanies()
        .then(function (selectCompanies: Array < {} >) {
          if (selectCompanies !== null && selectCompanies !== undefined) {
            that.selectCompanies = selectCompanies;
          }
        });
      that.sortCreditDocumentsSubscription = that.DispatcherService.subscribe('printoutstandingclaim:SortCreditDocuments',
        that.sortCreditDocumentsHandler.bind(this));

      that.switchPageCreditDocumentsSubscription = that.DispatcherService.subscribe('printoutstandingclaim:SwitchPageCreditDocuments',
        that.switchPageCreditDocumentsSortHandler.bind(this));

      that.$scope.$on('$stateChangeStart', function () {
        that.sortCreditDocumentsSubscription.unsubscribe();
        that.switchPageCreditDocumentsSubscription.unsubscribe();
      });
    }

    datePickerOptions = this.DateUtilService.getDatePickerOptions();

    // a model to keep the form values
    // implement cache strategy later?
    /**
     *
     *
     *
     * @memberOf PrintOutstandingClaimSummarySearchFilterController
     */
    outstandingClaimSummaryDocumentForm = {
      'importerno': '',
      'dealerno': '',
      'creditdocumentno': '',
      'selectedbrandkeyval': '-1',
      'creationdatefrom': null,
      'creationdateto': null
    };

    /**
     *
     *
     * @param {any} company
     * @returns {*}
     *
     * @memberOf PrintOutstandingClaimSummarySearchFilterController
     */
    CompanyChangeHandler(company): any {
      this.selectedCompany = company;
    }

    /**
     *
     *
     *
     * @memberOf PrintOutstandingClaimSummarySearchFilterController
     */
    OnlyNumbers = /^\d+$/;

    /**
     *
     *
     * @param {any} pageSize
     * @param {any} pageNumber
     * @param {any} sort
     * @param {any} orderBy
     *
     * @memberOf PrintOutstandingClaimSummarySearchFilterController
     */
    SearchOutstandingClaimSummaryDocuments(pageSize, pageNumber, sort, orderBy) {
      var that = this;
      that.searchData = {
        companyNo: that.selectedCompany,
        importerNo: that.outstandingClaimSummaryDocumentForm.importerno,
        dealerNo: that.outstandingClaimSummaryDocumentForm.dealerno,
        creditDocNo: that.outstandingClaimSummaryDocumentForm.creditdocumentno,
        make: that.outstandingClaimSummaryDocumentForm.selectedbrandkeyval,
        creationDateFrom: that.DateUtilService.DateParse(that.outstandingClaimSummaryDocumentForm.creationdatefrom),
        creationDateTo: that.DateUtilService.DateParse(that.outstandingClaimSummaryDocumentForm.creationdateto),
        descendingSortOrder: sort,
        numberOfRows: pageSize || 10,
        startIndex: ((pageNumber - 1) * pageSize) + 1 || 1,
        orderBy: orderBy || null
      };
      that.OutstandingClaimSummaryDocumentStore.getOutstandingClaimSummaryDocuments(that.searchData);
    }

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf PrintOutstandingClaimSummarySearchFilterController
     */
    sortCreditDocumentsHandler(data) {
      this.SearchOutstandingClaimSummaryDocuments(data.pageSize, data.pageNumber, data.sort, data.sortColumn);
    }

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf PrintOutstandingClaimSummarySearchFilterController
     */
    switchPageCreditDocumentsSortHandler(data) {
      this.SearchOutstandingClaimSummaryDocuments(data.pageSize, data.pageNumber, null, null);
    }
  }

  angular
    .module('uchpClientAngularApp')
    .component('printOutstandingClaimSummarySearchFilter', {
      templateUrl : 'components/crediting/print-outstanding-claim-summary/' +
      'print-outstanding-claim-summary-search-filter/print-outstanding-claim-summary-search-filter.html',
      controller  : [
        'BrandStore',
        'OutstandingClaimSummaryDocumentStore',
        'SelectCompaniesStore',
        '$log',
        'DispatcherService',
        'DateUtilService',
        '$scope',
        PrintOutstandingClaimSummarySearchFilterController
      ],
      controllerAs: 'ctrl'
    });
}
