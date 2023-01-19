/**
 * Created by a148094 on 18-03-2016.
 */
(function () {

  'use strict';

  angular.module('uchpClientAngularApp').config(['$stateProvider', function ($stateProvider) {
    var company = location.href.toString().split('company=')[1] || '1';
    $stateProvider
      .state('app.print-credit-document', {
        url: 'print-credit-document',
        views: {
          'content@': {
            template: '<print-credit-document></print-credit-document>',
          }
        },
        ncyBreadcrumb: {
          label: '{{"NAVBAR.CREDITING" | translate}} > {{"NAVBAR.CREDITING.PRINT_CREDIT_DOCUMENT" | translate}}'
        }
      })
      .state('app.create-outstanding-claim-summary', {
        url: 'create-outstanding-claim-summary',
        views: {
          'content@': {
            template: '<create-outstanding-claim-summary></create-outstanding-claim-summary>',
          }
        },
        ncyBreadcrumb: {
          label: '{{"NAVBAR.CREDITING" | translate}} > ' +
          '{{"NAVBAR.CREDITING.CREATE_OUTSTANDING_CLAIM_SUMMARY." + "' + company + '" | translate}}'
        }
      })
      .state('app.print-outstanding-claim-summary', {
        url: 'print-outstanding-claim-summary',
        views: {
          'content@': {
            template: '<print-outstanding-claim-summary></print-outstanding-claim-summary>',
          }
        },
        ncyBreadcrumb: {
          label: '{{"NAVBAR.CREDITING.CREATE_OUTSTANDING_CLAIM_SUMMARY." + "' + company + '" | translate}}'
        }
      });
  }]);
})();
