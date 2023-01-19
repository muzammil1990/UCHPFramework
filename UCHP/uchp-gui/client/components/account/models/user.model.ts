/**
 * Created by a232625 on 2016-07-18.
 */
///<reference path="../../../app/_all.ts" />

module uchpClientAngularApp {

  'use strict';

  /**
   * 
   * 
   * @export
   * @class User
   */
  export class User {

    /**
     * Creates an instance of User.
     * 
     * @param {String} userName
     * @param {String} firstName
     * @param {String} lastName
     * @param {String} password
     * @param {String} email
     * @param {[any]} typeOfConcernList
     * @param {[any]} usersetting
     * @param {[any]} privilegeList
     * @param {[any]} roleList
     * 
     * @memberOf User
     */
    constructor(public userName: String,
                public firstName: String,
                public lastName: String,
                public password: String,
                public email: String,
                public typeOfConcernList: [any],
                public usersetting: [any],
                public privilegeList: [any],
                public roleList: [any]) {
    }

  }
}
