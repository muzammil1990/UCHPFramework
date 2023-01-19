/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class ViewCampaignSearchFilterController {
    private viewCampaignFormData: Object = null;
    private viewCampaignResult: Array < SearchVehicleCampaignListModel >;
    private companyChangeSubscription: any;
    private selectedCompany: String;
    private selectCompanies: Array < any >;
    private isVpcOrVce: boolean = false;
    private isVce: boolean = false;
    private chassiSerieMaxlength: string;
    private chassiNoMaxlength: string;

    static $inject = [
      'ViewCampaignStore',
      'SelectCompaniesStore',
      '$log',
      'DispatcherService',
      '$scope',
      '$rootScope'

    ];

    constructor(
      private ViewCampaignStore: IViewCampaignStore,
      private SelectCompaniesStore: ISelectCompaniesStore,
      private $log: ng.ILogService,
      private DispatcherService: IDispatcherService,
      private $scope: ng.IScope,
      private $rootScope: ng.IScope
    ) {
      var that = this;
      that.$log.debug('ViewCampaignController Initialized');

      that
        .SelectCompaniesStore
        .getSelectCompanies()
        .then(function (selectCompanies: Array < {} >) {
          if (selectCompanies !== null && selectCompanies !== undefined) {
            that.selectCompanies = selectCompanies;
          }
        });

      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      that.isVce = that.selectedCompany === '3';
      that.isVpcOrVce = that.isVce || that.selectedCompany === '6' || false;
      that.chassiSerieMaxlength = that.isVce ? '17' : '5';
      that.chassiNoMaxlength = that.isVce ? '8' : '6';
      that.companyChangeSubscription = that.DispatcherService.subscribe(
        'change:CompanyChanged',
        that.CompanyChangeHandler.bind(this));

      that.$scope.$on('$stateChangeStart', function () {
        that.companyChangeSubscription.unsubscribe();
      });

    }


    viewCampaignForm = {
      'registrationno': null,
      'chassisno': null,
      'chassisseries': null,
      'vehiclevin': null
    };

    CompanyChangeHandler(company): any {
      this.selectedCompany = company;
      console.log('Company Number ::: ' + this.selectedCompany);
    }

    OnlyNumbers = /^\d+$/;

    AlphaNumeric = /^[a-zA-Z0-9]+$/;

    SearchViewCampaign() {
      var that = this;

      that.viewCampaignFormData = {
        regNo: that.viewCampaignForm.registrationno,
        chassisNo: that.viewCampaignForm.chassisno,
        chassisSeries: that.viewCampaignForm.chassisseries,
        vin: that.viewCampaignForm.vehiclevin,
        descendingSortOrder: false,
        numberOfRows: 25,
        startIndex: 1,
        orderBy: null
      };
      that.$log.debug(that.viewCampaignFormData);
      that.ViewCampaignStore.getViewCampaign(that.viewCampaignFormData)
        .then(function (RESdata: Array < SearchVehicleCampaignListModel >) {
          that.viewCampaignResult = RESdata;
        });
    }


  }

  angular.module('uchpClientAngularApp')
    .component('viewCampaignSearchFilter', {
      templateUrl: 'components/claim/vehicle/view-campaign/view-campaign-search-filter/view-campaign-search-filter.html',
      controller: [
        'ViewCampaignStore',
        'SelectCompaniesStore',
        '$log',
        'DispatcherService',
        '$scope',
        '$rootScope',
        ViewCampaignSearchFilterController ],
      controllerAs: 'ctrl'
    });
}
