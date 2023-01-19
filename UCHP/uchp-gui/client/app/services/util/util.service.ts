/**
 * Created by a232625 on 2016-07-18.
 */
/// <reference path="../../_all.ts" />
module uchpClientAngularApp {
  'use strict';
  class UtilService implements IUtilService {

    static $inject = [
      '$window',
      '$log'
    ];

    constructor(private $window: ng.IWindowService,
                private $log: ng.ILogService) {
      $log.debug('Initializing UtilService...');
    }

    /**
     * Return a callback or noop function
     *
     * @param  {Function|*} cb - a 'potential' function
     * @return {Function}
     */
    safeCb(cb: any): any {
      return (angular.isFunction(cb)) ? cb : angular.noop;
    }

    /**
     * Parse a given url with the use of an anchor element
     *
     * @param  {String} url - the url to parse
     * @return {Object}     - the parsed url, anchor element
     */
    urlParse(url: string): HTMLAnchorElement {
      var a = document.createElement('a');
      a.href = url;

      // Special treatment for IE, see http://stackoverflow.com/a/13405933 for details
      if (a.host === '') {
        a.href = a.href;
      }

      return a;
    }

    /**
     * Test whether or not a given url is same origin
     *
     * @param  {String}           url       - url to test
     * @param  {String|String[]}  [origins] - additional origins to test against
     * @return {Boolean}                    - true if url is same origin
     */
    isSameOrigin(url: any, origins: any): boolean {
      url = this.urlParse(url);
      origins = (origins && [].concat(origins)) || [];
      origins.push(this.$window.location.href);
      origins = origins.map(this.urlParse);
      origins = origins.filter(function (o) {
        return url.hostname === o.hostname &&
          url.port === o.port &&
          url.protocol === o.protocol;
      });
      return (origins.length >= 1);
    }
  }


  angular.module('uchpClientAngularApp')
    .service('UtilService', UtilService);


}
