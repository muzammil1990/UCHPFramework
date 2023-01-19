/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IDispatcherService
   */
  export interface IDispatcherService {
    /**
     * 
     * 
     * @param {string} topic
     * @param {*} message
     * 
     * @memberOf IDispatcherService
     */
    publish (topic: string, message: any);
    /**
     * 
     * 
     * @param {string} topic
     * @param {ng.IPromise<{}>} callback
     * @returns {*}
     * 
     * @memberOf IDispatcherService
     */
    subscribe (topic: string, callback: ng.IPromise<{}>): any;
    /**
     * 
     * 
     * @param {string} topic
     * @param {*} message
     * 
     * @memberOf IDispatcherService
     */
    publishAndWaitForReply (topic: string, message: any);
  }
}
