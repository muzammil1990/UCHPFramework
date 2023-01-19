
'use strict';

angular.module('uchpClientAngularApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('view-repair-history-vehicle-informations', {
        url: '/view-repair-history-vehicle-informations',
        template: '<view-repair-history-vehicle-informations></view-repair-history-vehicle-informations>'
      });
  });

