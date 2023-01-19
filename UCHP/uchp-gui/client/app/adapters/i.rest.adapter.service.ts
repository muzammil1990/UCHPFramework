/// <reference path="../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IRESTAdapterService
   */
  export interface IRESTAdapterService {
    /**
     * 
     * 
     * @param {string} uri
     * @param {*} options
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf IRESTAdapterService
     */
    get(uri: string, options: any): ng.IPromise<{}>;
    /**
     * 
     * 
     * @param {string} uri
     * @param {*} options
     * @param {*} data
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf IRESTAdapterService
     */
    put(uri: string, options: any, data: any): ng.IPromise<{}>;
    /**
     * 
     * 
     * @param {string} uri
     * @param {*} options
     * @param {*} data
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf IRESTAdapterService
     */
    post(uri: string, options: any, data: any): ng.IPromise<{}>;
    /**
     * 
     * 
     * @param {string} uri
     * @param {*} options
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf IRESTAdapterService
     */
    delete(uri: string, options: any): ng.IPromise<{}>;
  }

}
