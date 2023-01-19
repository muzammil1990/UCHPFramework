/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  function clearOnDisabled() {
    return {
      restrict: 'A',
      scope: {
        disabled: '=ngDisabled',
        ngModel: '='
      },
      /**
       * 
       * 
       * @param {any} scope
       * @param {any} elem
       * @param {any} attrs
       */
      link: function (scope, elem, attrs) {
        var lastValue = null;
        scope.$parent.$watch(attrs.ngDisabled, function (val) {
          if (!attrs.disabled) {
            lastValue = elem.val();
            scope.ngModel = undefined;
          }  else {
            if (lastValue) {
              scope.ngModel = lastValue;
            }
          }
        });
      }
    };
  }

  angular.module('uchpClientAngularApp')
    .directive('clearOnDisabled', clearOnDisabled);
}
