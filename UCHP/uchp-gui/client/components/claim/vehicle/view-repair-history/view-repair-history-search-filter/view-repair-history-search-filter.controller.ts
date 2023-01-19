/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';
  var targetService = 'viewRepairHistory';

  class ViewRepairHistorySearchFilterController {
    private brandlist: Array < BrandModel >;
    private viewRepairHistoryData: Object = null;
    private vehicleInformations: Array < VehicleInformationModel >;
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
      '$translate',
      '$scope'
    ];

    constructor(
      private VehicleInformationStore: IVehicleInformationStore,
      private SelectCompaniesStore: ISelectCompaniesStore,
      private BrandStore: IBrandStore,
      private $log: ng.ILogService,
      private $translate: ng.translate.ITranslateService,
      private $scope: ng.IScope
    ) {
      var that = this;
      $log.debug('ViewRepairHistorySearchFilterController Initialized');
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

      that.isVpcOrVce = that.selectedCompany === '3' || that.selectedCompany === '6' || false;
      that.isVce = that.selectedCompany === '3';
      that.isVpcOrVce = that.isVce || that.selectedCompany === '6' || false;
      that.chassiSerieMaxlength = that.isVce ? '17' : '5';
      that.chassiNoMaxlength = that.isVce ? '8' : '6';
    }

    viewRepairHistoryForm = {
      'registrationno': null,
      'marketchassisno': null,
      'chassisseries': null,
      'vehiclevin': null,
      'selectedbrandmarketkeyval': '',
      'marketingtype': null,
      'marketingchassisno': null,
      'selectedbrandunitkeyval': '',
      'brandunitno': null
    };

    OnlyNumbers = /^\d+$/;

    AlphaNumeric = /^[a-zA-Z0-9]+$/;

    SearchViewRepairHistory() {
      var that = this;
      that.viewRepairHistoryData = {
        regNo: that.viewRepairHistoryForm.registrationno,
        chassisNo: that.viewRepairHistoryForm.marketchassisno,
        chassisSeries: that.viewRepairHistoryForm.chassisseries,
        vin: that.viewRepairHistoryForm.vehiclevin,
        marketingmake: that.viewRepairHistoryForm.selectedbrandmarketkeyval,
        marketingType: that.viewRepairHistoryForm.marketingtype,
        marketingchassisNo: that.viewRepairHistoryForm.marketingchassisno,
        brandmake: that.viewRepairHistoryForm.selectedbrandunitkeyval,
        unitNo: that.viewRepairHistoryForm.brandunitno
      };
      that.VehicleInformationStore.getVehicleInformation(that.viewRepairHistoryData, targetService)
        .then(function (VehicleInfodata: Array < VehicleInformationModel >) {
          that.vehicleInformations = VehicleInfodata;
        });
    }

  }

  angular.module('uchpClientAngularApp')
    .component('viewRepairHistorySearchFilter', {
      templateUrl: 'components/claim/vehicle/view-repair-history/view-repair-history-search-filter/view-repair-history-search-filter.html',
      controller: [
        'VehicleInformationStore',
        'SelectCompaniesStore',
        'BrandStore',
        '$log',
        '$translate',
        '$scope',
        ViewRepairHistorySearchFilterController ],
      controllerAs: 'ctrl'
    });
}
