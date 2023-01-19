/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';
  var targetService = 'viewCoverage';

  class ViewCoverageSearchFilterController {
    private brandlist: Array < BrandModel >;
    private viewCoverageData: Object = null;
    private sortViewCoverageDocumentsSubscription: any;
    private switchPageViewCoverageDocumentsSubscription: any;
    private selectedCompany: String;
    private isVpcOrVce: boolean = false;
    private isVce: boolean = false;
    private chassiSerieMaxlength: string;
    private chassiNoMaxlength: string;

    static $inject = [
      'VehicleInformationStore',
      'SelectCompaniesStore',
      'BrandStore',
      '$log',
      'DispatcherService',
      'DateUtilService',
      '$translate',
      '$scope',
      '$filter'
    ];

    constructor(
      private VehicleInformationStore: IVehicleInformationStore,
      private SelectCompaniesStore: ISelectCompaniesStore,
      private BrandStore: IBrandStore,
      private $log: ng.ILogService,
      private DispatcherService: IDispatcherService,
      private DateUtilService: IDateUtilService,
      private $translate: ng.translate.ITranslateService,
      private $scope: ng.IScope,
      private $filter: ng.IFilterDate
    ) {
      var that = this;
      console.log('VehicleSearchFilterController Initialized');
      that.BrandStore
        .getBrandList(targetService)
        .then(function (BrandList: Array < BrandModel >) {
          that.brandlist = BrandList;
          var text = that.$translate.instant('SELECT.BRAND');
          that.brandlist.unshift({
            'key': '',
            'displayText': text
          });
        });

      /**
       * Get initial company (used in translation keys)
       * */
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      that.isVce = that.selectedCompany === '3';
      that.isVpcOrVce = that.isVce || that.selectedCompany === '6' || false;
      that.chassiSerieMaxlength = that.isVce ? '17' : '5';
      that.chassiNoMaxlength = that.isVce ? '8' : '6';

      that.sortViewCoverageDocumentsSubscription = that.DispatcherService.subscribe('printcreditdocument:SortCreditDocuments',
        that.sortViewCoverageDocumentsHandler.bind(this));

      that.switchPageViewCoverageDocumentsSubscription = that.DispatcherService.subscribe('printcreditdocument:SwitchPageCreditDocuments',
        that.switchPageViewCoverageDocumentsSortHandler.bind(this));
    }

    viewCoverageForm = {
      'registrationno': null,
      'marketchassisno': null,
      'chassisseries': null,
      'vehiclevin': null,
      'selectedbrandmarketkeyval': '',
      'marketingtype': null,
      'marketingchassisno': null,
      'selectedbrandunitkeyval': '',
      'brandunitno': null,
      'repairDate': null
    };

    datePickerOptions = this.DateUtilService.getDatePickerOptions();

    OnlyNumbers = /^\d+$/;

    AlphaNumeric = /^[a-zA-Z0-9]+$/;

    sortViewCoverageDocumentsHandler(data) {
      this.SearchViewCoverage(data.pageSize, data.pageNumber, data.sort, data.sortColumn);
    }

    switchPageViewCoverageDocumentsSortHandler(data) {
      this.SearchViewCoverage(data.pageSize, data.pageNumber, null, null);
    }

    SearchViewCoverage(pageSize, pageNumber, sort, orderBy) {
      var that = this;
      that.viewCoverageData = {
        regNo: that.viewCoverageForm.registrationno,
        chassisNo: that.viewCoverageForm.marketchassisno,
        chassisSeries: that.viewCoverageForm.chassisseries,
        vin: that.viewCoverageForm.vehiclevin,
        marketingmake: that.viewCoverageForm.selectedbrandmarketkeyval,
        marketingType: that.viewCoverageForm.marketingtype,
        marketingchassisNo: that.viewCoverageForm.marketingchassisno,
        brandmake: that.viewCoverageForm.selectedbrandunitkeyval,
        unitNo: that.viewCoverageForm.brandunitno,
        descendingSortOrder: sort,
        numberOfRows: pageSize || 25,
        startIndex: ((pageNumber - 1) * pageSize) + 1 || 1,
        orderBy: orderBy || null
      };
     that.VehicleInformationStore.getVehicleInformation(that.viewCoverageData, targetService);
  }
}
  angular.module('uchpClientAngularApp')
    .component('viewCoverageSearchFilter', {
      templateUrl: 'components/claim/vehicle/view-coverage/view-coverage-search-filter/view-coverage-search-filter.html',
      controller: [
        'VehicleInformationStore',
        'SelectCompaniesStore',
        'BrandStore',
        '$log',
        'DispatcherService',
        'DateUtilService',
        '$translate',
        '$scope',
        '$filter',
        ViewCoverageSearchFilterController
      ],
      controllerAs: 'ctrl'
    });
}
