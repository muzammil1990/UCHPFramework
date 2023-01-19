/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class AdvancedSearchListController {
   // private user: User = null;
  //  private showView: Boolean = false;

    constructor(private $log: ng.ILogService,
                private UserStore: IUserStore,
                private $state: any,
                private $translate: ng.translate.ITranslateService,
                private $translatePartialLoaderProvider: ng.translate.ITranslatePartialLoaderProvider) {
      console.log('Advanced Search List Controller ...');

    }
  }


  angular.module('uchpClientAngularApp')
  .component('advancedSearchList', {
    templateUrl: 'components/claim/claims/advanced-search/advanced-search-list/advanced-search-list.html',
    controller: [
      '$log',
      'UserStore',
      '$state',
      '$translate',
      '$translatePartialLoader',
      AdvancedSearchListController
    ],
    controllerAs: 'ctrl'
  });

}
