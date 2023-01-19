/**
 * Created by a148094 on 06-01-2017.
 */
/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class AdvancedSearchCriteriaStore implements IAdvancedSearchCriteriaStore {
    private advancedsearchcriteriaUrl: string;
    private advancedsearchcriteriaCacheKey: string;

    static $inject = [
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log'
    ];

    constructor(
      private RESTAdapterService: IRESTAdapterService,
      private CacheService: ICacheService,
      private $q: ng.IQService,
      private $log: ng.ILogService
    ) {

      this.advancedsearchcriteriaCacheKey = 'AdvancedSearchCriteriaStore.advancedSearchCriteria';
      this.advancedsearchcriteriaUrl = '/claim/advancedsearchclaims/advancedSearchCriteria';

      $log.debug('Initializing ClaimSearchCriteriaStore...');
    }

    getAdvancedSearchCriteria(): ng.IPromise<{}> {
      console.log('AS STORE...');
      var deferred = this.$q.defer();
      var that = this;
      //tries to get cached data
      //var cachedclaimsearchcriteriaData = this.CacheService.get(this.claimsearchcriteriaCacheKey);
      //TODO Cache Service
      var cachedadvancedsearchcriteriaData = null;

      //returns data from cache or server
      if (!cachedadvancedsearchcriteriaData) {
        this.RESTAdapterService
          .get(this.advancedsearchcriteriaUrl, {
            params: {}
          })
          .then((result: any) => {
            var advancedsearchcriteriadocs: Array < AdvancedSearchCriteriaModel > = result;
            that.CacheService.put(that.advancedsearchcriteriaCacheKey, advancedsearchcriteriadocs);
            deferred.resolve(advancedsearchcriteriadocs);
          }, (err) => {
            deferred.reject('Error getting the Content types: ' + err);
          });
      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedadvancedsearchcriteriaData);
      }
      return deferred.promise;
    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('AdvancedSearchCriteriaStore', AdvancedSearchCriteriaStore);
}
