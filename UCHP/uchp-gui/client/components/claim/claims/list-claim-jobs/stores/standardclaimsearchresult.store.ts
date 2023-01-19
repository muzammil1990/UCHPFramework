/**
 * Created by a148094 on 27-04-2016.
 */
/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @class StandardClaimSearchResultStore
   * @implements {IStandardClaimSearchResultStore}
   */
  class StandardClaimSearchResultStore implements IStandardClaimSearchResultStore {
    /**
     *
     *
     * @private
     * @type {string}
     * @memberOf StandardClaimSearchResultStore
     */
    private standardClaimSearchResultUrl: string;
    /**
     *
     *
     * @private
     * @type {string}
     * @memberOf StandardClaimSearchResultStore
     */
    private standardClaimSearchResultCacheKey: string;

    /**
     *
     *
     * @static
     *
     * @memberOf StandardClaimSearchResultStore
     */
    static $inject = [
      'SelectCompaniesStore',
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService'
    ];

    /**
     * Creates an instance of StandardClaimSearchResultStore.
     *
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {IRESTAdapterService} RESTAdapterService
     * @param {ICacheService} CacheService
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} dispatcherService
     *
     * @memberOf StandardClaimSearchResultStore
     */
    constructor(
      private SelectCompaniesStore: ISelectCompaniesStore,
      private RESTAdapterService: IRESTAdapterService,
      private CacheService: ICacheService,
      private $q: ng.IQService,
      private $log: ng.ILogService,
      private dispatcherService: IDispatcherService
    ) {

      this.standardClaimSearchResultCacheKey = 'ClaimSearchCriteriaStore.standardClaimSearchResult';
      this.standardClaimSearchResultUrl = '/claim/claims/standardClaimSearchResult';

      $log.debug('Initializing StandardClaimSearchResultStore...');
    }

    /**
     *
     *
     * @param {*} searchFormData
     * @param {*} claimSearchTypeIndex
     * @param {*} pagination
     * @returns {ng.IPromise<{}>}
     *
     * @memberOf StandardClaimSearchResultStore
     */
    getClaimSearchResult(searchFormData: any, claimSearchTypeIndex: any, pagination: any): ng.IPromise<{}> {
      var deferred = this.$q.defer();
      var that = this;
      var userCompanyNo = that.SelectCompaniesStore.getSelectedCompany();
      //tries to get cached data
      // var cachedclaimsearchcriteriaData = this.CacheService.get(this.standardClaimSearchResultCacheKey);
      //TODO Cache Service
      var cachedclaimsearchcriteriaData = null;
      //returns data from cache or server
      if (!cachedclaimsearchcriteriaData) {
        this.RESTAdapterService
          .get(this.standardClaimSearchResultUrl, {
            params: {
              repairingImporter: searchFormData.repairingImporter,
              repairingDealer: searchFormData.repairingDealer,
              companyNo: userCompanyNo,
              refNo: searchFormData.refNo,
              repairDateFrom: searchFormData.repairDateFrom,
              repairDateTo: searchFormData.repairDateTo,
              registrationDateFrom: searchFormData.registrationDateFrom,
              registrationDateTo: searchFormData.registrationDateTo,
              registrationNumber: searchFormData.registrationNumber,
              vin: searchFormData.vin,
              chassisNo: searchFormData.chassisNo,
              chassisSeries: searchFormData.chassisSeries,
              repairOrderNo: searchFormData.repairOrderNo,
              typeOfClaim: searchFormData.typeOfClaim,
              followUpCode: searchFormData.followUpCode,
              causalPartPrefix: searchFormData.causalPartPrefix,
              causalPartNo: searchFormData.causalPartNo,
              functionGroup: searchFormData.functionGroup,
              caseSensitive: searchFormData.caseSensitive,
              claimSearchType: searchFormData.claimSearchType,
              scc: searchFormData.scc,
              typeOfConcern: searchFormData.typeOfConcern,
              appealStatus: searchFormData.appealStatuses,
              miType: searchFormData.miTypes,
              miPrintStatus: searchFormData.miPrintStatuses,
              miPrintStatusDateFrom: searchFormData.miPrintStatusDateFrom,
              miPrintStatusDateTo: searchFormData.miPrintStatusDateTo,
              materialInstrDateFrom: searchFormData.materialInstrDateFrom,
              materialInstrDateTo: searchFormData.materialInstrDateTo,
              descendingSortOrder: pagination && pagination.sort ? pagination.sort : false,
              numberOfRows: pagination && pagination.pageSize ? pagination.pageSize : 10,
              startIndex: pagination && pagination.pageNumber ? (((pagination.pageNumber - 1) * pagination.pageSize) + 1) : 1,
              orderBy: pagination && pagination.sortColumn ? pagination.sortColumn : null
            }
          })
          .then((result: any) => {
            that.CacheService.put(that.standardClaimSearchResultCacheKey, {claimSearchResults: result, search: true});
            deferred.resolve(result);
            // console.log(claimSearchTypeIndex);
            // claimSearchTypeIndex = [claimSearchTypeIndex];
            that.dispatcherService.publish('listclaimjobs:getTabClaimSearchResult' + claimSearchTypeIndex,
              {result: result, search: true});
            that.dispatcherService.publish('listclaimjobs:SearchListClaimJobs',
              {claimSearchTypeIndex: claimSearchTypeIndex});
          }, (err) => {
            deferred.reject('Error getting the Content types: ' + err);
          });
      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedclaimsearchcriteriaData);
        that.dispatcherService.publish('listclaimjobs:getTabClaimSearchResult' + claimSearchTypeIndex,
          cachedclaimsearchcriteriaData);
        that.dispatcherService.publish('listclaimjobs:SearchListClaimJobs',
          {claimSearchTypeIndex: claimSearchTypeIndex});

      }
      return deferred.promise;

    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('StandardClaimSearchResultStore', StandardClaimSearchResultStore);
}


