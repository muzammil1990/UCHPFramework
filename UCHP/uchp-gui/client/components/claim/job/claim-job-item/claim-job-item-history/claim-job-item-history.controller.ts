/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';

  class ClaimJobItemHistoryController {
    private fresh: any;
    private historyList: any;
    private claimJobSubscription: any;
    private form: any;

    static $inject = [
      'ClaimJobItemStore',
      'ClaimJobStore',
      'NewClaimService',
      'DispatcherService',
      '$scope'
    ];
    constructor(private ClaimJobItemStore: IClaimJobItemStore,
                private ClaimJobStore: IClaimJobStore,
                private NewClaimService: INewClaimService,
                private DispatcherService: IDispatcherService,
                private $scope: ng.IScope
    ) {
      var that = this;
      that.claimJobSubscription = that.DispatcherService.subscribe('claimJob:getClaimJobItemHistoryTab',
        that.claimJobResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.claimJobSubscription.unsubscribe();
      });
      $scope.$watch('ctrl.form.$pristine', function(v){
        if (v || that.form.$invalid) {
          return;
        }
        that.form.$setPristine();
        that.updateReadyObject();
      }, true);
      if (that.fresh) {
        that.historyList = [];
      }
    }

    claimJobResultHandler(claimJobItemData: any) {
      var that = this;
      that.historyList = claimJobItemData && claimJobItemData.historyList &&
      angular.isArray(claimJobItemData.historyList) ? claimJobItemData.historyList : [];
    }

    updateReadyObject() {
      var that = this;
      that.NewClaimService.addHistory(that.historyList);
      that.DispatcherService.publish('claimJob:SaveEnabled', {});
    }
  }

  angular.module('uchpClientAngularApp')
    .component('claimJobItemHistory', {
      templateUrl: 'components/claim/job/claim-job-item/claim-job-item-history/claim-job-item-history.html',
      controller: [
        'ClaimJobItemStore',
        'ClaimJobStore',
        'NewClaimService',
        'DispatcherService',
        '$scope',
        ClaimJobItemHistoryController
      ],
      controllerAs: 'ctrl',
      bindings: {
        fresh: '<'
      }
    });
}
