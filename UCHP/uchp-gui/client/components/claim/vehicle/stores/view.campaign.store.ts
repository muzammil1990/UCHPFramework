/// <reference path="../../../../app/_all.ts" />


module uchpClientAngularApp {
  'use strict';

  class ViewCampaignStore implements IViewCampaignStore {
    private searchVehicleCampaignListUrl: string;
    private searchVehicleCampaignListCacheKey: string;

    static $inject = [
      'SelectCompaniesStore',
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService'
    ];

    constructor(private SelectCompaniesStore: ISelectCompaniesStore,
                private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService,
                private dispatcherService: IDispatcherService

    ) {
      this.searchVehicleCampaignListCacheKey = 'ViewCampaignStore.vehicleInformations';
      this.searchVehicleCampaignListUrl = '/claim/vehicle/searchVehicleCampaignList';
    }


    getViewCampaign(searchFormData: any): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var userCompanyNo = that.SelectCompaniesStore.getSelectedCompany();
      //tries to get cached data
      //var cachedData = this.CacheService.get(this.creditDocsCacheKey);
      //TODO Cache Service
      var cachedData: any = null;
      //returns data from cache or server
      if (!cachedData) {
        that.RESTAdapterService
          .get(that.searchVehicleCampaignListUrl, {
            params: {
              companyNo: userCompanyNo,
              regNo: searchFormData.regNo,
              vin: searchFormData.vin,
              chassisSeries: searchFormData.chassisSeries,
              chassisNo: searchFormData.chassisNo,
              descendingSortOrder: false,
              numberOfRows: 25,
              startIndex: 1,
              orderBy: null
            }
          })
          .then((result: any) => {
            var documents: Array < SearchVehicleCampaignListModel > = result.vehicleInformations;
            that.CacheService.put(that.searchVehicleCampaignListCacheKey, documents);
            deferred.resolve(documents);
            that.dispatcherService.publish('searchVehicleCampaignList:SearchVehicleCampaign',
              {vehicleInformationsSearchResults: documents});
          }, (err) => {
            deferred.reject('Error getting the View Campaign SearchResults: ' + err);
          });
      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedData);
        //Publish the results with cached Data 'printcreditdocument:SearchCreditDocuments' to listDocumentController
        that.dispatcherService.publish('searchVehicleCampaignList:SearchVehicleCampaign',
          {vehicleInformationsSearchResults: cachedData});
      }
      //Publish the results with deferred promise 'printcreditdocument:SearchCreditDocuments' to listDocumentController
      that.dispatcherService.publish('searchVehicleCampaignList:SearchVehicleCampaign',
        {vehicleInformationsSearchResults: deferred.promise});
      return deferred.promise;
    }
  }


  angular
    .module('uchpClientAngularApp')
    .service('ViewCampaignStore', ViewCampaignStore);
}
