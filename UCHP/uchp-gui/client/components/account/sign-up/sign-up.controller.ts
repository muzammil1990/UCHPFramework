/**
 * Created by a232625 on 2016-07-18.
 */
///<reference path="../../../app/_all.ts" />

module uchpClientAngularApp {

  'use strict';


  class SignUpController {

    private user: any;
    private errors: any;
    private submitted: boolean = false;

    /**
     *
     *
     * @static
     *
     * @memberOf SignUpController
     */
    static $inject = [
      'DispatcherService',
      '$log',
      '$state',
      'AuthService'
    ];

    /**
     * Creates an instance of SignUpController.
     *
     * @param {IDispatcherService} DispatcherService
     * @param {ng.ILogService} $log
     * @param {*} $state
     * @param {IAuthService} AuthService
     *
     * @memberOf SignUpController
     */
    constructor(private DispatcherService: IDispatcherService,
                private $log: ng.ILogService,
                private $state: any,
                private AuthService: IAuthService) {

      $log.debug('SignUpController initializing ...');

      this.errors = {};
      this.submitted = false;
    }

    /**
     *
     *
     * @param {any} form
     *
     * @memberOf SignUpController
     */
    register(form) {
      // TODO once the user has been created then it should be authenticated and signed with token
      var that = this;
      that.submitted = true;

      if (form.$valid) {
        var firstName: string = null;
        var lastName: string = null;

        if (this.user && this.user.name) {
          let name = this.user.name.split(' ');
          firstName = name.shift();
          lastName = name.join(' ');
        }
        const UserAny: any = User;
        var user = new UserAny(this.user.email, firstName, lastName, this.user.password,
          this.user.email, this.user.usersetting, null, null);

        that
          .DispatcherService
          .publishAndWaitForReply('user:request:signup', user)
          .then((userToken: any) => {
            that.$log.debug('user has been signed up');
            form.$setPristine();
            that.DispatcherService.publish('user:signed:up', that.user); // notify other components
            return userToken.token;
          })
          .then((userToken: string) => {
            return that.DispatcherService.publishAndWaitForReply('user:request:signin:token', userToken);
          })
          .then((signedUser: User) => {
            that.DispatcherService.publish('user:signed:in', signedUser);
            that.$state.go('app');
          })
          .catch(err => {
            that.$log.debug(`user sign up error: ${err}`);
            that.user.password = '';
            that.user.confirmPassword = '';

            if (err !== undefined) {
              err = err.data;
              this.errors = {};

              // Update validity of form fields that match the mongoose errors
              angular.forEach(err.errors, (error, field) => {
                form[field].$setValidity('mongoose', false);
                this.errors[field] = error.message;
              });
            }
          });
      }
    }
  }

  angular.module('uchpClientAngularApp')
    .component('signUp', {
      templateUrl: 'components/account/sign-up/sign-up.html',
      controller: ['DispatcherService', '$log', '$state', 'AuthService', SignUpController],
      controllerAs: 'ctrl'
    });
}
