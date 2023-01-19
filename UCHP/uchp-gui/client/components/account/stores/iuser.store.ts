/**
 * Created by a232625 on 2016-07-19.
 */
/// <reference path="../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IUserStore
   */
  export interface IUserStore {
    /**
     * 
     * 
     * @param {any} companyNumber
     * @returns {ng.IPromise<any>}
     * 
     * @memberOf IUserStore
     */
    setPreferredCompany(companyNumber): ng.IPromise<any>;
    /**
     * 
     * 
     * @param {string} userName
     * @returns {ng.IPromise<User>}
     * 
     * @memberOf IUserStore
     */
    getUserByName(userName: string): ng.IPromise<User>;
    /**
     * 
     * 
     * @returns {ng.IPromise<User>}
     * 
     * @memberOf IUserStore
     */
    getUserByToken(): ng.IPromise<User>;
    /**
     * 
     * 
     * @returns {ng.IPromise<User>}
     * 
     * @memberOf IUserStore
     */
    getUser(): ng.IPromise<User>;
    /**
     * 
     * 
     * @returns {ng.IPromise<Array<User>>}
     * 
     * @memberOf IUserStore
     */
    getAllUsers(): ng.IPromise<Array<User>>;
  }

}
