/// <reference path="../../_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface ICacheService
   */
  export interface ICacheService {
    /**
     * 
     * 
     * @param {string} key
     * @returns {*}
     * 
     * @memberOf ICacheService
     */
    get(key: string): any;
    /**
     * 
     * 
     * @param {string} key
     * @param {*} value
     * 
     * @memberOf ICacheService
     */
    put(key: string, value: any);

  }

}
