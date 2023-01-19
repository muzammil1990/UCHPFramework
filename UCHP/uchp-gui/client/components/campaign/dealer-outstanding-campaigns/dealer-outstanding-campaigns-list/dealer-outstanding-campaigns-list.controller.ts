/// <reference path="../../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  class DealerOutstandingCampaignsListController {
    static $inject = [
      '$log',
      'DispatcherService',
      '$scope',
      'ModalService',
      'DispatcherService',
      'NotificationService',
      'localStorageService',
      'uiGridConstants'
    ];
    constructor( private $log: ng.ILogService,
                private dispatcherService: IDispatcherService,
                private $scope: ng.IScope,
                private modal: IModalService,
                private DispatcherService: IDispatcherService,
                private NotificationService: INotificationService,
                private localStorageService) {
    }
  }

  angular.module('uchpClientAngularApp')
    .component('dealerOutstandingCampaignsList', {
    controller: [
      '$log',
      'DispatcherService',
      '$scope',
      'ModalService',
      'DispatcherService',
      'NotificationService',
      'localStorageService',
      'uiGridConstants',
      DealerOutstandingCampaignsListController
    ],
    controllerAs: 'ctrl',
    templateUrl: 'components/campaign/dealer-outstanding-campaigns/dealer-outstanding-campaigns-list/dealer-outstanding-campaigns-list.html'
  });
}
