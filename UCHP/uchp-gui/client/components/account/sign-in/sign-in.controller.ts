/**
 * Created by a232625 on 2016-07-18.
 */
///<reference path="../../../app/_all.ts" />

module uchpClientAngularApp {

  'use strict';


  class SignInController {

    private user: any;
    private errors: any;
    private submitted: boolean;

    /**
     *
     *
     * @static
     *
     * @memberOf SignInController
     */
    static $inject = [
      'DispatcherService',
      '$log',
      '$state',
      'AuthService'
    ];

    /**
     * Creates an instance of SignInController.
     *
     * @param {IDispatcherService} DispatcherService
     * @param {ng.ILogService} $log
     * @param {any} $state
     * @param {IAuthService} AuthService
     *
     * @memberOf SignInController
     */
    constructor(private DispatcherService: IDispatcherService,
                private $log: ng.ILogService,
                private $state,
                private AuthService: IAuthService) {

      $log.debug('SignInController initializing ...');

      this.user = {
        email: '',
        password: ''
      };

      this.errors = {};
      this.submitted = false;

    }

    /**
     *
     *
     * @param {any} form
     *
     * @memberOf SignInController
     */
    login(form) {
      const UserAny: any = User;
      var that = this;
      that.submitted = true;
      var user = new UserAny(null, null, null, this.user.password, this.user.email, null, null, null);
      if (form.$valid) {
        that
          .DispatcherService
          .publishAndWaitForReply('user:request:signin', user)
          .then(function (data) {
              that.user = data;
              that.$log.debug('user has been signed in');
              form.$setPristine();
              that.DispatcherService.publish('user:signed:in', that.user); // notify other components
              that.$state.go('app');
            }, function (err) {
              that.user.password = '';
              // TODO setSignInError does now exist, so commented out for now
              //setSignInError(err.message);
            }
          );
      }
    }
  }

  angular.module('uchpClientAngularApp')
    .component('signIn', {
      templateUrl: 'components/account/sign-in/sign-in.html',
      controller: ['DispatcherService', '$log', '$state', 'AuthService', SignInController],
      controllerAs: 'ctrl'
    });
}
