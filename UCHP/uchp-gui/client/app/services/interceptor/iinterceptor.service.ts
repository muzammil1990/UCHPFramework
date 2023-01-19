/**
 * Created by a232625 on 2016-07-21.
 */
/// <reference path="../../_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IAuthInterceptorService
   */
  export interface IAuthInterceptorService {
    /**
     * 
     * 
     * @param {ng.IRequestConfig} config
     * @returns {ng.IRequestConfig}
     * 
     * @memberOf IAuthInterceptorService
     */
    request(config: ng.IRequestConfig): ng.IRequestConfig;
    /**
     * 
     * 
     * @param {*} response
     * @returns {*}
     * 
     * @memberOf IAuthInterceptorService
     */
    responseError(response: any): any;
  }

}
