/**
 * Created by a148094 on 27-04-2016.
 */
/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @class TabClaimSearchResultStore
   * @implements {ITabClaimSearchResultStore}
   */
  class TabClaimSearchResultStore implements ITabClaimSearchResultStore {
    /**
     *
     *
     * @private
     * @type {string}
     * @memberOf TabClaimSearchResultStore
     */
    private tabClaimSearchResultUrl: string;
    /**
     *
     *
     * @private
     * @type {string}
     * @memberOf TabClaimSearchResultStore
     */
    private tabClaimSearchResultCacheKey: string;
    /**
     *
     *
     * @private
     * @type {string}
     * @memberOf TabClaimSearchResultStore
     */
    private deleteClaimUrl: string;
    /**
     *
     *
     * @private
     * @type {*}
     * @memberOf TabClaimSearchResultStore
     */
    private currentClaimJob: any;

    /**
     *
     *
     * @static
     *
     * @memberOf TabClaimSearchResultStore
     */
    static $inject = [
      'SelectCompaniesStore',
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService'
    ];

    /**
     * Creates an instance of TabClaimSearchResultStore.
     *
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {IRESTAdapterService} RESTAdapterService
     * @param {ICacheService} CacheService
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} dispatcherService
     *
     * @memberOf TabClaimSearchResultStore
     */
    constructor(private SelectCompaniesStore: ISelectCompaniesStore,
                private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService,
                private dispatcherService: IDispatcherService) {

      this.tabClaimSearchResultCacheKey = 'ClaimSearchCriteriaStore.tabClaimSearchResult';
      this.tabClaimSearchResultUrl = '/claim/claims/tabClaimSearchResult';
      this.deleteClaimUrl = '/claim/claims/deleteclaim/'; // {companyNo}/{claimRepairHeaderId}/{claimJobId};

      $log.debug('Initializing TabClaimSearchResultStore...');
    }

    /**
     *
     *
     * @returns
     *
     * @memberOf TabClaimSearchResultStore
     */
    getCurrentClaimJob() {
      return this.currentClaimJob;
    }

    /**
     *
     *
     * @param {*} claim
     * @returns
     *
     * @memberOf TabClaimSearchResultStore
     */
    deleteCurrentClaimJob(claim: any) {
      var deferred = this.$q.defer();
      var that = this;
      let deleteClaimUrl = that.deleteClaimUrl + claim.companyNo + '/' + claim.claimHeaderId + '/' + claim.claimJobId;
      that.RESTAdapterService
        .delete(deleteClaimUrl, {
          params: {}
        })
        .then(() => {
          that.dispatcherService.publish('listclaimjobs:claimDeleted', {
            success: true,
            index: claim.index
          });
        }, () => {
          that.dispatcherService.publish('listclaimjobs:claimDeleted', {
            success: false
          });
        });
      return deferred.promise;
    }

    /**
     *
     * @param tabjobtype
     * @param pagination
     * @returns {IPromise<T>}
     */
    /**
     *
     *
     * @param {Number} tabjobtype
     * @param {Number} typeOfConcern
     * @param {*} pagination
     * @returns {ng.IPromise < {} >}
     *
     * @memberOf TabClaimSearchResultStore
     */
    getTabClaimSearchResult(tabjobtype: Number, typeOfConcern: Number, pagination: any): ng.IPromise < {} > {
      var deferred = this.$q.defer();
      var that = this;
      var userCompanyNo = that.SelectCompaniesStore.getSelectedCompany();
      // tries to get cached data var cachedclaimsearchcriteriaData =
      // this.CacheService.get(this.tabClaimSearchResultCacheKey);
      //TODO Cache Service
      var cachedclaimsearchcriteriaData = null;
      //returns data from cache or server
      if (!cachedclaimsearchcriteriaData) {
        this.RESTAdapterService
          .get(this.tabClaimSearchResultUrl, {
            params: {
              claimSearchType: tabjobtype,
              companyNo: userCompanyNo,
              typeOfConcern: typeOfConcern,
              descendingSortOrder: pagination && pagination.sort ? pagination.sort : false,
              numberOfRows: pagination && pagination.pageSize ? pagination.pageSize : 10,
              orderBy: pagination && pagination.sortColumn ? pagination.sortColumn : null,
              startIndex: pagination && pagination.pageNumber ? (((pagination.pageNumber - 1) * pagination.pageSize) + 1) : 1
            }
          })
          .then((result: any) => {
            that.CacheService.put(that.tabClaimSearchResultCacheKey, {result: result, search: false});
            deferred.resolve(result);
            that.dispatcherService.publish('listclaimjobs:getTabClaimSearchResult' + tabjobtype, {result: result, search: false});
          }, (err) => {
            deferred.reject('Error getting the Content types: ' + err);
          });
      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedclaimsearchcriteriaData);
        that.dispatcherService.publish('listclaimjobs:getTabClaimSearchResult' + tabjobtype, cachedclaimsearchcriteriaData);
      }
      return deferred.promise;
    }
  }

  angular
    .module('uchpClientAngularApp')
    .service('TabClaimSearchResultStore', TabClaimSearchResultStore);
}
