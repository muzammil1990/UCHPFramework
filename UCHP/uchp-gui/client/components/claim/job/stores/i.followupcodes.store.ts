/// <reference path="../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @export
   * @interface IFollowUpCodesStore
   */
  export interface IFollowUpCodesStore {
    /**
     *
     *
     * @param {*} searchData
     * @returns {ng.IPromise <{ }>}
     *
     * @memberOf IFollowUpCodesStore
     */
    getFollowUpCodes(searchData: any): ng.IPromise <{ }>;
  }
}
