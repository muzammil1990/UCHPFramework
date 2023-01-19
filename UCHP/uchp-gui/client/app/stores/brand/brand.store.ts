/**
 * Created by a148094 on 27-04-2016.
 */
/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class BrandStore implements IBrandStore {
    private brandlistUrl: string;
    private viewCoveragebrandlistUrl: string;
    private serviceURL: string;
    private brandlistCacheKey: string;

    /**
     *
     *
     * @static
     *
     * @memberOf BrandStore
     */
    static $inject = [
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService'
    ];

    /**
     * Creates an instance of BrandStore.
     *
     * @param {IRESTAdapterService} RESTAdapterService
     * @param {ICacheService} CacheService
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} DispatcherService
     *
     * @memberOf BrandStore
     */
    constructor(
      private RESTAdapterService: IRESTAdapterService,
      private CacheService: ICacheService,
      private $q: ng.IQService,
      private $log: ng.ILogService,
      private DispatcherService: IDispatcherService
    ) {

      this.brandlistCacheKey = 'BrandStoreService.brandLists';
      this.brandlistUrl = '/crediting/creditdocuments/brandlist';
      this.viewCoveragebrandlistUrl = '/claim/vehicle/brandlist';

      $log.debug('Initializing BrandStoreService...');
    }

    /**
     *
     *
     * @param {any} targetService
     * @returns {ng.IPromise<{}>}
     *
     * @memberOf BrandStore
     */
    getBrandList(targetService): ng.IPromise<{}> {
      if (targetService === 'viewCoverage' || targetService === 'viewRepairHistory') {
        this.serviceURL = this.viewCoveragebrandlistUrl;
      } else if (targetService === 'printCreditDocument' ||
        targetService === 'newClaimJob' || targetService === 'claimJob') {
        this.serviceURL = this.brandlistUrl;
      }
      this.$log.debug('this.serviceURL :: ' + this.serviceURL);
      var deferred = this.$q.defer();
      var that = this;
      //tries to get cached data
      //var cachedBrandData = this.CacheService.get(this.brandlistCacheKey);
      //TODO Cache Service
      var cachedBrandData = null;
      //returns data from cache or server
      if (!cachedBrandData) {
        this.RESTAdapterService
          .get(this.serviceURL, {
            params: {}
          })
          .then((result: any) => {
            var docs: Array<CreditDocumentModel> = result.translations;
            that.CacheService.put(that.brandlistCacheKey, docs);
            deferred.resolve(docs);
            that.DispatcherService.publish('GetBrandList:BrandList', {BrandListResults: docs});
          }, (err) => {
            deferred.reject('Error getting the Brand List: ' + err);
          });
      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedBrandData);
        that.DispatcherService.publish('GetBrandList:BrandList', {BrandListResults: cachedBrandData});
      }
      that.DispatcherService.publish('GetBrandList:BrandList', {BrandListResults: deferred.promise});
      return deferred.promise;
    }
  }
  angular
    .module('uchpClientAngularApp')
    .service('BrandStore', BrandStore);
}
