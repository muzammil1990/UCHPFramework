/// <reference path="../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class RESTAdapterService implements IRESTAdapterService {
    private basePath: string = '/uchp/api/api';

    /**
     * 
     * 
     * @static
     * 
     * @memberOf RESTAdapterService
     */
    static $inject = [
      '$resource',
      '$http',
      '$q',
      '$log',
      'NotificationService'
    ];

    /**
     * Creates an instance of RESTAdapterService.
     * 
     * @param {ng.resource.IResourceService} $resource
     * @param {ng.IHttpService} $http
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * @param {INotificationService} NotificationService
     * 
     * @memberOf RESTAdapterService
     */
    constructor(
      private $resource: ng.resource.IResourceService,
      private $http: ng.IHttpService,
      private $q: ng.IQService,
      private $log: ng.ILogService,
      private NotificationService: INotificationService
    ) {
      $log.debug('Initializing RESTAdapterService...');
    }

    /**
     * 
     * 
     * @param {string} verb
     * @param {string[]} headers
     * @returns {*}
     * 
     * @memberOf RESTAdapterService
     */
    getAction(verb: string, headers: string[]): any {
      switch (verb) {
        case 'get':
          return {'get': {method: 'GET', headers: headers}};
        case 'query':
          return {'query': {method: 'GET', headers: headers, isArray: false, cache: true}};
        case 'put':
          return {'put': {method: 'PUT', headers: headers}};
        case 'save':
          return {'save': {method: 'POST', headers: headers}};
        case 'update':
          return {'update': {method: 'POST', headers: headers}};
        case 'remove':
          return {'delete': {method: 'DELETE', headers: headers}};
      }
    }

    /**
     * 
     * 
     * @param {string} uri
     * @param {*} options
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf RESTAdapterService
     */
    downloadDocument(uri: string, options: any): ng.IPromise<{}> {
      var httpUrl = this.basePath + uri;
      return this.$http.get(httpUrl, options);
    }

    /**
     * 
     * 
     * @param {string} verb
     * @param {string} uri
     * @param {*} data
     * @param {*} options
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf RESTAdapterService
     */
    makeRequest(verb: string, uri: string, data: any, options: any): ng.IPromise<{}> {

      //start with the uri
      var httpUrl = this.basePath + uri;

      verb = verb.toLowerCase();

      //empty options if it's needed
      options = options || {};
      //empty headers if it's needed
      options.headers = options.headers || {};
      //empty params if it's needed
      options.params = options.params || {};
      options.params = _.omitBy(options.params, _.isNil);
      options.params = _.omitBy(options.params, function(a) {
        return a === '';
      });
      var deferredObject = this.$q.defer();

      // define the resource by following the API:
      // $resource(url, [paramDefaults], [actions], options);
      var resource = this.$resource(httpUrl, options.params, this.getAction(verb, options.headers));

      // call the REST API
      // here we need a switch-case verb.match(/post|put/)
      switch (verb) {
        case 'get':
        case 'query':
          resource.get((result: any) => {
            // On success, always send an empty errorMsg to hide earlier error alerts
            this.NotificationService.trigger(null);
            deferredObject.resolve(result);
          }, (errorMsg: any) => {
            this.NotificationService.trigger(errorMsg);
            deferredObject.reject(errorMsg);
          });
          return deferredObject.promise;
        case 'post':
          resource.save(data, (result: any) => {
            deferredObject.resolve(result);
          }, (errorMsg: any) => {
            this.NotificationService.trigger(errorMsg);
            deferredObject.reject(errorMsg);
          });
          return deferredObject.promise;
        /*case 'put':
          resource.put(data, (result:any) => {
            deferredObject.resolve(result);
          }, (errorMsg:any) => {
         this.NotificationService.trigger(errorMsg);
            deferredObject.reject(errorMsg);
          });
          return deferredObject.promise;*/
        case 'delete':
          resource.delete((result: any) => {
            deferredObject.resolve(result);
          }, (errorMsg: any) => {
            this.NotificationService.trigger(errorMsg);
            deferredObject.reject(errorMsg);
          });
          return deferredObject.promise;
      }
    }


    /**
     * 
     * 
     * @param {string} uri
     * @param {*} options
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf RESTAdapterService
     */
    get(uri: string, options: any): ng.IPromise<{}> {
      // use a simple $http service to download documents
      if (options.download) {
        return this.downloadDocument(uri, options);
      } else {
        return this.makeRequest('get', uri, {}, options);
      }
    }

    /**
     * 
     * 
     * @param {string} uri
     * @param {*} options
     * @param {*} data
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf RESTAdapterService
     */
    put(uri: string, options: any, data: any): ng.IPromise<{}> {
      return this.makeRequest('put', uri, data, options);
    }

    /**
     * 
     * 
     * @param {string} uri
     * @param {*} options
     * @param {*} data
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf RESTAdapterService
     */
    post(uri: string, options: any, data: any): ng.IPromise<{}> {
      return this.makeRequest('post', uri, data, options);
    }
    /**
     * 
     * 
     * @param {string} uri
     * @param {*} options
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf RESTAdapterService
     */
    delete(uri: string, options: any): ng.IPromise<{}> {
      return this.makeRequest('delete', uri, {}, options);
    }

  }

  angular.module('uchpClientAngularApp')
    .service('RESTAdapterService', RESTAdapterService);
}
