/**
 * Created by a148094 on 27-04-2016.
 */
/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class ClaimSearchCriteriaStore implements IClaimSearchCriteriaStore {
    private claimsearchcriteriaUrl: string;
    private claimsearchcriteriaCacheKey: string;

    /**
     *
     *
     * @static
     *
     * @memberOf ClaimSearchCriteriaStore
     */
    static $inject = [
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log'
    ];

    /**
     * Creates an instance of ClaimSearchCriteriaStore.
     *
     * @param {IRESTAdapterService} RESTAdapterService
     * @param {ICacheService} CacheService
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     *
     * @memberOf ClaimSearchCriteriaStore
     */
    constructor(
      private RESTAdapterService: IRESTAdapterService,
      private CacheService: ICacheService,
      private $q: ng.IQService,
      private $log: ng.ILogService
    ) {

      this.claimsearchcriteriaCacheKey = 'ClaimSearchCriteriaStore.claimSearchCriteria';
      this.claimsearchcriteriaUrl = '/claim/claims/claimSearchCriteria';

      $log.debug('Initializing ClaimSearchCriteriaStore...');
    }

    /**
     *
     *
     * @returns {ng.IPromise<{}>}
     *
     * @memberOf ClaimSearchCriteriaStore
     */
    getClaimSearchCriteria(): ng.IPromise<{}> {
      var deferred = this.$q.defer();
      var that = this;
      //tries to get cached data
      //var cachedclaimsearchcriteriaData = this.CacheService.get(this.claimsearchcriteriaCacheKey);
      //TODO Cache Service
      var cachedclaimsearchcriteriaData = null;
      //returns data from cache or server
      if (!cachedclaimsearchcriteriaData) {
        this.RESTAdapterService
          .get(this.claimsearchcriteriaUrl, {
            params: {}
          })
          .then((result: any) => {
            var ClaimSearchCriterias: ClaimListSearchCriteriaModel = result;
            that.CacheService.put(that.claimsearchcriteriaCacheKey, ClaimSearchCriterias);
            deferred.resolve(ClaimSearchCriterias);
          }, (err) => {
            deferred.reject('Error getting the Content types: ' + err);
          });
      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedclaimsearchcriteriaData);
      }
      return deferred.promise;
    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('ClaimSearchCriteriaStore', ClaimSearchCriteriaStore);
}
