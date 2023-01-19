/// <reference path="../../../../app/_all.ts" />


module uchpClientAngularApp {
  'use strict';

  class DefectCodesStore implements IDefectCodesStore {
    private defectCodesGetUrl: string;
    private defectCodesCacheKey: string;

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
      that.defectCodesCacheKey = 'DefectCodesStore.defectcodes';
      that.defectCodesGetUrl = '/claim/manageclaim/defectcodes';
      that.$log.debug('DefectCodesStore Initialized');
    }

    getDefectCodes(searchData: any): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var cachedData: any = null;
      if (!cachedData) {
        that.RESTAdapterService
          .get(that.defectCodesGetUrl, {
            params: {
              companyNo: searchData.companyNo,
              typeOfConcern: searchData.typeOfConcern
            }
          })
          .then((result: any) => {
            var defectCodes: Array<any> = result.defectCodeDTOList;
            that.CacheService.put(that.defectCodesCacheKey, defectCodes);
            deferred.resolve(defectCodes);
          }, (err) => {
            deferred.reject('Error getting the defect codes: ' + err);
          });
      } else {
        deferred.resolve(cachedData);
      }
      return deferred.promise;
    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('DefectCodesStore', DefectCodesStore);
}
