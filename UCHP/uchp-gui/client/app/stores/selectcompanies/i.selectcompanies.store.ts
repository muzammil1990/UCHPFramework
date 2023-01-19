/// <reference path="../../_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface ISelectCompaniesStore
   */
  export interface ISelectCompaniesStore {
    /**
     * 
     * 
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf ISelectCompaniesStore
     */
    getSelectCompanies(): ng.IPromise<{}>;
    /**
     * 
     * 
     * @returns {String}
     * 
     * @memberOf ISelectCompaniesStore
     */
    getSelectedCompany(): String;
  }

}
