/// <reference path="../../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';
  declare var moment: any;

  class ListClaimJobsSearchTabController {
    private standardClaimSearchResultsSubscription: any;
    private claimSearchTypeKeyIndex: String;
    private searchTabVisible: boolean = false;

    /**
     *
     *
     * @static
     *
     * @memberOf ListClaimJobsSearchTabController
     */
    static $inject = [
      '$log',
      'DispatcherService',
      '$scope',
      'uiGridConstants'
    ];
    /**
     * Creates an instance of ListClaimJobsSearchTabController.
     *
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} DispatcherService
     * @param {ng.IScope} $scope
     *
     * @memberOf ListClaimJobsSearchTabController
     */
    constructor(private $log: ng.ILogService,
                private DispatcherService: IDispatcherService,
                private $scope: ng.IScope
    ) {
      var that = this;
      that.standardClaimSearchResultsSubscription = that.DispatcherService
        .subscribe('listclaimjobs:SearchListClaimJobs', that.standardClaimSearchResultHandler.bind(this));

      that.$scope.$on('$stateChangeStart', function () {
        that.standardClaimSearchResultsSubscription.unsubscribe();
      });
    }

    //Subscribe Handler function for 'listclaimjobs:SearchListClaimJobs'
    /**
     *
     *
     * @param {any} data
     *
     * @memberOf ListClaimJobsSearchTabController
     */
    standardClaimSearchResultHandler(data) {
      var that = this;
      that.searchTabVisible = true;
      that.claimSearchTypeKeyIndex = data.claimSearchTypeIndex;
    }
  }

  angular
    .module('uchpClientAngularApp')
    .component('listClaimJobsSearchTab', {
      controller: [
        '$log',
        'DispatcherService',
        '$scope',
        'uiGridConstants',
        ListClaimJobsSearchTabController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/claims/list-claim-jobs/list-claim-jobs-list/list-claim-jobs-sea' +
      'rch-tab/list-claim-jobs-search-tab.html'
    });

}
