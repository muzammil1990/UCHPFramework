/**
 * Created by a232625 on 2016-07-18.
 */
///<reference path="../../_all.ts" />

module uchpClientAngularApp {

  'use strict';

  /**
   * 
   * 
   * @export
   * @class AuthInterceptorService
   * @implements {IAuthInterceptorService}
   */
  export class AuthInterceptorService implements IAuthInterceptorService {
    private state: any;

    /**
     * Creates an instance of AuthInterceptorService.
     * 
     * @param {ng.IQService} $q
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {ng.cookies.ICookiesService} $cookies
     * @param {angular.auto.IInjectorService} $injector
     * @param {IUtilService} UtilService
     * @param {ng.ILogService} $log
     * 
     * @memberOf AuthInterceptorService
     */
    constructor(private $q: ng.IQService,
                private SelectCompaniesStore: ISelectCompaniesStore,
                private $cookies: ng.cookies.ICookiesService,
                private $injector: angular.auto.IInjectorService,
                private UtilService: IUtilService,
                private $log: ng.ILogService) {
      $log.debug('Initializing AuthInterceptorService...');
    }

    // Add authorization token to headers
    /**
     * 
     * 
     * 
     * @memberOf AuthInterceptorService
     */
    public request = (config: ng.IRequestConfig): ng.IRequestConfig => {
      var that = this;
      config.headers = config.headers || {};
      let auth_key = 'Authorization';
      let userCompanyNoKey = 'userCompanyNo';
      let userCompanyNo = that.SelectCompaniesStore.getSelectedCompany();
      let temporaryAuth = 'authorization'; //temporary base 64
      config.headers[userCompanyNoKey] = userCompanyNo;
      if (that.$cookies.get('token') && that.UtilService.isSameOrigin(config.url, null)) {
        config.headers[auth_key] = 'Bearer ' + this.$cookies.get('token');
      } else {
        config.headers[temporaryAuth] = 'Basic Y2xhaW1oYW5kbGVyZGVhbGVyOmJhbGRv';
      }
      return config;
    };

    // Intercept 401s and redirect you to login
    /**
     * 
     * 
     * 
     * @memberOf AuthInterceptorService
     */
    public responseError = (response: any): any => {
      if (response.status === 401) {
        (this.state || (this.state = this.$injector.get('$state'))).go('app');
        // remove any stale tokens
        this.$cookies.remove('token');
      }
      return this.$q.reject(response);
    }
  }
}
