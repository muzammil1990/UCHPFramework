/**
 * Created by a148094 on 13-06-2016.
 */

(function () {

  'use strict';

  angular.module('uchpClientAngularApp').config(['$stateProvider', function ($stateProvider) {
    var company = location.href.toString().split('company=')[1] || '1';
    $stateProvider
      .state('app.list-claim-jobs', {
        url: 'list-claim-jobs',
        views: {
          'content@': {
            template: '<list-claim-jobs></list-claim-jobs>',
          }
        },
        ncyBreadcrumb: {
          label: '{{"NAVBAR.CLAIM" | translate}} > {{"NAVBAR.CLAIM.LIST_CLAIM_JOBS" | translate}}'
        }
      });
    $stateProvider
      .state('app.view-coverage', {
        url: 'view-coverage',
        views: {
          'content@': {
            template: '<view-coverage></view-coverage>',
          }
        },
        ncyBreadcrumb: {
          label: '{{"COMMON.VEHICLE." + "' + company + '" | translate}} > {{"NAVBAR.VEHICLE.VIEW_COVERAGE" | translate}}'
        }
      });
    $stateProvider
      .state('app.view-repair-history', {
        url: 'view-repair-history',
        views: {
          'content@': {
            template: '<view-repair-history></view-repair-history>',
          }
        },
        ncyBreadcrumb: {
          label: '{{"COMMON.VEHICLE." + "' + company + '" | translate}} > ' +
          '{{"NAVBAR.VEHICLE.VIEW_REPAIR_HISTORY" | translate}}'
        }
      });
    $stateProvider
      .state('app.view-campaign', {
        url: 'view-campaign',
        views: {
          'content@': {
            template: '<view-campaign></view-campaign>',
          }
        },
        ncyBreadcrumb: {
          label: '{{"COMMON.VEHICLE." + "' + company + '" | translate}} > View campaign'
        }
      });
    $stateProvider
      .state('app.new-claim-job', {
        url: 'new-claim-job',
        views: {
          'content@': {
            template: '<new-claim-job></new-claim-job>',
          }
        },
        ncyBreadcrumb: {
          label: '{{"NAVBAR.CLAIM" | translate}} > {{"NAVBAR.CLAIM.NEW_CLAIM_JOB" | translate}}'
        }
      });
    $stateProvider
      .state('app.claim-job', {
        url: 'claim-job/:companyNo/:claimRepairHeaderId/:claimJobId',
        views: {
          'content@': {
            template: '<claim-job></claim-job>',
          }
        },
        ncyBreadcrumb: {
          label: '{{"NAVBAR.CLAIM" | translate}} > {{"NAVBAR.CLAIM.JOB" | translate}}'
        }
      });
    $stateProvider
      .state('app.advanced-search', {
        url: 'advanced-search',
        views: {
          'content@': {
            template: '<advanced-search></advanced-search>',
          }
        },
        ncyBreadcrumb: {
          label: '{{"NAVBAR.CLAIM" | translate}} > {{"NAVBAR.CLAIM.ADVANCED_SEARCH" | translate}}'
        }
      });
  }]);
})();
