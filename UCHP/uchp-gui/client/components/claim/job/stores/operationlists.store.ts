/**
 * Created by a232625 on 2017-01-20.
 */
/// <reference path="../../../../app/_all.ts" />


module uchpClientAngularApp {
  'use strict';

  class OperationListsStore implements IOperationListsStore {
    private operationListsGetUrl: string;
    private operationListsCacheKey: string;

    static $inject = [
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log'
    ];

    constructor(private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService

    ) {
      var that = this;
      that.operationListsCacheKey = 'OperationListsStore.operations';
      that.operationListsGetUrl = '/claim/manageclaim/operations';
      that.$log.debug('OperationListsStore Initialized');
    }

    getOperationLists(params: any): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var cachedData: any = null;
      if (!cachedData) {
        that.RESTAdapterService
          .get(that.operationListsGetUrl, {
            params: params
          })
          .then((result: any) => {
            var operationLists: Array<any> = result.operationSearchResultList;
            that.CacheService.put(that.operationListsCacheKey, operationLists);
            deferred.resolve(operationLists);
          }, (err) => {
            deferred.reject('Error getting the operation lists: ' + err);
          });
      } else {
        deferred.resolve(cachedData);
      }
      return deferred.promise;
    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('OperationListsStore', OperationListsStore);
}
