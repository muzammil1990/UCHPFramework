/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';

  class ClaimJobItemInformationController {
   // private fresh: any;
    private claimJob: any;
    private claimJobSubscription: any;
    private datePickerOptions: any;
    private common: boolean;
    private parts: boolean;
    private campaign: boolean;

    static $inject = [
      'DispatcherService',
      'DateUtilService',
      '$log',
      '$scope'
    ];
    constructor(private DispatcherService: IDispatcherService,
                private DateUtilService: IDateUtilService,
                private $log: ng.ILogService,
                private $scope: ng.IScope
    ) {
      var that = this;
      that.claimJobSubscription = that.DispatcherService.subscribe('claimJob:getClaimJob',
        that.claimJobResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.claimJobSubscription.unsubscribe();
      });
      that.datePickerOptions = that.DateUtilService.getDatePickerOptions();
    }

    claimJobResultHandler(claimJob) {
      var that = this;
      // convert strings to numbers.....
      let claimJobTypes = [];
      _.each(claimJob.claimJobTypes, function(type) {
        claimJobTypes.push({
          key: +type.key,
          displayText: type.displayText
        });
      });
      claimJob.claimJobTypes = claimJobTypes;
      that.claimJob = claimJob;
      that.setType();
    }

    setType() {
      var that = this;
      that.parts = that.claimJob.claimRepairHeaderDTO.activeClaimJobDTO.typeOfClaim === 2;
      that.campaign = that.claimJob.claimRepairHeaderDTO.activeClaimJobDTO.typeOfClaim === 3;
      that.common =  !that.parts && !that.campaign;
    }
  }

  angular.module('uchpClientAngularApp')
    .component('claimJobItemInformation', {
      controller  : [
        'DispatcherService',
        'DateUtilService',
        '$log',
        '$scope',
        ClaimJobItemInformationController
      ],
      controllerAs: 'ctrl',
      bindings: {
        fresh: '<'
      },
      templateUrl : 'components/claim/job/claim-job-item/claim-job-item-information/claim-job-item-in' +
      'formation.html'
    });
}
