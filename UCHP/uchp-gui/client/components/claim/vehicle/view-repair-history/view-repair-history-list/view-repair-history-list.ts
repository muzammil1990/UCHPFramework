
'use strict';

angular.module('uchpClientAngularApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('view-repair-history-list', {
        url: '/view-repair-history-list',
        template: '<view-repair-history-list></view-repair-history-list>'
      });
  });

