/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IDispatcherServiceProvider
   * @extends {ng.IServiceProvider}
   */
  export interface IDispatcherServiceProvider extends ng.IServiceProvider {
    /**
     * 
     * 
     * @param {*} postalGlobal
     * 
     * @memberOf IDispatcherServiceProvider
     */
    setPostalRef(postalGlobal: any): void;
    /**
     * 
     * 
     * @param {ng.IQService} q
     * @param {ng.ILogService} $log
     * @returns {IDispatcherService}
     * 
     * @memberOf IDispatcherServiceProvider
     */
    $get(q: ng.IQService, $log: ng.ILogService): IDispatcherService;
  }
}
