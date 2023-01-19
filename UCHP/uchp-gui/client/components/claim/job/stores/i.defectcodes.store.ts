/// <reference path="../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @export
   * @interface IDefectCodesStore
   */
  export interface IDefectCodesStore {
    /**
     *
     *
     * @param {*} searchData
     * @returns {ng.IPromise <{ }>}
     *
     * @memberOf IDefectCodesStore
     */
    getDefectCodes(searchData: any): ng.IPromise <{ }>;
  }
}
