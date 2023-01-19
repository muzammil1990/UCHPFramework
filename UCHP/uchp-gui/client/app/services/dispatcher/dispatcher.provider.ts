/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';
  class DispatcherServiceProvider implements IDispatcherServiceProvider {
    // TODO postal-R&R not available in Typings yet
    private postalRef: any = undefined;

    /**
     * Creates an instance of DispatcherServiceProvider.
     * 
     * 
     * @memberOf DispatcherServiceProvider
     */
    constructor() {
      this.$get.$inject = ['$q', '$log'];
    }

    /**
     * 
     * 
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * @returns {IDispatcherService}
     * 
     * @memberOf DispatcherServiceProvider
     */
    $get($q: ng.IQService, $log: ng.ILogService): IDispatcherService {
      return new DispatcherService($q, $log, this.postalRef);
    }

    /**
     * 
     * 
     * @param {*} postalGlobal
     * 
     * @memberOf DispatcherServiceProvider
     */
    setPostalRef(postalGlobal: any) {
      this.postalRef = postalGlobal;
    };
  }

  angular
    .module('uchpClientAngularApp')
    .provider('DispatcherService', DispatcherServiceProvider);

}
