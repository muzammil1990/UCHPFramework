/**
 * Created by a232625 on 2016-08-01.
 */
module uchpClientAngularApp {
  'use strict';

  class ClaimJobHeaderController {
    private claimJob: Object;
    private claimJobSubscription: any;
    private tabSelectSubscription: any;

    static $inject = [
      'DateUtilService',
      'DispatcherService',
      '$log',
      '$scope'
    ];

    constructor(
      private DateUtilService: IDateUtilService,
      private DispatcherService: IDispatcherService,
      private $log: ng.ILogService,
      private $scope: ng.IScope
    ) {
      $log.debug('claimJobHeaderController Initialized');
      var that = this;
      that.claimJobSubscription = that.DispatcherService.subscribe('claimJob:getClaimJobItem',
        that.claimJobResultHandler.bind(this));
      that.tabSelectSubscription = that.DispatcherService.subscribe('claimjobs:TabEnter0',
        that.claimJobResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.claimJobSubscription.unsubscribe();
        that.tabSelectSubscription.unsubscribe();
      });
    }

    claimJobResultHandler(claimJob) {
      this.claimJob = claimJob;
    }

    datePickerOptions = this.DateUtilService.getDatePickerOptions();
  }

  angular.module('uchpClientAngularApp')
    .component('claimJobHeader', {
      templateUrl: 'components/claim/job/claim-job-header/claim-job-header.html',
      controller: [
        'DateUtilService',
        'DispatcherService',
        '$log',
        '$scope',
        ClaimJobHeaderController
      ],
      controllerAs: 'ctrl'
    });
}
