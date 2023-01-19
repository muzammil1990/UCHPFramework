/// <reference path="../../../../app/_all.ts" />


module uchpClientAngularApp {
  'use strict';

  class ReturnCodesStore implements IReturnCodesStore {
    private returnCodesGetUrl: string;
    private returnCodesCacheKey: string;

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
      that.returnCodesCacheKey = 'ReturnCodesStore.returncodes';
      that.returnCodesGetUrl = '/claim/manageclaim/returncodes';
      that.$log.debug('ReturnCodesStore Initialized');
    }

    getReturnCodes(searchData: any): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var cachedData: any = null;
      if (!cachedData) {
        that.RESTAdapterService
          .get(that.returnCodesGetUrl, {
            params: {
              companyNo: searchData.companyNo,
              typeOfConcern: searchData.typeOfConcern
            }
          })
          .then((result: any) => {
            var returnCodes: Array<any> = result.returnCodeDTOList;
            that.CacheService.put(that.returnCodesCacheKey, returnCodes);
            deferred.resolve(returnCodes);
          }, (err) => {
            deferred.reject('Error getting the return codes: ' + err);
          });
      } else {
        deferred.resolve(cachedData);
      }
      return deferred.promise;
    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('ReturnCodesStore', ReturnCodesStore);
}
