/// <reference path="../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class AdvancedSearchController {
    private user: User = null;
    private showView: Boolean = false;

    constructor(private $log: ng.ILogService,
                private UserStore: IUserStore,
                private $state: any,
                private $translate: ng.translate.ITranslateService,
                private $translatePartialLoaderProvider: ng.translate.ITranslatePartialLoaderProvider) {
      var that = this;
      console.log('Advanced Search Controller ...');
      $translatePartialLoaderProvider.addPart('claim');
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
  .component('advancedSearch', {
    templateUrl: 'components/claim/claims/advanced-search/advanced-search.html',
    controller: [
      '$log',
      'UserStore',
      '$state',
      '$translate',
      '$translatePartialLoader',
      AdvancedSearchController
    ],
    controllerAs: 'ctrl'
  });

}
