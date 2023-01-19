module uchpClientAngularApp {
  'use strict';

  class ClaimJobStore implements IClaimJobStore {
    private claimJobGetExistingClaimUrl: string;
    private claimJobCacheKey: string;
    private claimParams: any;
    private operationFormat: number;
    private saveClaimJobUrl: string;
    private releaseClaimJobUrl: string;
    private deleteClaimJobUrl: string;

    static $inject = [
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService'
    ];

    constructor(private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService,
                private DispatcherService: IDispatcherService

    ) {
      var that = this;
      that.claimJobCacheKey = 'ClaimJobStore.claimJobs'; // TODO: if use cache add tab param to cachekey
      that.claimJobGetExistingClaimUrl = '/claim/manageclaim/existingclaim/'; // '/claim/manageclaim/existingclaim/1/1/1';
      that.saveClaimJobUrl = '/claim/manageclaim/saveclaimjob';
      that.releaseClaimJobUrl = '/claim/manageclaim/releaseclaim';
      that.deleteClaimJobUrl = '/claim/manageclaim/deleteclaim'; // /{companyNo}/{claimRepairHeaderId}/{claimJobId}
      that.$log.debug('ClaimJobStore Initialized');
    }

    getClaimJob(searchData: any): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var cachedData: any = null;
      var queryUrl = that.claimJobGetExistingClaimUrl;
      var params: any = {};
      if (searchData) {
        queryUrl = queryUrl + searchData.companyNo + '/';
        queryUrl = queryUrl + searchData.claimRepairHeaderId + '/';
        queryUrl = queryUrl + searchData.claimJobId;
      }
      if (!cachedData) {
        that.RESTAdapterService
          .get(queryUrl, {
            params: params
          }).then((claimJob: any) => {
          that.CacheService.put(that.claimJobCacheKey, claimJob);
          deferred.resolve(claimJob);
          that.setOperationFormat(claimJob.claimRepairHeaderDTO.activeClaimJobDTO);
          that.setClaimParams(claimJob.claimRepairHeaderDTO);
          that.DispatcherService.publish('claimJob:getClaimJob', claimJob);
        }, (err) => {
          deferred.reject('Error getting the Claim job: ' + err);
        });
      } else {
        deferred.resolve(cachedData);
        that.setOperationFormat(cachedData.claimRepairHeaderDTO.activeClaimJobDTO);
        that.setClaimParams(cachedData.claimRepairHeaderDTO);
        that.DispatcherService.publish('claimJob:getClaimJob', cachedData);
      }
      return deferred.promise;
    }

    saveClaimJob(formData: any): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var queryUrl = that.saveClaimJobUrl;
      var params: any = {};
      that.RESTAdapterService
        .post(queryUrl, {
          params: params
        },
          formData
        ).then((claimJob: any) => {
        that.CacheService.put(that.claimJobCacheKey, claimJob);
        deferred.resolve(claimJob);
        that.setOperationFormat(claimJob.claimRepairHeaderDTO.activeClaimJobDTO);
        that.setClaimParams(claimJob.claimRepairHeaderDTO);
        that.DispatcherService.publish('claimJob:getClaimJob', claimJob);
        that.DispatcherService.publish('claimJob:Saved', {});
      }, (err) => {
        deferred.reject('Error getting the Claim job: ' + err);
      });
      return deferred.promise;
    }

    releaseClaimJob(formData: any): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var queryUrl = that.releaseClaimJobUrl;
      var params: any = {};
      that.RESTAdapterService
        .post(queryUrl, {
            params: params
          },
          formData
        ).then((claimJob: any) => {
        that.CacheService.put(that.claimJobCacheKey, claimJob);
        deferred.resolve(claimJob);
        that.setOperationFormat(claimJob.claimRepairHeaderDTO.activeClaimJobDTO);
        that.setClaimParams(claimJob.claimRepairHeaderDTO);
        that.DispatcherService.publish('claimJob:getClaimJob', claimJob);
        that.DispatcherService.publish('claimJob:Released', {});
      }, (err) => {
        deferred.reject('Error getting the Claim job: ' + err);
      });
      return deferred.promise;
    }

    deleteClaimJob(): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var queryUrl = that.deleteClaimJobUrl;
      var path = ['/',
        that.claimParams.companyNo,
        '/',
        that.claimParams.claimRepairHeaderId,
        '/',
        that.claimParams.claimJobId
      ].join('');
      var params: any = {};
      that.RESTAdapterService
        .delete(queryUrl + path, {
            params: params
          }
        ).then((claimJob: any) => {
        that.DispatcherService.publish('claimJob:deleted', {});
      }, (err) => {
        deferred.reject('Error deleting the Claim job: ' + err);
      });
      return deferred.promise;
    }

    getClaimParams() {
      return this.claimParams;
    }

    setClaimParams(data) {
      return this.claimParams =  {
        companyNo: data.companyNo,
        claimRepairHeaderVersion: data.activeClaimJobDTO.version,
        claimRepairHeaderId: data.claimHeaderId,
        claimJobId: data.activeClaimJobDTO.claimJobId,
        repairingImporter: data.repairingImporter,
        repairingDealer: data.repairingDealer
      };
    }

    getOperationFormat() {
      return this.operationFormat;
    }

    setOperationFormat(data) {
      return this.operationFormat = data.operationFormat;
    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('ClaimJobStore', ClaimJobStore);
}
