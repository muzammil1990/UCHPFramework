module uchpClientAngularApp {
  'use strict';

  class ClaimJobItemStore implements IClaimJobItemStore {
    private claimJobGetTabsUrl: string;
    private claimJobCacheKey: string;

    static $inject = [
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService'
    ];

    constructor(private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService,
                private DispatcherService: IDispatcherService

    ) {
      var that = this;
      that.claimJobCacheKey = 'ClaimJobStore.claimJobs'; // TODO: if use cache add tab param to cachekey
      that.claimJobGetTabsUrl = '/claim/manageclaim/existingclaim/'; // '/claim/manageclaim/existingclaim/1/1/1';
      // GET /claim/manageclaim/existingclaim/{headCompanyNo}/{claimRepairHeaderId}/{claimJobId}/{claimRepairHeaderVersion}/tabs/{tabNa
      // https://localhost/uchp/api/api/claim/manageclaim/existingclaim/1/11131046/15547395/8/tabs/InformationTab
      that.$log.debug('ClaimJobStore Initialized');
    }

    getClaimJob(searchData: any, tab: String) {
      var deferred = this.$q.defer();
      var that = this;
      var cachedData: any = null;
      var queryUrl = that.claimJobGetTabsUrl;
      var params: any = {};
      if (searchData) {
        queryUrl = queryUrl + searchData.companyNo + '/';
        queryUrl = queryUrl + searchData.claimRepairHeaderId + '/';
        queryUrl = queryUrl + searchData.claimJobId;
      }
      if (tab) {
        tab = tab + 'Tab';
        queryUrl = queryUrl + '/' + searchData.claimRepairHeaderVersion;
        queryUrl = queryUrl + '/tabs/';
        queryUrl = queryUrl + tab + '/';
        if (_.includes(queryUrl, 'CostTab')) {
          params = {
            repairingImporter: searchData.repairingImporter || 1,
            repairingDealer: searchData.repairingDealer || 1
          };
        }
        if (_.includes(queryUrl, 'RemarksTab')) {
          params.appealStatus = searchData.appealStatus || 1;
        }
      }
      if (!cachedData) {
        that.RESTAdapterService
          .get(queryUrl, {
            params: params
          }).then((result: any) => {
          that.CacheService.put(that.claimJobCacheKey, result);
          deferred.resolve(result);
          that.DispatcherService.publish('claimJob:getClaimJobItem' + tab, result);
        }, (err) => {
          deferred.reject('Error getting the Claim job item: ' + err);
        });

      } else {
        deferred.resolve(cachedData);
        that.DispatcherService.publish('claimJob:getClaimJobItem' + tab, cachedData);
      }
      return deferred.promise;
    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('ClaimJobItemStore', ClaimJobItemStore);
}
