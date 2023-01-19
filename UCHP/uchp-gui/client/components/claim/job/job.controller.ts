/**
 * Created by a232625 on 2016-08-01.
 */
/// <reference path="../../../../client/app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class ClaimJobController {
    private searchData: any;
    private selectedTab: any;
    private saveDisabled: boolean;
    private enableSaveSubscription: any;
    private savedSubscription: any;
    private newDisabled: boolean;
    private deleteDisabled: boolean;
    private releaseDisabled: boolean;
    private selectedTypeOfClaim: any;
    private typeOfClaims: any;
    private claimJob: any;
    private existingClaim: any;

    static $inject = [
      '$log',
      'ClaimJobStore',
      'ClaimSearchCriteriaStore',
      'DispatcherService',
      'NewClaimService',
      '$scope',
      '$state',
      '$stateParams',
      '$translate',
      '$translatePartialLoader'
    ];
    /**
     * Creates an instance of ClaimJobController.
     *
     * @param {ng.ILogService} $log
     * @param {IClaimJobStore} ClaimJobStore
     * @param {IClaimSearchCriteriaStore} ClaimSearchCriteriaStore
     * @param {IDispatcherService} DispatcherService
     * @param {INewClaimService} NewClaimService
     * @param {*} $scope
     * @param {*} $state
     * @param {*} $stateParams
     * @param {ng.translate.ITranslateService} $translate
     * @param {ng.translate.ITranslatePartialLoaderProvider} $translatePartialLoaderProvider
     *
     * @memberOf ClaimJobController
     */
    constructor(
      private $log: ng.ILogService,
      private ClaimJobStore: IClaimJobStore,
      private ClaimSearchCriteriaStore: IClaimSearchCriteriaStore,
      private DispatcherService: IDispatcherService,
      private NewClaimService: INewClaimService,
      private $scope: ng.IScope,
      private $state: any,
      private $stateParams: any,
      private $translate: ng.translate.ITranslateService,
      private $translatePartialLoaderProvider: ng.translate.ITranslatePartialLoaderProvider
    ) {
      var that = this;
      that.selectedTab = 1;
      that.selectedTypeOfClaim = '';
      that.typeOfClaims = [];
      that.existingClaim = false;
      that.saveDisabled = true;
      that.newDisabled = true;
      that.deleteDisabled = true;
      that.releaseDisabled = true;
      $translatePartialLoaderProvider.addPart('claim');
      $translate.refresh();
      $log.debug('claimJobController Initialized');
      that.enableSaveSubscription = that.DispatcherService.subscribe('claimJob:SaveEnabled',
        that.enableSaveClaimHandler.bind(this));
      that.savedSubscription = that.DispatcherService.subscribe('claimJob:Saved',
        that.savedClaimHandler.bind(this));
      if ($stateParams) {
        that.searchData = {
          companyNo: $stateParams.companyNo,
          claimRepairHeaderId: $stateParams.claimRepairHeaderId,
          claimJobId: $stateParams.claimJobId
        };
        that.ClaimJobStore.getClaimJob(that.searchData)
          .then(function (claimJob) {
            that.existingClaim = true;
            that.claimJob = _.omit(claimJob, ['$promise', '$resolved']); // TODO.....
            that.selectedTypeOfClaim = that.claimJob.claimRepairHeaderDTO.activeClaimJobDTO.typeOfClaim;
            that.NewClaimService.addExistingClaim(that.claimJob);
          });
      }
      that.ClaimSearchCriteriaStore.getClaimSearchCriteria()
        .then(function (ClaimSearchCriterias: ClaimListSearchCriteriaModel) {
          that.typeOfClaims = ClaimSearchCriterias.typeOfClaims;
        });
    }

    enableSaveClaimHandler() {
      this.saveDisabled = false;
    }

    savedClaimHandler() {
      this.saveDisabled = true;
      this.releaseDisabled = false;
    }

    newClaimJob() {
      var that = this;
      that.$state.go('app.new-claim-job', {
        selectedTypeOfClaim: that.selectedTypeOfClaim
      });
    }

    selectTab(tab) {
      this.DispatcherService.publish('claimjobs:TabEnter' + tab, this.claimJob);
    }

    saveClaimJob() {
      let formdata = this.NewClaimService.get(this.claimJob);
      this.ClaimJobStore.saveClaimJob(formdata);
    }

    releaseClaimJob() {
      let formdata = this.NewClaimService.get(this.claimJob);
      this.ClaimJobStore.releaseClaimJob(formdata);
    }

    deleteClaimJob() {
      this.ClaimJobStore.deleteClaimJob();
    }
  }

  angular.module('uchpClientAngularApp')
    .component('claimJob', {
      templateUrl: 'components/claim/job/job.html',
      controller: [
        '$log',
        'ClaimJobStore',
        'ClaimSearchCriteriaStore',
        'DispatcherService',
        'NewClaimService',
        '$scope',
        '$state',
        '$stateParams',
        '$translate',
        '$translatePartialLoader',
        ClaimJobController
      ],
      controllerAs: 'ctrl'
    });
}
