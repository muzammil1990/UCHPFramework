/// <reference path="../../app/_all.ts" />
/* tslint:disable:no-bitwise */
module uchpClientAngularApp {
  'use strict';

  class NavbarController {

    /**
     *
     *
     * @static
     *
     * @memberOf NavbarController
     */
    static $inject = [
      '$translate',
      '$log',
      'UserStore',
      'SelectCompaniesStore',
      'DispatcherService',
      'NotificationService',
      '$state',
      'OAuth',
      '$rootScope',
      '$cookies',
      '$timeout',
      '$window',
      '$stateParams',
      '$scope'
    ];

    private user: User = null;
    private selectCompanies: Array < CompanyModel >;
    private selectedCompany: String;
    private menu: any;

    /**
     * Creates an instance of NavbarController.
     *
     * @param {ng.translate.ITranslateService} $translate
     * @param {ng.ILogService} $log
     * @param {IUserStore} UserStore
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {IDispatcherService} DispatcherService
     * @param {INotificationService} NotificationService
     * @param {*} $state
     * @param {HelloJSStatic} oauth
     * @param {ng.IRootScopeService} $rootScope
     * @param {ng.cookies.ICookiesService} $cookies
     * @param {ng.ITimeoutService} $timeout
     * @param {ng.IWindowService} $window
     * @param {any} $stateParams
     * @param {ng.IScope} $scope
     *
     * @memberOf NavbarController
     */
    constructor(private $translate: ng.translate.ITranslateService,
                private $log: ng.ILogService,
                private UserStore: IUserStore,
                private SelectCompaniesStore: ISelectCompaniesStore,
                private DispatcherService: IDispatcherService,
                private NotificationService: INotificationService,
                private $state: any,
                private oauth: HelloJSStatic,
                private $rootScope: ng.IRootScopeService,
                private $cookies: ng.cookies.ICookiesService,
                private $timeout: ng.ITimeoutService,
                private $window: ng.IWindowService,
                private $stateParams,
                private $scope: ng.IScope
    ) {

      var that: any = this;
      this.menu = [
        {
          'className': '',
          'icon': 'glyphicon glyphicon-home',
          'link': 'app',
          'title': 'NAVBAR.HOME'
        }, {
          'className': 'dropdown',
          'link': 'app',
          'privilege': 'CanManageClaim',
          'subtitle': [
            {
              'link': 'app.new-claim-job',
              'privilege': 'CanCreateClaimJobs',
              'SecclassName': '',
              'title': 'NAVBAR.CLAIM.NEW_CLAIM_JOB'
            }, {
              'link': 'app.list-claim-jobs',
              'privilege': 'CanEnterAndEditClaimJobs',
              'SecclassName': '',
              'title': 'NAVBAR.CLAIM.LIST_CLAIM_JOBS'
            }, {
              'link': 'app.advanced-search',
              'privilege': 'CanEnterAdvancedSearch',
              'SecclassName': '',
              'title': 'NAVBAR.CLAIM.ADVANCED_SEARCH'
            }
            // CanManageMultiUpdateClaimJobOrder
          ],
          'title': 'NAVBAR.CLAIM'
        }, {
          'className': 'dropdown',
          'companySpecific': true,
          'link': 'app',
          'privilege': 'CanManageVehicle',
          'subtitle': [
            {
              'link': 'app.view-repair-history',
              'privilege': '',
              'SecclassName': '',
              'title': 'NAVBAR.VEHICLE.VIEW_REPAIR_HISTORY'
            }, {
              'direct': true,
              'link': ($stateParams && $stateParams.company) ?
                that.$translate.instant('technicalDataURL.' + $stateParams.company) :
                that.$translate.instant('technicalDataURL.' + 1),
              'privilege': 'CanViewVehicleTechnicalData',
              'SecclassName': '',
              'title': 'NAVBAR.VEHICLE.VIEW_TECHNICAL_DATA'
            }, {
              'link': 'app.view-coverage',
              'privilege': '',
              'SecclassName': '',
              'title': 'NAVBAR.VEHICLE.VIEW_COVERAGE'
            }, {
              'direct': ($stateParams && $stateParams.company && $stateParams.company === '3') ? false : true,
              'link': ($stateParams && $stateParams.company) ?
                that.$translate.instant('viewCampaignURL.' + $stateParams.company) :
                that.$translate.instant('viewCampaignURL.' + 1),
              'privilege': '',
              'SecclassName': '',
              'title': 'NAVBAR.VEHICLE.VIEW_CAMPAIGN'
            }
            // CanViewVehicleCostHistory
          ],
          'title': 'NAVBAR.VEHICLE.'
        }, {
          'className': 'dropdown',
          'link': '',
          'privilege': 'CanManageCrediting',
          'subtitle': [
            {
              'link': 'app.print-credit-document',
              'privilege': 'CanPrintCreditDocuments',
              'SecclassName': '',
              'title': 'NAVBAR.CREDITING.PRINT_CREDIT_DOCUMENT'
            }, {
              'link': 'app.create-outstanding-claim-summary',
              'privilege': 'CanPrintOutstandingClaimSummaryDocumentCompany', // ??
              'SecclassName': '',
              'title': ($stateParams  && $stateParams.company)
                ? that.$translate.instant('NAVBAR.CREDITING.CREATE_OUTSTANDING_CLAIM_SUMMARY.' + $stateParams.company)
               : that.$translate.instant('NAVBAR.CREDITING.CREATE_OUTSTANDING_CLAIM_SUMMARY.' + 1)
            }, {
              'link': 'app.print-outstanding-claim-summary',
              'privilege': 'CanPrintCreditDocuments', //  ??
              'SecclassName': '',
              'title': ($stateParams  && $stateParams.company)
                ? that.$translate.instant('NAVBAR.CREDITING.PRINT_OUTSTANDING_CLAIM_SUMMARY.' + $stateParams.company)
               : that.$translate.instant('NAVBAR.CREDITING.PRINT_OUTSTANDING_CLAIM_SUMMARY.' + 1)
            }
            // CanApproveCreditRuns CanManageCreditSchedules
            // CanPrintOutstandingClaimSummaryDocumentDealer CanViewCreditRuns
          ],
          'title': 'NAVBAR.CREDITING'
        }
      ];

      $log.debug('NavbarController Initialized');

      let historyBackAccessor = 'historyBack';
      /**
       * #2894 ListClaimJobs: On click Close(X) button should redirect to List claim job page instead of Home page
       */
      $scope[historyBackAccessor] = function() {
        that.$state.go('app');
        if (that.$state.$current.url.prefix === '/claim-job/') {
          that.$state.go('app.list-claim-jobs');
        }
      };
      if ($stateParams && $stateParams.company && $stateParams.company !== '') {
        that.DispatcherService.publish('change:Company', $stateParams.company);
      }

      if ($stateParams && $stateParams.company && $stateParams.company === '3') {
        that.DispatcherService.publish('change:Company', $stateParams.company);
      }

      this.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();

      DispatcherService.subscribe('user:signed:in', this.signInHandler.bind(this));
      DispatcherService.subscribe('user:signed:in:via:ping', this.signInViaPingHandler.bind(this));
      DispatcherService.subscribe('user:signed:out', this.signOutHandler.bind(this));

      that
        .UserStore
        .getUser()
        .then(function (userFromStore) {
        if (userFromStore !== null && userFromStore !== undefined) {
          that.user = userFromStore;
          that.setSelectedCompany();
        }
      });

      that
        .SelectCompaniesStore
        .getSelectCompanies()
        .then(function (selectCompanies: Array < CompanyModel >) {
        if (selectCompanies !== null && selectCompanies !== undefined) {
          that.selectCompanies = selectCompanies;
        }
      });
    }

    /**
     * Open start page in a new window
     * */
    /**
     *
     *
     * @param {any} id
     * @returns {*}
     *
     * @memberOf NavbarController
     */
    openNewWindow(id): any {
      var root = '/uchp/client/';
      var url = decodeURIComponent(window.location.origin + root + '?company=' + id);
      this.$window.open(url, '_blank');
    }

    /**
     * Set initial company from user settings
     * */
    /**
     *
     *
     * @returns {*}
     *
     * @memberOf NavbarController
     */
    setSelectedCompany(): any {
      this.selectedCompany = this.user.usersetting[0].usersettingcompanyno.toString();
      this.reflectChangeOfSelectedCompany();
    }

    /**
     *
     *
     * @param {any} company
     * @returns {*}
     *
     * @memberOf NavbarController
     */
    setPreferredCompany(company): any {
      var companyNumber = +company;
      var that = this;
      that
        .UserStore
        .setPreferredCompany(+companyNumber)
        .then(function () {
          that.NotificationService.sendSuccess('Preferred company changed to: ' +
            that.selectCompanies[companyNumber - 1].name);
            that.$log.debug('Preferred company set to: ' + companyNumber);
      }, (err) => {
          that.NotificationService.sendError('Error in changing company: ' + JSON.stringify(err));
          that.$log.debug('Preferred company set error: ' + err);
        });
    }

    /**
     *
     *
     * @returns {*}
     *
     * @memberOf NavbarController
     */
    reflectChangeOfSelectedCompany(): any {
      this.DispatcherService.publish('change:Company', this.selectedCompany);
    }

    /**
     *
     *
     * @returns {boolean}
     *
     * @memberOf NavbarController
     */
    isUserSignedIn(): boolean {
      //return true;
      return this.user !== null;
    }

    /**
     * Check if a feature should be shown, based on privileges
     * */
    /**
     *
     *
     * @param {any} privilege
     * @returns {boolean}
     *
     * @memberOf NavbarController
     */
    isPrivileged(privilege): boolean {
      //return true;
      var that = this;
      return that.user && (!privilege || that.user.privilegeList.indexOf(privilege) !== -1);
    }

    /**
     *
     *
     * @returns {boolean}
     *
     * @memberOf NavbarController
     */
    isUserAuthorised(): boolean {
      return true;
      /*let user_setting_key = 'usersetting';
      let value_access_key = 'value';
      if (this.user === null || typeof this.user[user_setting_key] === 'undefined') {
        return false;
      } else {
        return ~ this.user[user_setting_key][0][value_access_key].indexOf('-1') ? true : false;
      }*/
    }

    /**
     * Handler invoked on sign in process.
     * */
    /**
     *
     *
     * @param {any} userFromStore
     *
     * @memberOf NavbarController
     */
    signInHandler(userFromStore) {
      this.user = userFromStore;
    }

    /**
     *
     *
     * @param {any} userFromStore
     *
     * @memberOf NavbarController
     */
    signInViaPingHandler(userFromStore) {
      this.user = userFromStore;
      this.$rootScope.$digest();
    }

    /**
     * Handler for sign out process.
     * */
    /**
     *
     *
     *
     * @memberOf NavbarController
     */
    signOutHandler() {
      this.$timeout(() => {
        this.user = null;
        this.$state.go('app');
      }, 0);
    }

    /**
     * Send notification to interested parties that user has decided to sign out.
     * */
    /**
     *
     *
     *
     * @memberOf NavbarController
     */
    signOut(): void {
      if (this.$cookies.get('provider') === 'ping') {
        this.signOutWithPing();
      } else {
        this.DispatcherService.publish('user:request:signout', null);
      }
      var cookies = document.cookie.split(';');

      for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf('=');
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:00 GMT';
      }
      window.location.reload();
    }

    /**
     *
     *
     * @returns {Boolean}
     *
     * @memberOf NavbarController
     */
    isHistoryBackAvailable(): Boolean {
      return this.$state.$current.name !== 'app';
    }

    /**
     * Sign out with Ping Federate as Authorization Server.
     */
    /**
     *
     *
     *
     * @memberOf NavbarController
     */
    signOutWithPing(): void {
      const that: any = this;
      that.oauth().logout('pingFederate', {force: true});
    }

    /**
     * Sign in with Ping Federate as Authorization Server.
     * */
    /**
     *
     *
     *
     * @memberOf NavbarController
     */
    signInWithPing(): void {
      this.oauth('pingFederate').login();
    }

  }

  angular.module('uchpClientAngularApp').component('navbar', {
    controller  : [
      '$translate',
      '$log',
      'UserStore',
      'SelectCompaniesStore',
      'DispatcherService',
      'NotificationService',
      '$state',
      'OAuth',
      '$rootScope',
      '$cookies',
      '$timeout',
      '$window',
      '$stateParams',
      '$scope',
      NavbarController
    ],
    controllerAs: 'ctrl',
    templateUrl: 'components/navbar/navbar.html'
  });
}
