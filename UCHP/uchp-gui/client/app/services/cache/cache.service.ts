/// <reference path="../../_all.ts" />
module uchpClientAngularApp {
  'use strict';

  class CacheService implements ICacheService {
    private cache: ng.ICacheObject;

    /**
     * 
     * 
     * @static
     * 
     * @memberOf CacheService
     */
    static $inject = [
      '$cacheFactory',
      '$log'
    ];

    /**
     * Creates an instance of CacheService.
     * 
     * @param {ng.ICacheFactoryService} $cacheFactory
     * @param {ng.ILogService} $log
     * 
     * @memberOf CacheService
     */
    constructor(
        private $cacheFactory: ng.ICacheFactoryService,
        private $log: ng.ILogService
    ) {
      $log.debug('Initializing CacheService...');
      this.cache = $cacheFactory('uchpClientCache');
    }

    /**
     * 
     * 
     * @param {string} key
     * @param {*} value
     * 
     * @memberOf CacheService
     */
    put(key: string, value: any) {
      this.cache.put(key, value);
    }

    /**
     * 
     * 
     * @param {string} key
     * @returns {*}
     * 
     * @memberOf CacheService
     */
    get(key: string): any {
      return this.cache.get(key);
    }
  }
  angular
    .module('uchpClientAngularApp')
    .service('CacheService', CacheService);
}
