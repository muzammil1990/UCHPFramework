'use strict';

angular.module('uchpClientAngularApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('view-repair-history', {
        url: '/view-repair-history',
        template: '<view-repair-history></view-repair-history>'
      });
  });
