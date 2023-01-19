/**
 * Created by a232625 on 2016-07-19.
 */
/// <reference path="../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';
  import IJwtHelper = angular.jwt.IJwtHelper;


  class UserStore implements IUserStore {

    private CACHE_KEY_PREFIX = 'UserDataService.user';
    private OAUTH_LOCAL = '/auth/local';
    private CURRENT_USER_URL = '/common/user/usercontext/me';
    private USER_URL = '/common/user/usercontext/:id';
    private USERS_URL = '/common/user/usercontext';
    private PREFERRED_COMPANY_URL = '/common/user/setpreferredcompany';

    static $inject = [
      '$q',
      'RESTAdapterService',
      'DispatcherService',
      '$log',
      'λ',
      '$cookies',
      'CacheService',
      'jwtHelper',
      '$translate'
    ];

    /**
     * Creates an instance of UserStore.
     * 
     * @param {ng.IQService} $q
     * @param {IRESTAdapterService} RESTAdapterService
     * @param {IDispatcherService} DispatcherService
     * @param {ng.ILogService} $log
     * @param {*} λ
     * @param {ng.cookies.ICookiesService} $cookies
     * @param {ICacheService} CacheService
     * @param {IJwtHelper} jwtHelper
     * @param {*} $translate
     * 
     * @memberOf UserStore
     */
    constructor(private $q: ng.IQService,
                private RESTAdapterService: IRESTAdapterService,
                private DispatcherService: IDispatcherService,
                private $log: ng.ILogService,
                private λ: any,
                private $cookies: ng.cookies.ICookiesService,
                private CacheService: ICacheService,
                private jwtHelper: IJwtHelper,
                private $translate: any) {

      $log.debug('Initializing UserStore...');
      DispatcherService.subscribe('user:request:signin', this.signIn.bind(this));
      DispatcherService.subscribe('user:request:signin:with:ping', this.signInWithPing.bind(this));
      DispatcherService.subscribe('user:request:signin:token', this.signInWithToken.bind(this));

      DispatcherService.subscribe('user:request:signout', this.signOut.bind(this));
      DispatcherService.subscribe('user:request:signup', this.signUp.bind(this));
    }

    /**
     * Set preferred company.
     * @param companyNumber: number
     * */
    setPreferredCompany(companyNumber: number): ng.IPromise<any> {
      var
        that = this,
        deferredObject = that.$q.defer();

      that.RESTAdapterService.post(that.PREFERRED_COMPANY_URL,
          {
            headers: {
            userCompanyNo: companyNumber
          }
        }, {})
        .then(() => {
          deferredObject.resolve();
        })
        .catch((err: any) => {
          deferredObject.reject(err);
        });

      return deferredObject.promise;
    }

    /**
     * Sign up a new user.
     * @param user: User
     * @param envelope : any
     * */
    signUp(user: User, envelope: any): void {
      var that = this;
      this
        .λ
        .waterfall([
          function (next) {
            that.createUser(user)
              .then((createdUser: User) => {
                next(null, createdUser);
              })
              .catch(err => {
                next(err);
              });
          }
        ], (err, createdUser: User) => {
          if (err || !createdUser) {
            envelope.reply(err);
            that.DispatcherService.publish('user:signed:up:error', {});
          } else {
            envelope.reply(null, createdUser);
          }
        });
    }

    signOut(): void {
      this.CacheService.put(this.CACHE_KEY_PREFIX, null);
      this.$cookies.remove('token');
      this.$cookies.remove('provider');
      this.$cookies.remove('connect.sid');
      this.DispatcherService.publish('user:signed:out', {});
    }


    /**
     * Sigin in user via local security flow.
     * @param user: User
     * @param envelope : any
     * */
    signIn(user: User, envelope: any): void {
      var that = this;
      this
        .λ
        .waterfall([
          function (next) {
            that.RESTAdapterService
              .post(that.OAUTH_LOCAL, {}, user)
              .then((res: any) => {
                that.$cookies.put('token', res.token, {'path': '/'});
                next(null, res.token);
              })
              .catch(err => {
                next(err);
              });
          },
          function (token, next) {
            var userDecoded = that.jwtHelper.decodeToken(token);
            next(null, userDecoded);
          },
          function (userDecoded, next) {
            that.getUserById(userDecoded._id)
              .then((userByToken: User) => {
                next(null, userByToken);
              })
              .catch(err => {
                next(err);
              });
          }
        ], (err, userByToken) => {
          if (err || !userByToken) {
            envelope.reply(err);
            that.DispatcherService.publish('user:signed:in:error', {});
          } else {
            that.CacheService.put(this.CACHE_KEY_PREFIX, userByToken);
            envelope.reply(null, userByToken);
          }
        });

    }

    /**
     * Signin in user via local security flow using security Token.
     * @param userToken: string
     * @param envelope : any
     * */
    signInWithToken(userToken: string, envelope: any): void {
      var that = this;

      // Because interceptor will be invoked
      that.$cookies.put('token', userToken, {'path': '/'});

      this
        .λ
        .waterfall([
          function (next) {
            var userDecoded = that.jwtHelper.decodeToken(userToken);
            next(null, userDecoded);
          },
          function (userDecoded, next) {
            that.getUserById(userDecoded._id)
              .then((userByToken: User) => {
                next(null, userByToken);
              })
              .catch(err => {
                next(err);
              });
          }
        ], (err, userByToken) => {
          if (err || !userByToken) {
            envelope.reply(err);
            that.DispatcherService.publish('user:signed:in:error', {});
          } else {
            that.CacheService.put(this.CACHE_KEY_PREFIX, userByToken);
            envelope.reply(null, userByToken);
          }
        });

    }

    /**
     * Sign in with Ping Federate Oauth flow.
     * @param JWT token received from Ping Federate
     * */
    signInWithPing(token: string): void {
      var that = this;
      // Because interceptor will be invoked
      that.$cookies.put('token', token, {'path': '/'});
      that.$cookies.put('provider', 'ping', {'path': '/'});

      that
        .λ.waterfall([
        function (next) {
          var decodedUser = that.jwtHelper.decodeToken(token);
          next(null, decodedUser);
        },
        function (user, next) {
          //that.getUserByName(user.userName)
          that.getUserByName(user.uid)
            .then((userByName: User) => {
              next(null, userByName);
            })
            .catch(err => {
              next(err);
            });
        }
      ], function (err, userByName) {
        if (err || !userByName) {
          that.DispatcherService.publish('user:signed:in:error', {});
        } else {
          that.CacheService.put(that.CACHE_KEY_PREFIX, userByName);
          that.DispatcherService.publish('user:signed:in:via:ping', userByName);
        }
      });
    }

    /**
     *  Returns user from the cache.
     * @return ng.IPromise<User>
     * */
    getUser(): ng.IPromise<User> {
      var deferred = this.$q.defer(),
        cachedData = this.CacheService.get(this.CACHE_KEY_PREFIX),
        user = null;

      if (angular.isDefined(cachedData) && cachedData !== null) {
        user = cachedData;
      }

      deferred.resolve(angular.copy(user));
      return deferred.promise;
    }

    getAllUsers(): ng.IPromise<Array<User>> {
      var
        that = this,
        deferredObject = that.$q.defer();

      that.RESTAdapterService.get(that.USERS_URL, {})
        .then((result: any) => {
          deferredObject.resolve(result.users);
        })
        .catch((err: any) => {
          deferredObject.reject(err);
        });

      return deferredObject.promise;
    }


    /**
     * Returns user from the backend based on local token stored.
     * @return ng.IPromise<User>
     * */
    getUserByToken(): ng.IPromise<User> {
      var
        that = this,
        deferredObject = that.$q.defer();
      const UserAny: any = User;

      that.RESTAdapterService
        .get(that.CURRENT_USER_URL, {})
        .then((user: User) => {
          that
            .CacheService
            .put(that.CACHE_KEY_PREFIX,
              new UserAny(user.userName, user.firstName, user.lastName, null,
                user.email, user.usersetting, user.privilegeList, user.roleList));
          deferredObject.resolve(user);
        })
        .catch((err: any) => {
          that.CacheService.put(that.CACHE_KEY_PREFIX, null);
          that.$cookies.remove('token');
          deferredObject.reject(err);
        });

      return deferredObject.promise;
    }

    /**
     * Get user by user id decoded from ping federate access token.
     * @param userId
     * @return User
     * */
    getUserByName(userName: string): ng.IPromise<User> {
      var
        that = this,
        deferredObject = that.$q.defer();
      const UserAny: any = User;

      that.RESTAdapterService.get(that.USER_URL, {params: {username: userName}})
        .then((user: User) => {
          that
            .CacheService
            .put(that.CACHE_KEY_PREFIX,
              new UserAny(user.userName, user.firstName, user.lastName, null,
                user.email, user.usersetting, user.privilegeList, user.roleList));
          deferredObject.resolve(user);
          // set preferred language
          if (typeof user !== 'undefined' && typeof that.$translate !== 'undefined') {
            let userInfoAccessor = 'userInfo', localeAccessor = 'locale', momentAccessor = 'moment';
            that.$translate.use(user[userInfoAccessor][localeAccessor]);
            window[momentAccessor].locale(user[userInfoAccessor][localeAccessor].slice(0, 2));
          }
        })
        .catch((err: any) => {
          that.CacheService.put(that.CACHE_KEY_PREFIX, null);
          deferredObject.reject(err);
        });

      return deferredObject.promise;
    }


    /**
     * Get user by user id decoded from ping federate access token.
     * @param userId
     * @return User
     * */
    getUserById(userId: string): ng.IPromise<User> {
      var
        that = this,
        deferredObject = that.$q.defer();
      const UserAny: any = User;

      that.RESTAdapterService.get(that.USER_URL, {params: {id: userId}})
        .then((user: User) => {
          that
            .CacheService
            .put(that.CACHE_KEY_PREFIX, new UserAny(user.userName, user.firstName, user.lastName, null,
              user.email, user.usersetting, user.privilegeList, user.roleList));
          deferredObject.resolve(user);
        })
        .catch((err: any) => {
          that.CacheService.put(that.CACHE_KEY_PREFIX, null);
          deferredObject.reject(err);
        });

      return deferredObject.promise;
    }

    /**
     * Create a new user. This method could be used in Sign up scenario.
     * @param user
     * @return User
     * */
    private createUser(user: User): ng.IPromise<User> {
      var
        that = this,
        deferredObject = that.$q.defer();

      that.RESTAdapterService.post(that.USERS_URL, {}, user)
        .then((createdUser: User) => {
          deferredObject.resolve(createdUser);
        })
        .catch((err: any) => {
          deferredObject.reject(err);
        });

      return deferredObject.promise;
    }

  }


  angular
    .module('uchpClientAngularApp')
    .service('UserStore', UserStore);
}
