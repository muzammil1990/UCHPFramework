/**
 * Created by a148094 on 18-03-2016.
 */

(function () {

  'use strict';

  angular
    .module('uchpClientAngularApp')
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
      /**
       * Default route
       **/
      $urlRouterProvider
        .when('app', '');

      /**
       * Default route for invalid routes
       **/
      $urlRouterProvider
        .otherwise('app');

      /**
       * Defines main layout which contains three view ports:
       * - navbar : for static navigation component
       * - content : for dynamic content
       * - footer : for static information component
       **/
      $stateProvider
        .state('app', {
          url: '/?company',
          views: {
            'navbar': {
              template: '<navbar></navbar>'
            },
            'content': {
              template: '<main></main>'
            },
            'footer': {
              template: '<footer></footer>'
            }
          },
          ncyBreadcrumb: {
            label: ''
          }
       });
      $stateProvider
        .state('app.help', {
          url: 'help',
          views: {
            'content@': {
              template: '<help></help>',
            }
          },
          ncyBreadcrumb: {
            label: '{{ "NAVBAR.HELP" | translate}} > {{ "BREADCRUMB.ABOUT" | translate}}'
          }
        });
    }]);
})();
