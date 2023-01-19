/// <reference path="../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class HelpController {
    private date: any;
    /**
     * Creates an instance of HelpController.
     *
     * @param {ng.ILogService} log
     *
     * @memberOf HelpController
     */
    constructor(private log: ng.ILogService) {
      log.debug('HelpController Initialized');
      this.date = 1484829777950;
    }
  }

  angular.module('uchpClientAngularApp')
    .component('help', {
      templateUrl: 'components/help/help.html',
      controller: [ '$log', HelpController ],
      controllerAs: 'ctrl'
    });
}

