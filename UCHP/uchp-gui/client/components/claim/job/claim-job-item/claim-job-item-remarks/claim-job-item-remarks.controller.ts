/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';
  class ClaimJobItemRemarksController {
    private remarks: any;
    private form: any;
    private claimJobSubscription: any;
    private fresh: any;

    static $inject = [
      '$log',
      'ClaimJobItemStore',
      'ClaimJobStore',
      'ModalService',
      'NewClaimService',
      'DispatcherService',
      '$scope'
    ];

    constructor(
      private $log: ng.ILogService,
      private ClaimJobItemStore: IClaimJobItemStore,
      private ClaimJobStore: IClaimJobStore,
      private modal: IModalService,
      private NewClaimService: INewClaimService,
      private DispatcherService: IDispatcherService,
      private $scope: ng.IScope
    ) {
      var that = this;
      that.claimJobSubscription = that.DispatcherService.subscribe('claimJob:getClaimJobItemRemarksTab',
        that.claimJobResultHandler.bind(this));
      $scope.$watch('ctrl.form.$pristine', function(v){
        if (v || that.form.$invalid) {
          return;
        }
        that.form.$setPristine();
        that.updateReadyObject(that.remarks);
      }, true);
      if (that.fresh) {
        that.remarks = {
          remarkCause: '',
          internalComment: '',
          claimComment: '',
          remarkComplaint: '',
          remarkCorrection: ''
        };
      }
    }

    claimJobResultHandler(/* claimJobItemData */) {
      var that = this;
      that.remarks = {
        // TODO: where are the remarks?
        remarkCause: '', // claimJobItemData.activeClaimJobDTO.remarkCause,
        internalComment: '', // claimJobItemData.activeClaimJobDTO.claimSupplementDTO.internalComment,
        claimComment: '', // claimJobItemData.activeClaimJobDTO.claimSupplementDTO.claimComment,
        remarkComplaint: '', // claimJobItemData.activeClaimJobDTO.remarkComplaint,
        remarkCorrection: '' // claimJobItemData.activeClaimJobDTO.remarkCorrection
      };
    }
    updateReadyObject(data) {
      this.NewClaimService.addRemarks(data);
      this.DispatcherService.publish('claimJob:SaveEnabled', {});
    }

    showAppealStatesLog() {
      var that = this;
      var content: ModalContent = {
        title: '',
        message: '',
        subMessage: '',
        operationformat: null,
        rowindex: null
      };
      that.modal.open(content, 'AppealStatus', '', true, null, 'appealstatus.html');
    }

    showCommentHistoryLog() {
      var that = this;
      var content: ModalContent = {
        title: '',
        message: '',
        subMessage: '',
        operationformat: null,
        rowindex: null
      };
      that.modal.open(content, 'CommentHistory', '', true, null, 'commenthistory.html');
    }

    readOnly = false;
  }

  angular.module('uchpClientAngularApp')
    .component('claimJobItemRemarks', {
    controller: [
      '$log',
      'ClaimJobItemStore',
      'ClaimJobStore',
      'ModalService',
      'NewClaimService',
      'DispatcherService',
      '$scope',
      ClaimJobItemRemarksController
    ],
    controllerAs: 'ctrl',
      // TODO: this is not needed anymore?
      bindings: {
        fresh: '<'
      },
    templateUrl: 'components/claim/job/claim-job-item/claim-job-item-remarks/claim-job-item-remarks.html'
  });
}
