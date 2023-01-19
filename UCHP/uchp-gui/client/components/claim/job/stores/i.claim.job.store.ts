/// <reference path="../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @export
   * @interface IClaimJobStore
   */
  export interface IClaimJobStore {
    /**
     *
     *
     * @param {any} searchData
     * @returns {*}
     *
     * @memberOf IClaimJobStore
     */
    getClaimJob(searchData): any;
    /**
     *
     *
     * @returns {*}
     *
     * @memberOf IClaimJobStore
     */
    getClaimParams(): any;
    /**
     *
     *
     * @returns {*}
     *
     * @memberOf IClaimJobStore
     */
    getOperationFormat(): any;
    /**
     *
     *
     * @param {any} formData
     * @returns {*}
     *
     * @memberOf IClaimJobStore
     */
    saveClaimJob(formData: any): any;
    /**
     *
     *
     * @param {any} formData
     * @returns {*}
     *
     * @memberOf IClaimJobStore
     */
    releaseClaimJob(formData: any): any;
    /**
     *
     *
     * @param {any} formData
     * @returns {*}
     *
     * @memberOf IClaimJobStore
     */
    deleteClaimJob(): any;
  }
}
