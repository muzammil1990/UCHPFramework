/**
 * Created by a232625 on 2016-07-18.
 */
/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IPingFederateConfig
   */
  export interface IPingFederateConfig {

    /**
     * 
     * 
     * @param {string} code
     * @returns {string}
     * 
     * @memberOf IPingFederateConfig
     */
    buildUrl(code: string): string;

    /**
     * 
     * 
     * @returns {string}
     * 
     * @memberOf IPingFederateConfig
     */
    buildLogOutUrl(): string;
  }
}
