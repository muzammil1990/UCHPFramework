/**
 * Created by a232625 on 2016-07-18.
 */
///<reference path="../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class SettingsController {

    private errors: any;
    private submitted: boolean;
    //private message: any;
    //private user: any;

    /**
     * Creates an instance of SettingsController.
     * 
     * @param {IAuthService} AuthService
     * 
     * @memberOf SettingsController
     */
    constructor(private AuthService: IAuthService) {
      this.errors = {};
      this.submitted = false;
    }

    /**
     * 
     * 
     * @param {*} form
     * 
     * @memberOf SettingsController
     */
    changePassword(form: any) {
      this.submitted = true;
    }
  }

  angular.module('uchpClientAngularApp')
    .component('settings', {
      templateUrl: 'components/account/settings/settings.html',
      controller: ['AuthService', SettingsController],
      controllerAs: 'ctrl'
    });
}
