/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';

  class ClaimJobItemTravelController {
    private fresh: any;
    private form: any;
    private claimJobSubscription: any;
    private travelList: any;

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
      that.travelList = [{
        claimTravelId: 0,
        travelType: 0,
        amount: 0,
        rate: 0,
        travelNetCost: 0,
        description: '',
        costAdjustFactor: 0,
        travelNetCostOriginal: 0,
        modifiedBy: '',
        modifyTime: '',
        jobClaimJobId: 0,
        rejectedTravelNetCost: 0,
        approvedTravelNetCost: 0,
        rejectCode: '',
        rejectCodeDescription: '',
        deletedInGUI: false
      }];
      if (that.fresh) {
        console.log('New Claim Travel');
      }
    }

    claimJobResultHandler(claimJobItemData: any) {
      var that = this;
      that.travelList = that.travelList.concat(claimJobItemData.travelList);
    }

    updateReadyObject() {
      var that = this;
      // that.NewClaimService.addTravel(that.travelList);
      that.DispatcherService.publish('claimJob:SaveEnabled', {});
    }
  }

  angular.module('uchpClientAngularApp')
    .component('claimJobItemTravel', {
      bindings: {
        fresh: '<'
      },
      controller: [
        'ClaimJobItemStore',
        'DispatcherService',
        '$scope',
        'NewClaimService',
        'ClaimJobStore',
        ClaimJobItemTravelController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/job/claim-job-item/claim-job-item-travel/claim-job-item-travel.html'
    });
}
