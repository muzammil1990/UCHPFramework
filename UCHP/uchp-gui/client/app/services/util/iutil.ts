/**
 * Created by a232625 on 2016-07-18.
 */
/// <reference path="../../_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IUtilService
   */
  export interface IUtilService {
    /**
     * 
     * 
     * @param {*} cb
     * @returns {*}
     * 
     * @memberOf IUtilService
     */
    safeCb(cb: any): any;
    /**
     * 
     * 
     * @param {*} url
     * @returns {HTMLAnchorElement}
     * 
     * @memberOf IUtilService
     */
    urlParse(url: any): HTMLAnchorElement;
    /**
     * 
     * 
     * @param {string} url
     * @param {*} origins
     * @returns {boolean}
     * 
     * @memberOf IUtilService
     */
    isSameOrigin(url: string, origins: any): boolean;
  }

}
