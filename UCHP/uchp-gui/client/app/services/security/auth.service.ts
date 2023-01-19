/// <reference path="../../_all.ts" />
/**
 * Created by a232625 on 2016-07-18.
 * oAuth may be changed to PingFederateConfig after ping.federate.config.ts refactor
 */
module uchpClientAngularApp {
  'use strict';

  import IJwtHelper = angular.jwt.IJwtHelper;

  class AuthService implements IAuthService {

    static $inject = [
      '$log',
      'DispatcherService',
      'UserStore',
      '$cookies',
      'jwtHelper',
      'OAuth',
      '$window',
      'PingFederateConfig'
    ];

    constructor(private $log: ng.ILogService,
                private DispatcherService: IDispatcherService,
                private UserStore: IUserStore,
                private $cookies: ng.cookies.ICookiesService,
                private jwtHelper: IJwtHelper,
                private oAuth: HelloJSStatic,
                private $window: ng.IWindowService,
                private PingFederateConfig: IPingFederateConfig) {
      this.$log.debug('Initializing Auth service...');

      this.DispatcherService.subscribe('user:signed:out', this.signOut.bind(this));

      this.loadUser();
      this.registerPingFederateCallbacks();
    }

    signOut(): void {
      this.$log.debug('user signing out ..');
    }

    /**
     * If user has signed in, there is a cookie with the token.
     * */
    isSignedIn(): boolean {
      var token = this.$cookies.get('token');
      return (angular.isDefined(token) && token !== '');
    }

    /**
     * Post authentication request where we will expect to receive access token
     * */
    private registerPingFederateCallbacks() {
      this.registerOAuthLoginCallback();
      this.registerOAuthLogoutCallback();
    };

    private registerOAuthLoginCallback() {
      var that = this;

      this.oAuth.on('auth.login', r => {
        that.$log.debug('OAuth login event caught');

        that.DispatcherService.publish('user:request:signin:with:ping', r.authResponse.access_token);
      });
    }

    private registerOAuthLogoutCallback() {
      var that = this;

      this.oAuth.on('auth.logout', r => {
        that.$log.debug('OAuth logout event caught');
        that.DispatcherService.publish('user:request:signout', null);
      });
    }

    /**
     * Load the user based on token remembered in the cookie store
     * @param token
     * */
    private loadUser() {
      if (!this.isSignedIn()) {
        this.$log.debug('User will be not loaded - no token defined');
        return;
      }
      var token = this.$cookies.get('token');
      var provider = this.$cookies.get('provider');

      if (provider === 'ping') {
        var decodedUser = this.jwtHelper.decodeToken(token);
        this.loadUserByName(decodedUser);
      } else {
        this.loadUserByToken();
      }
    };

    /**
     * Load the user from UserStore
     * */
    private loadUserByToken() {
      var that = this;
      this.UserStore
        .getUserByToken()
        .then((user: User) => {
          that.DispatcherService.publish('user:signed:in', user);
        }, (err: any) => {
          that.DispatcherService.publish('user:signed:out', err);
        });
    };

    /**
     * @param decodedUser from JWT token
     * */
    private loadUserByName(decodedUser) {
      var that = this;
      this.UserStore
        //.getUserByName(decodedUser.userName)
        .getUserByName(decodedUser.uid)
        .then((user: User) => {
          that.DispatcherService.publish('user:signed:in', user);
        }, (err: any) => {
          that.DispatcherService.publish('user:signed:out', err);
        });
    };



  }
  angular
    .module('uchpClientAngularApp')
    .service('AuthService', AuthService);
}
