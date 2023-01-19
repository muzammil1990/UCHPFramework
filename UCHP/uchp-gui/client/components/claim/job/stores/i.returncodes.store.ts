/// <reference path="../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @export
   * @interface IReturnCodesStore
   */
  export interface IReturnCodesStore {
    /**
     *
     *
     * @param {*} searchData
     * @returns {ng.IPromise <{ }>}
     *
     * @memberOf IReturnCodesStore
     */
    getReturnCodes(searchData: any): ng.IPromise <{ }>;
  }
}
