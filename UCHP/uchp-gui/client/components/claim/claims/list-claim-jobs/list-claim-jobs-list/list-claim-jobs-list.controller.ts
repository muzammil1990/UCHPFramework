/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';
  declare var moment: any;

  class ListClaimJobsListController {
    private selected: any;
    private searchTabSubscription: any;

    /**
     *
     *
     * @static
     *
     * @memberOf ListClaimJobsListController
     */
    static $inject = [
      '$log',
      'DispatcherService',
      '$scope'
    ];
    /**
     * Creates an instance of ListClaimJobsListController.
     *
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} DispatcherService
     * @param {ng.IScope} $scope
     *
     * @memberOf ListClaimJobsListController
     */
    constructor(
      private $log: ng.ILogService,
      private DispatcherService: IDispatcherService,
      private $scope: ng.IScope
    ) {
      var that = this;
      that.searchTabSubscription = that.DispatcherService
        .subscribe('listclaimjobs:showSearchTab', that.searchTabHandler.bind(this));
    }

    /**
     *
     *
     * @param {any} tab
     *
     * @memberOf ListClaimJobsListController
     */
    selectTab(tab) {
      this.DispatcherService.publish('listclaimjobs:TabEnter' + tab, {});
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsListController
     */
    selectSearchTab() {
      this.DispatcherService.publish('listclaimjobs:TabSearch', {});
    }

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf ListClaimJobsListController
     */
    searchTabHandler(data) {
      this.selected = data.tab;
    }
  }
  angular.module('uchpClientAngularApp')
    .component('listClaimJobsList', {
      controller: [
        '$log',
        'DispatcherService',
        '$scope',
        ListClaimJobsListController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/claims/list-claim-jobs/list-claim-jobs-list/list-claim-jobs-list.html'
    });

}
