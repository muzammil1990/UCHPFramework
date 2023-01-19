/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';

  class ClaimJobItemOtherCostsController {
    private fresh: any;
    private form: any;
    private currency: any;
    private costList: any;
    private newCost: any;
    private claimJobSubscription: any;

    static $inject = [
      'ClaimJobItemStore',
      '$scope',
      'NewClaimService',
      'DispatcherService',
      'ClaimJobStore'
    ];
    constructor(private ClaimJobItemStore: IClaimJobItemStore,
                private $scope: ng.IScope,
                private NewClaimService: INewClaimService,
                private DispatcherService: IDispatcherService,
                private ClaimJobStore: IClaimJobStore
    ) {
      var that = this;
      that.claimJobSubscription = that.DispatcherService.subscribe('claimJob:getClaimJobItemCostTab',
        that.claimJobResultHandler.bind(this));
      $scope.$watch('ctrl.form.$pristine', function(v){
        if (v || that.form.$invalid) {
          return;
        }
        that.form.$setPristine();
        that.updateReadyObject();
      }, true);
      if (that.fresh) {
        that.newCost = {
          amountChanged: false,
          approvedCostNetCost: 0,
          claimCostId: 0,
          core: false,
          costAdjustFactor: 0,
          costDebitList: [
            {
              claimCostDebitId: '',
              colNo: 0,
              costClaimCostId: 0,
              coverageId: '',
              debitCode: 0,
              debitCodeCategory: 0,
              debitCodeType: 0,
              modifiedBy: '',
              modifyTime: '',
              netCost: 0,
              rejectCode: ''
            }
          ],
          deletedInGUI: false,
          description: '',
          jobClaimJobId: 0,
          netCost: 0,
          netCostOriginal: 0,
          otherCostCategoryCode: 0,
          quantity: 0,
          rejectCode: '',
          rejectCodeDescription: '',
          rejectedCostNetCost: 0,
          unitPrice: 0
        };
        that.costList = [];
        that.costList.push(angular.copy(that.newCost));
      }
    }

    claimJobResultHandler(claimJobItemData: any) {
      var that = this;
      that.costList = claimJobItemData && claimJobItemData.costList &&
      angular.isArray(claimJobItemData.costList) ? claimJobItemData.costList : [];
      that.currency = claimJobItemData && claimJobItemData.costTransactionSummaryDTO &&
      claimJobItemData.costTransactionSummaryDTO.currency ? claimJobItemData.costTransactionSummaryDTO.currency : '';
    }

    updateReadyObject() {
      var that = this;
      that.NewClaimService.addCosts(that.costList);
      that.DispatcherService.publish('claimJob:SaveEnabled', {});
    }

    deleteRowInGui($index) {
      var that = this;
      that.costList[$index].deletedInGUI = true;
      console.log(that.costList);
    };

    addRow() {
      var that = this;
      this.costList.push(angular.copy(that.newCost));
    };

  }

  angular
    .module('uchpClientAngularApp')
    .component('claimJobItemOtherCosts', {
      controller: [
        'ClaimJobItemStore',
        '$scope',
        'NewClaimService',
        'DispatcherService',
        'ClaimJobStore',
        ClaimJobItemOtherCostsController
      ],
      controllerAs: 'ctrl',
      bindings: {
        fresh: '<'
      },
      templateUrl: 'components/claim/job/claim-job-item/claim-job-item-other-costs/claim-job-item-other-costs.html'
    });
}
