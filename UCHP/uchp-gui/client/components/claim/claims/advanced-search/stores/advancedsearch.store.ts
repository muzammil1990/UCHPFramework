/**
 * Created by a148094 on 06-01-2017.
 */
/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class AdvancedSearchStore implements IAdvancedSearchStore {
    private advancedsearchUrl: string;
    private advancedsearchCacheKey: string;

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

      this.advancedsearchCacheKey = 'AdvancedSearchStore.advancedSearch';
      this.advancedsearchUrl = '/claim/advancedsearchclaims/advancedSearch';

      $log.debug('Initializing ClaimSearchCriteriaStore...');
    }

    postAdvancedSearchClaimsForm(formData: any): ng.IPromise<{}> {
      console.log('AS STORE...');
      var deferred = this.$q.defer();
      var that = this;
      that.RESTAdapterService
        .post(that.advancedsearchUrl, {
            params: {}
          },
          formData
        )
        .then((result: AdvancedSearchModel) => {
          deferred.resolve(result);
        }, (err) => {
          deferred.reject('Error getting the Content types: ' + err);
        });
      return deferred.promise;
    }
  }

  angular
    .module('uchpClientAngularApp')
    .service('AdvancedSearchStore', AdvancedSearchStore);
}
