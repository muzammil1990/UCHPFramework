/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';

  class ClaimJobItemLabourController {
    private fresh: any;
    private form: any;
    private labourList: any;
    private newLabour: any;
    private factoryLabourNetTotal: any;
    private currency: any;
    private claimParams: any;
    private claimJobSubscription: any;
    private setOperationNumberSubscription: any;
    private operationFormat: number;

    static $inject = [
      'ClaimJobItemStore',
      'DispatcherService',
      'ModalService',
      '$scope',
      'NewClaimService',
      'ClaimJobStore',
      'OperationListsStore'
    ];
    constructor(private ClaimJobItemStore: IClaimJobItemStore,
                private DispatcherService: IDispatcherService,
                private modal: IModalService,
                private $scope: ng.IScope,
                private NewClaimService: INewClaimService,
                private ClaimJobStore: IClaimJobStore,
                private OperationListsStore: IOperationListsStore
    ) {
      var that = this;
      $scope.$watch('ctrl.form.$pristine', function(v){
        if (v || that.form.$invalid) {
          return;
        }
        that.form.$setPristine();
        that.updateReadyObject();
      }, true);
      that.setOperationNumberSubscription = that.DispatcherService.subscribe('modalclose:setOperationNumber',
        that.setOperationNumberHandler.bind(this));
      that.claimJobSubscription = that.DispatcherService.subscribe('claimJob:getClaimJobItemLabourTab',
        that.claimJobResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.claimJobSubscription.unsubscribe();
        that.setOperationNumberSubscription.unsubscribe();
      });
      if (that.fresh) {
        that.labourList = [];
        that.currency = '';
        that.factoryLabourNetTotal = '';
        that.labourList.push(angular.copy(that.newLabour));
      }
    }

    showOperationNumberModal(rowindex: number) {
      var that = this;
      that.modal.open({
        title: '',
        message: '',
        subMessage: '',
        operationformat: that.operationFormat,
        rowindex: rowindex
      }, 'setOperationNumber', 'md', true, null,
        'operationnomodal.html', null, null);
    }

    setOperationNumberHandler(data) {
      this.labourList[data.rowindex].operationNo = data.operation.operationNo;
      this.labourList[data.rowindex].description = data.operation.description;
    }

    claimJobResultHandler(claimJobItemData: any) {
      var that = this;
      that.claimParams = that.ClaimJobStore.getClaimParams();
      that.operationFormat = that.ClaimJobStore.getOperationFormat() || 1;
      that.newLabour = {
        approvedLabourNetCost: 0,
        claimLabourId: 0,
        costAdjustFactor: 0,
        costAdjustFactorOriginal: 0,
        deletedInGUI: false,
        description: '',
        headCompanyNo: that.claimParams.companyNo,
        hours: 0,
        jobClaimJobId: that.claimParams.claimJobId,
        labourDebitList: [
          {
            claimLabourDebitId: '',
            colNo: 0,
            coverageId: '',
            debitCode: 0,
            debitCodeCategory: 0,
            debitCodeType: 0,
            labourClaimLabourId: 0,
            modifiedBy: '',
            modifyTime: '',
            netCost: 0,
            rejectCode: ''
          }
        ],
        labourNetCost: 0,
        labourNetCostOriginal: 0,
        labourRate: 0,
        modifiedBy: '',
        modifyTime: '',
        operationId: '',
        operationNo: '',
        operationFormat: 0,
        quantity: 0,
        rejectCode: '',
        rejectCodeDescription: '',
        rejectedLabourNetCost: 0,
        vst: false
      };
      that.labourList = claimJobItemData && claimJobItemData.labourList &&
      angular.isArray(claimJobItemData.labourList) ? claimJobItemData.labourList : [];
      that.currency = claimJobItemData && claimJobItemData.costTransactionSummaryDTO &&
      claimJobItemData.costTransactionSummaryDTO.currency ? claimJobItemData.costTransactionSummaryDTO.currency : '';
      that.factoryLabourNetTotal = claimJobItemData && claimJobItemData.costTransactionSummaryDTO &&
      claimJobItemData.costTransactionSummaryDTO.factoryLabourNetTotal ?
        claimJobItemData.costTransactionSummaryDTO.factoryLabourNetTotal : '';
    }

    updateReadyObject() {
      var that = this;
      that.NewClaimService.addLabour(that.labourList);
      that.DispatcherService.publish('claimJob:SaveEnabled', {});
    }

    deleteRowInGui($index) {
      var that = this;
      that.labourList[$index].deletedInGUI = true;
      console.log(that.labourList);
    };

    addRow() {
      var that = this;
      that.labourList.push(angular.copy(that.newLabour));
    };
  }

  angular.module('uchpClientAngularApp')
    .component('claimJobItemLabour', {
      bindings: {
        fresh: '<'
      },
      controller: [
        'ClaimJobItemStore',
        'DispatcherService',
        'ModalService',
        '$scope',
        'NewClaimService',
        'ClaimJobStore',
        'OperationListsStore',
        ClaimJobItemLabourController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/job/claim-job-item/claim-job-item-labour/claim-job-item-labour.html'
    });
}
