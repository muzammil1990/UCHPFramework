/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';

  class ClaimJobItemInspectorReportController {
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
      that.claimJobSubscription = that.DispatcherService.subscribe('claimJob:getClaimJobItemTravelTab',
        that.claimJobResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.claimJobSubscription.unsubscribe();
      });
      if (that.fresh) {
        console.log('New claim InspectorReport');
      }
    }

    claimJobResultHandler(claimJobItemData: any) {
      console.log(claimJobItemData);
    }

    updateReadyObject() {
      var that = this;
      // that.NewClaimService.addInspectorReport();
      that.DispatcherService.publish('claimJob:SaveEnabled', {});
    }
  }

  angular.module('uchpClientAngularApp')
    .component('claimJobItemInspectorReport', {
      bindings: {
        fresh: '<'
      },
      controller: [
        'ClaimJobItemStore',
        'DispatcherService',
        '$scope',
        'NewClaimService',
        'ClaimJobStore',
        ClaimJobItemInspectorReportController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/job/claim-job-item/claim-job-item-inspector-report/claim-job-item-inspector-report.html'
    });
}
