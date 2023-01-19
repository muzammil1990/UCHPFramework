/**
 * Created by a232625 on 2016-07-18.
 */
module uchpClientAngularApp {
  'use strict';
  /**
   * 
   * 
   * @export
   * @class OAuthProvider
   * @implements {ng.IServiceProvider}
   */
  export class OAuthProvider implements ng.IServiceProvider {
    private helloRef: HelloJSStatic = undefined;

    /**
     * Creates an instance of OAuthProvider.
     * 
     * 
     * @memberOf OAuthProvider
     */
    constructor() {
      this.$get.$inject = [
        '$log'
      ];
    }

    /**
     * 
     * 
     * @param {ng.ILogService} $log
     * @returns {HelloJSStatic}
     * 
     * @memberOf OAuthProvider
     */
    $get($log: ng.ILogService): HelloJSStatic {
      $log.debug('Global HelloJS injected via OAuth custom provider');
      return this.helloRef;
    }

    /**
     * 
     * 
     * @param {*} helloGlobal
     * 
     * @memberOf OAuthProvider
     */
    setHelloRef(helloGlobal: any) {
      this.helloRef = helloGlobal;
    };
  }

  angular
    .module('uchpClientAngularApp')
    .provider('OAuth', OAuthProvider);

}
