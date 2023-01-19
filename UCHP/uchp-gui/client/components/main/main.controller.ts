/// <reference path="../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class MainController {
    /**
     * Creates an instance of MainController.
     * 
     * @param {ng.ILogService} log
     * 
     * @memberOf MainController
     */
    constructor(private log: ng.ILogService) {
      log.debug('MainController Initialized');
    }
  }

  angular.module('uchpClientAngularApp')
    .component('main', {
      templateUrl: 'components/main/main.html',
      controller: [ '$log', MainController ],
      controllerAs: 'ctrl'
    });
}
