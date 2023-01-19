/**
 * Created by a148094 on 27-04-2016.
 */
/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class ContentTypeStore implements IContentTypeStore {
    private contenttypeUrl: string;
    private contenttypeCacheKey: string;

    /**
     * 
     * 
     * @static
     * 
     * @memberOf ContentTypeStore
     */
    static $inject = [
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log'
    ];

    /**
     * Creates an instance of ContentTypeStore.
     * 
     * @param {IRESTAdapterService} RESTAdapterService
     * @param {ICacheService} CacheService
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * 
     * @memberOf ContentTypeStore
     */
    constructor(
      private RESTAdapterService: IRESTAdapterService,
      private CacheService: ICacheService,
      private $q: ng.IQService,
      private $log: ng.ILogService
    ) {

      this.contenttypeCacheKey = 'ContentTypeStore.contentType';
      this.contenttypeUrl = '/crediting/creditdocuments/contenttype';

      $log.debug('Initializing ContentTypeStore...');
    }

    /**
     * 
     * 
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf ContentTypeStore
     */
    getContentTypes(): ng.IPromise<{}> {
      var deferred = this.$q.defer();
      var that = this;
      //tries to get cached data
      //var cachedContentData = this.CacheService.get(this.contenttypeCacheKey);
      //TODO Cache Service
      var cachedContentData = null;
      //returns data from cache or server
      if (!cachedContentData) {
        this.RESTAdapterService
          .get(this.contenttypeUrl, {
            params: {}
          })
          .then((result: any) => {
            var contenttypedocs: Array<ContentTypeModel> = result.translations;
            that.CacheService.put(that.contenttypeCacheKey, contenttypedocs);
            deferred.resolve(contenttypedocs);
          }, (err) => {
            deferred.reject('Error getting the Content types: ' + err);
          });
      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedContentData);
      }
      return deferred.promise;
    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('ContentTypeStore', ContentTypeStore);
}
