/// <reference path="../../../../app/_all.ts" />


module uchpClientAngularApp {
  'use strict';

  class FollowUpCodesStore implements IFollowUpCodesStore {
    private followUpCodesGetUrl: string;
    private followUpCodesCacheKey: string;

    static $inject = [
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log'
    ];

    constructor(private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService

    ) {
      var that = this;
      that.followUpCodesCacheKey = 'FollowUpCodesStore.followupcodes';
      that.followUpCodesGetUrl = '/claim/manageclaim/followupcodes';
      that.$log.debug('FollowUpCodesStore Initialized');
    }

    getFollowUpCodes(searchData: any): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var cachedData: any = null;
      if (!cachedData) {
        that.RESTAdapterService
          .get(that.followUpCodesGetUrl, {
            params: {
              companyNo: searchData.companyNo,
              typeOfConcern: searchData.typeOfConcern,
              importerNo: searchData.importerNo
            }
          })
          .then((result: any) => {
            var followUpCodes: Array<any> = result.followUpCodeDTOList;
            that.CacheService.put(that.followUpCodesCacheKey, followUpCodes);
            deferred.resolve(followUpCodes);
          }, (err) => {
            deferred.reject('Error getting the follow up codes: ' + err);
          });
      } else {
        deferred.resolve(cachedData);
      }
      return deferred.promise;
    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('FollowUpCodesStore', FollowUpCodesStore);
}
