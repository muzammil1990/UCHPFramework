/**
 * Created by a232625 on 2017-02-07.
 */
/**
 * Created by a148094 on 18-03-2016.
 */
(function () {

  'use strict';

  angular.module('uchpClientAngularApp').config(['$stateProvider', function ($stateProvider) {
    //var company = location.href.toString().split('company=')[1] || '1';
    $stateProvider
      .state('app.dealer-outstanding-campaigns', {
        url: 'dealer-outstanding-campaigns',
        views: {
          'content@': {
            template: '<dealer-outstanding-campaigns></dealer-outstanding-campaigns>',
          }
        },
        ncyBreadcrumb: {
          //label: '{{"NAVBAR.DEALER" | translate}} > {{"NAVBAR.DEALER.DEALER_OUTSTANDING_CAMPAIGNS" | translate}}'
          label: 'Dealer Outstanding Campaigns'
        }
      });
  }]);
})();
