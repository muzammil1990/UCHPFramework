/// <reference path="../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class PrintCreditDocumentController {
    private user: User = null;
 //   private privilege: String = 'CanCreateClaimJobs';
    private showView: Boolean = false;

    /**
     * Creates an instance of PrintCreditDocumentController.
     * 
     * @param {ng.ILogService} $log
     * @param {IUserStore} UserStore
     * @param {*} $state
     * @param {ng.translate.ITranslateService} $translate
     * @param {ng.translate.ITranslatePartialLoaderProvider} $translatePartialLoaderProvider
     * 
     * @memberOf PrintCreditDocumentController
     */
    constructor(
      private $log: ng.ILogService,
      private UserStore: IUserStore,
      private $state: any,
      private $translate: ng.translate.ITranslateService,
      private $translatePartialLoaderProvider: ng.translate.ITranslatePartialLoaderProvider
    ) {

      var that = this;
      that.$log.debug('PrintCreditDocumentController Initialized');
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
    }

    /**
     * 
     * 
     * @returns {*}
     * 
     * @memberOf PrintCreditDocumentController
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
     * @memberOf PrintCreditDocumentController
     */
    isPrivileged(): boolean {
     // var that = this;
      return true; // that.user && that.user.privilegeList.indexOf(that.privilege) !== -1;
    }
  }

  angular.module('uchpClientAngularApp')
    .component('printCreditDocument', {
      templateUrl: 'components/crediting/print-credit-document/print-credit-document.html',
      controller: [
        '$log',
        'UserStore',
        '$state',
        '$translate',
        '$translatePartialLoader',
        PrintCreditDocumentController
      ],
      controllerAs: 'ctrl'
    });
}
