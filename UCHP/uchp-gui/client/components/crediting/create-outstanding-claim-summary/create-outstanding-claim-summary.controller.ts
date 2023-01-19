/// <reference path="../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class CreateOutstandingClaimSummaryController {
    private user: User = null;
  //  private privilege: String = 'CanCreateClaimJobs';
    private showView: Boolean = false;
    private selectedCompany: String;

    static $inject = [
      'SelectCompaniesStore',
      '$log',
      'UserStore',
      '$state',
      '$translate',
      '$translatePartialLoader'
    ];

    constructor(
      private SelectCompaniesStore: ISelectCompaniesStore,
      private $log: ng.ILogService,
      private UserStore: IUserStore,
      private $state: any,
      private $translate: ng.translate.ITranslateService,
      private $translatePartialLoaderProvider: ng.translate.ITranslatePartialLoaderProvider) {

      var that = this;
      that.$log.debug('CreateOutstandingClaimSummaryController Initialized');
      $translatePartialLoaderProvider.addPart('crediting');
      $translate.refresh();
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
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
    }

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
    isPrivileged(): boolean {
      // var that = this;
      return true; // that.user && that.user.privilegeList.indexOf(that.privilege) !== -1;
    }
  }

  angular.module('uchpClientAngularApp')
    .component('createOutstandingClaimSummary', {
      templateUrl: 'components/crediting/create-outstanding-claim-summary/create-outstanding-claim-summary.html',
      controller: [
        'SelectCompaniesStore',
        '$log',
        'UserStore',
        '$state',
        '$translate',
        '$translatePartialLoader',
        CreateOutstandingClaimSummaryController
      ],
      controllerAs: 'ctrl'
    });
}
