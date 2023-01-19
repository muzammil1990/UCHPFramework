/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  function filenameFilter() {
    return function (mimeType: string) {
      if (!mimeType) {
        return '';
      }
      if (mimeType === 'application/pdf') {
        return 'PDF';
      }
      if (mimeType === 'application/vnd.ms-excel') {
        return 'EXCEL';
      }
      return mimeType;
    };
  }


  angular.module('uchpClientAngularApp')
    .filter('filename', filenameFilter);
}
