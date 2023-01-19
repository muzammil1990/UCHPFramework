/**
 * Created by a232625 on 2017-01-20.
 */
/// <reference path="../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @export
   * @interface IOperationListsStore
   */
  export interface IOperationListsStore {
    /**
     *
     *
     * @param {*} searchData
     * @returns {ng.IPromise <{ }>}
     *
     * @memberOf IOperationListsStore
     */
    getOperationLists(params: any): ng.IPromise <{ }>;
  }
}
