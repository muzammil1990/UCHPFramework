/**
 * Created by a148094 on 11-08-2016.
 */
/// <reference path="../../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @export
   * @interface ITabClaimSearchResultStore
   */
  export interface ITabClaimSearchResultStore {
    /**
     *
     *
     * @param {Number} tabjobtype
     * @param {Number} typeOfConcern
     * @param {*} pagination
     * @returns {ng.IPromise<{}>}
     *
     * @memberOf ITabClaimSearchResultStore
     */
    getTabClaimSearchResult(tabjobtype: Number, typeOfConcern: Number, pagination: any): ng.IPromise<{}>;
    /**
     *
     *
     * @param {*} claim
     * @returns {ng.IPromise<{}>}
     *
     * @memberOf ITabClaimSearchResultStore
     */
    deleteCurrentClaimJob(claim: any): ng.IPromise<{}>;
  }
}
