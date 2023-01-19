/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';

  class ClaimJobItemAssessmentController {
    private fresh: any;
    private form: any;
    private claimJobSubscription: any;

    static $inject = [
      'ClaimJobItemStore',
      'DispatcherService',
      '$scope',
      'NewClaimService',
      'ClaimJobStore'
    ];
    constructor(private ClaimJobItemStore: IClaimJobItemStore,
                private DispatcherService: IDispatcherService,
                private $scope: ng.IScope,
                private NewClaimService: INewClaimService,
                private ClaimJobStore: IClaimJobStore
    ) {
      var that = this;
      // TODO: not implemented, below are 'placeholder code'
      $scope.$watch('ctrl.form.$pristine', function(v){
        if (v || that.form.$invalid) {
          return;
        }
        that.form.$setPristine();
        that.updateReadyObject();
      }, true);
      that.claimJobSubscription = that.DispatcherService.subscribe('claimJob:getClaimJobItemAssessmentTab',
        that.claimJobResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.claimJobSubscription.unsubscribe();
      });
      if (that.fresh) {
        console.log('new claim assessment');
      }
    }


    claimJobResultHandler(claimJobItemData: any) {
      console.log(claimJobItemData);
    }

    updateReadyObject() {
      var that = this;
      // that.NewClaimService.addAssessment();
      that.DispatcherService.publish('claimJob:SaveEnabled', {});
    }
  }

  angular.module('uchpClientAngularApp')
    .component('claimJobItemAssessment', {
      bindings: {
        fresh: '<'
      },
      controller: [
        'ClaimJobItemStore',
        'DispatcherService',
        '$scope',
        'NewClaimService',
        'ClaimJobStore',
        ClaimJobItemAssessmentController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/job/claim-job-item/claim-job-item-assessment/claim-job-item-assessment.html'
    });
}
