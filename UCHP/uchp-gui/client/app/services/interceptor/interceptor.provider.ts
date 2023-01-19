/**
 * Created by a232625 on 2016-07-18.
 */

/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';
  class AuthInterceptorServiceProvider implements ng.IServiceProvider {
    /**
     * Creates an instance of AuthInterceptorServiceProvider.
     * 
     * 
     * @memberOf AuthInterceptorServiceProvider
     */
    constructor() {
      this.$get.$inject = [
        '$q',
        'SelectCompaniesStore',
        '$cookies',
        '$injector',
        'UtilService',
        '$log'
      ];
    }

    /**
     * 
     * 
     * @param {ng.IQService} $q
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {ng.cookies.ICookiesService} $cookies
     * @param {angular.auto.IInjectorService} $injector
     * @param {IUtilService} UtilService
     * @param {ng.ILogService} $log
     * @returns {IAuthInterceptorService}
     * 
     * @memberOf AuthInterceptorServiceProvider
     */
    $get($q: ng.IQService,
         SelectCompaniesStore: ISelectCompaniesStore,
         $cookies: ng.cookies.ICookiesService,
         $injector: angular.auto.IInjectorService,
         UtilService: IUtilService,
         $log: ng.ILogService): IAuthInterceptorService {
      return new AuthInterceptorService($q, SelectCompaniesStore, $cookies, $injector, UtilService, $log);
    }

  }

  angular
    .module('uchpClientAngularApp')
    .provider('AuthInterceptorService', AuthInterceptorServiceProvider);

}
