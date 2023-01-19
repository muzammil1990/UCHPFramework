/**
 * Created by a232625 on 2016-08-01.
 */
module uchpClientAngularApp {
  'use strict';

  class ClaimJobItemController {
    private claimJobSubscription: any;
    private claimJob: any;
    private selected: any;
    private fresh: any;
    private claimParams: any;
    private showTabs: boolean;
    private selectedCompany: any;
    private showTravelTab: boolean;
    private showCostTransactionTab: boolean;
    private showInspectorReportTab: boolean;
    private showAssessmentTab: boolean;

    static $inject = [
      '$log',
      'DispatcherService',
      'ClaimJobStore',
      'ClaimJobItemStore',
      'SelectCompaniesStore',
      '$scope',
      '$stateParams',
      '$window',
      'ModalService'
    ];
    constructor(private $log: ng.ILogService,
                private DispatcherService: IDispatcherService,
                private ClaimJobStore: IClaimJobStore,
                private ClaimJobItemStore: IClaimJobItemStore,
                private SelectCompaniesStore: ISelectCompaniesStore,
                private $scope: ng.IScope,
                private $stateParams,
                private $window: ng.IWindowService,
                private modal: IModalService
    ) {
      var that = this;
      this.$log.debug('claimJobItemController Initialized');
      that.showTabs = false;
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      if (that.selectedCompany === '6') {
        that.showTravelTab = true;
      }
      that.showCostTransactionTab = false;
      that.showInspectorReportTab = false;
      that.showAssessmentTab = false;
      that.claimJobSubscription = that.DispatcherService.subscribe('claimJob:getClaimJob',
        that.claimJobResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.claimJobSubscription.unsubscribe();
      });
    }

    viewTechnicalData() {
      var that = this;
      var path = 'https://secure4.volvo.com/vda/?ChassisNumber=' +
        that.claimJob.technicalDataUrl;
      that.$window.open(path, '_blank');
    }

    viewRepairHistory() {
      var that = this;
      // TODO: open view repair history modal
      var content: ModalContent = {
        message: '',
        subMessage: '',
        title: '',
        operationformat: null,
        rowindex: null
      };
      that.modal.open(content, 'ViewRepairHistory', '', true, null, 'repairhistory.html');
    }

    viewCoverage() {
      var that = this;
      // TODO: open view coverage modal
      var content: ModalContent = {
        message: '',
        subMessage: '',
        title: '',
        operationformat: null,
        rowindex: null
      };
      that.modal.open(content, 'ViewCoverage', '', true, null, 'coverage.html');
    }

    viewCampaign() {
      var that = this;
      var path = this.claimJob.viewCampaignUrl;
      that.$window.open(path, '_blank');
    }

    viewGDR() {
      var that = this;
      var path = this.claimJob.technicalDataUrl2;
      that.$window.open(path, '_blank');
    }

    viewPHV() {
      var that = this;
      var path = 'https://secure4.volvo.com/ProductHistoryViewer/';
      that.$window.open(path, '_blank');
    }

    viewPrintableVersion() {
      var that = this;
      // TODO: open print modal
      var content: ModalContent = {
        message: '',
        subMessage: '',
        title: '',
        operationformat: null,
        rowindex: null
      };
      that.modal.open(content, 'ViewPrintableVersion', '', true, null, 'printable.html');
    }

    claimJobResultHandler(claimJob) {
      var that = this;
      that.claimJob = claimJob;
      // set information as initial tab
      if (that.claimJob.claimRepairHeaderDTO.activeClaimJobDTO.typeOfClaim === 2 ||
        that.claimJob.claimRepairHeaderDTO.activeClaimJobDTO.typeOfClaim === 3) {
        that.selected = 0;
        // else set labour as initial tab
      } else {
        that.selected = 1;
      }
      that.showTabs = true;
    }

    selectTab(tabName) {
      var that = this;
      that.claimParams = that.ClaimJobStore.getClaimParams();
      if (tabName === 'Remarks') {
        that.claimParams.appealStatus = 1;
      }
      if (!that.fresh && tabName) {
        that.ClaimJobItemStore.getClaimJob(that.claimParams,
          tabName
        );
      }
    }

    closeAlert(index) {
      var that = this;
      let alertsAccessor = 'alerts';
      that.$scope[alertsAccessor].splice(index, 1);
    }
  }

  angular.module('uchpClientAngularApp')
    .component('claimJobItem', {
      bindings: {
        fresh: '<'
      },
      controller: [
        '$log',
        'DispatcherService',
        'ClaimJobStore',
        'ClaimJobItemStore',
        'SelectCompaniesStore',
        '$scope',
        '$stateParams',
        '$window',
        'ModalService',
        ClaimJobItemController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/job/claim-job-item/claim-job-item.html'
    });
}
