/**
 * Created by a232625 on 2017-01-20.
 */
/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  function sceFilter($sce) {
    return $sce.trustAsHtml;
  }


  angular.module('uchpClientAngularApp')
    .filter('sce', sceFilter);
}
