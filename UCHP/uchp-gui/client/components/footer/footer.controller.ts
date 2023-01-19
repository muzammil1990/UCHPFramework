/// <reference path="../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class FooterController {
    /**
     * Creates an instance of FooterController.
     * 
     * @param {ng.ILogService} log
     * 
     * @memberOf FooterController
     */
    constructor(private log: ng.ILogService) {
      log.debug('FooterController Initialized');
    }
  }

  angular.module('uchpClientAngularApp')
    .component('footer', {
      templateUrl: 'components/footer/footer.html',
      controller: [ '$log', FooterController ],
      controllerAs: 'ctrl'
    });
}

