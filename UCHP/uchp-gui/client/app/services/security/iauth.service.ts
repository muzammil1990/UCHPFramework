/**
 * Created by a232625 on 2016-07-18.
 */
module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IAuthService
   */
  export interface IAuthService {
    /**
     * 
     * 
     * @returns {boolean}
     * 
     * @memberOf IAuthService
     */
    isSignedIn(): boolean;
  }
}
