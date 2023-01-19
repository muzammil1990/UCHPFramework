/// <reference path="../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';
  class NewClaimJobController {

    private activeTab: any = 0;
    private postClaimSubscription: any;

    static $inject = [
      '$log',
      '$translate',
      '$translatePartialLoader',
      'DispatcherService'
    ];

    constructor(
      private $log: ng.ILogService,
      private $translate: ng.translate.ITranslateService,
      private $translatePartialLoaderProvider: ng.translate.ITranslatePartialLoaderProvider,
      private DispatcherService: IDispatcherService
    ) {
      var that = this;
      $log.debug('NewClaimJobController Initialized');
      $translatePartialLoaderProvider.addPart('claim');
      $translate.refresh();
      that.postClaimSubscription = that.DispatcherService.subscribe('newclaimjob:PostClaim',
        that.postClaimHandler.bind(this));
    }

    postClaimHandler() {
      this.activeTab = 1;
    }
  }

  angular.module('uchpClientAngularApp')
    .component('newClaimJob', {
      templateUrl: 'components/claim/claims/new-claim-job/new-claim-job.html',
      controller: [
        '$log',
        '$translate',
        '$translatePartialLoader',
        'DispatcherService',
        NewClaimJobController
      ],
      controllerAs: 'ctrl'
    });
}
