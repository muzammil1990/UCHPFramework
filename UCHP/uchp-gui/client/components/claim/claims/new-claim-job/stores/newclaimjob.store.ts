/**
 * Created by a038509 on 21-09-2016.
 */
/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class NewClaimJobStore implements INewClaimJobStore {
    private preparenewclaimjobCacheKey: string;
    private preparenewclaimUrl: string;
    private preparenewclaimJobUrl: string;
    private currentClaimJob: any;

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

      this.preparenewclaimjobCacheKey = 'NewClaimJobStore.newClaimJobData';
      this.preparenewclaimUrl = '/claim/manageclaim/preparenewclaim';
      this.preparenewclaimJobUrl = '/claim/manageclaim/preparenewclaimjob';

      $log.debug('Initializing ClaimSearchCriteriaStore...');
    }

    getCurrentClaimJob() {
      return this.currentClaimJob;
    }

    getNewClaimJobData(company): ng.IPromise<{}> {
      var deferred = this.$q.defer();
      var that = this;
      // tries to get cached data
      // var cachednewclaimjobData = this.CacheService.get(this.preparenewclaimjobCacheKey);
      // TODO Cache Service
      var cachednewclaimjobData = null;
      // returns data from cache or server
      if (!cachednewclaimjobData) {
        this.RESTAdapterService
          .get(that.preparenewclaimUrl, {
            params: {
              'userCompanyNo' : company
            }
          })
          .then((result: NewClaimJobModel) => {
            var claimjobdata = result;
            that.CacheService.put(that.preparenewclaimjobCacheKey, claimjobdata);
            deferred.resolve(claimjobdata);
            that.currentClaimJob = claimjobdata;
          }, (err) => {
            deferred.reject('Error getting the Content types: ' + err);
          });
      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachednewclaimjobData);
      }
      return deferred.promise;
    }

    postNewClaimJobData(selectedClaimJobType: number, searchFormData: any): ng.IPromise <{}> {
      var deferred = this.$q.defer();
      var that = this;
      // post data to server
        that.RESTAdapterService
          .post(that.preparenewclaimJobUrl, {
            params: {
              defaultPartNoPrefix: 'a',
              selectedClaimJobType: selectedClaimJobType
            }
          },
            searchFormData
          )
          .then((result: NewClaimJobModel) => {
            deferred.resolve(result);
          }, (err) => {
            deferred.reject('Error getting the Content types: ' + err);
          });
      return deferred.promise;
    }
  }

  angular
    .module('uchpClientAngularApp')
    .service('NewClaimJobStore', NewClaimJobStore);
}
