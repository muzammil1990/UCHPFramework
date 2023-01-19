/**
 * Created by a148094 on 27-04-2016.
 */
module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @export
   * @interface IClaimSearchCriteriaStore
   */
  export interface IClaimSearchCriteriaStore {
    /**
     *
     *
     * @returns {ng.IPromise<{}>}
     *
     * @memberOf IClaimSearchCriteriaStore
     */
    getClaimSearchCriteria(): ng.IPromise<{}>;
  }

}
