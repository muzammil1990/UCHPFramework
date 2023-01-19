/// <reference path="_all.ts" />


module uchpClientAngularApp {
  'use strict';
  declare var hello: any;

  var app = angular.module('uchpClientAngularApp', [
      'uchpClientAngularApp.constants',
      'ui.router',
      'ngCookies',
      'ngResource',
      'ngSanitize',
      'ngAnimate',
      'ngMessages',
      'ncy-angular-breadcrumb',
      'ui.router.stateHelper',
      'ui.bootstrap',
      'ui.grid',
      'ui.grid.pagination',
      'ui.grid.selection',
      'ui.grid.autoResize',
      'ui.grid.edit',
      'ui.grid.rowEdit',
      'pascalprecht.translate',
      'ae-datetimepicker',
      'angularMoment',
      'angular-jwt',
      'angular-loading-bar',
      'toastr',
      'naif.base64',
      'LocalStorageModule',
      'focus-if',
      'nya.bootstrap.select',
      'mwl.confirm'
    ]);

  app.config(function (OAuthProvider,
                       DispatcherServiceProvider,
                       $stateProvider,
                       $urlRouterProvider,
                       $locationProvider,
                       $translateProvider: ng.translate.ITranslateProvider,
                       $translatePartialLoaderProvider: ng.translate.ITranslatePartialLoaderProvider,
                       $httpProvider,
                       $breadcrumbProvider) {

      // TODO remove this provider as soon as HelloJS is 'angularized'
      OAuthProvider.setHelloRef(hello);


      // TODO remove this provider as soon as postal R&R is 'angularized'
      DispatcherServiceProvider.setPostalRef(postal);

      $httpProvider.interceptors.push('AuthInterceptorService');

      $locationProvider.html5Mode(true);
      $translateProvider.preferredLanguage('en_US');
      $translateProvider.fallbackLanguage('en_US');
      $translateProvider.useSanitizeValueStrategy('escaped');
      // TODO: local storage policy; how to clear when language is changed in Baldo??
      // $translateProvider.useLocalStorage();
      $translateProvider.useLoaderCache(true);
      $translatePartialLoaderProvider.addPart('start');
      $translateProvider.useLoader('$translatePartialLoader', {
        urlTemplate: 'i18n/{part}/{lang}.json'
      });

      $breadcrumbProvider.setOptions({
        template: `<ol class="breadcrumb">
          <li ng-repeat="step in steps" ng-class="{active: $last}" ng-switch="$last || !!step.abstract">
            <span ng-switch-when="true">{{ 'NAVBAR.HOME' | translate }} > {{step.ncyBreadcrumbLabel | translate}}</span>
          </li>
        </ol>`
      });
    })
    .factory('λ', function ($window) {
      return $window.contra;
    })
    .run(function ($rootScope, $templateCache) {
        $rootScope.$on('$stateChangeStart', function (event, next, nextParams, current) {
          if (next.name === 'app.signOut' && current && current.name && !current.authenticate) {
            next.referrer = current.name;
          }
        });
        var paginationTemplate = $templateCache.get('ui-grid/pagination');
        $templateCache.put('ui-grid/pagination',
          paginationTemplate.replace('class="ui-grid-pager-control-input"',
            'class="ui-grid-pager-control-input" update-on-enter ng-model-options="{updateOn: \'blur\'}"'));
      }
    ).run(function(PingFederateConfig){
      PingFederateConfig('pingFederate').login();

    });

}
