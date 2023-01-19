/// <reference path="../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @class ListClaimJobsController
   */
  class ListClaimJobsController {
    /**
     *
     *
     * @private
     * @type {User}
     * @memberOf ListClaimJobsController
     */
    private user: User = null;
    //   private privilege: String = 'CanCreateClaimJobs';
    /**
     *
     *
     * @private
     * @type {Boolean}
     * @memberOf ListClaimJobsController
     */
    private showView: Boolean = false;

    /**
     *
     *
     * @static
     *
     * @memberOf ListClaimJobsController
     */
    static $inject = [
      '$log',
      'UserStore',
      '$state',
      '$translate',
      '$translatePartialLoader'
    ];

    /**
     * Creates an instance of ListClaimJobsController.
     *
     * @param {ng.ILogService} $log
     * @param {IUserStore} UserStore
     * @param {*} $state
     * @param {ng.translate.ITranslateService} $translate
     * @param {ng.translate.ITranslatePartialLoaderProvider} $translatePartialLoaderProvider
     * @memberOf ListClaimJobsController
     */
    constructor(
      private $log: ng.ILogService,
      private UserStore: IUserStore,
      private $state: any,
      private $translate: ng.translate.ITranslateService,
      private $translatePartialLoaderProvider: ng.translate.ITranslatePartialLoaderProvider
    ) {
      var that = this;
      $translatePartialLoaderProvider.addPart('claim');
      $translate.refresh();
      that.$log.debug('ListClaimJobsController Initialized');
      that
        .UserStore
        .getUser()
        .then(function (userFromStore) {
          if (userFromStore !== null && userFromStore !== undefined) {
            that.user = userFromStore;
            that.checkIfUserIsPrivileged();
          } else {
            that.checkIfUserIsPrivileged();
          }
        });
    }

    /**
     *
     *
     * @returns {*}
     *
     * @memberOf ListClaimJobsController
     */
    checkIfUserIsPrivileged(): any {
      var that = this;
      if (that.isPrivileged()) {
        that.showView = true;
      } else {
        that.$state.go('app');
      }
    }

    /**
     * Check if user is logged in and a feature should be shown, based on privileges
     * */
    /**
     *
     *
     * @returns {boolean}
     *
     * @memberOf ListClaimJobsController
     */
    isPrivileged(): boolean {
      // var that = this;
      return true; // that.user && that.user.privilegeList.indexOf(that.privilege) !== -1;
    }
  }
  angular.module('uchpClientAngularApp')
    .component('listClaimJobs', {
      templateUrl: 'components/claim/claims/list-claim-jobs/list-claim-jobs.html',
      controller: [
        '$log',
        'UserStore',
        '$state',
        '$translate',
        '$translatePartialLoader',
        ListClaimJobsController ],
      controllerAs: 'ctrl'
    });
}
