/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  function updateOnEnter() {
    return {
      restrict: 'A',
      require: 'ngModel',
      /**
       * 
       * 
       * @param {any} scope
       * @param {any} element
       * @param {any} attrs
       * @param {any} ctrl
       */
      link: function(scope, element, attrs, ctrl) {
        element.bind('keypress', function(ev) {
          if (ev.which === 13) {
            ctrl.$commitViewValue();
            scope.$apply(ctrl.$setTouched);
          }
        });
      }
    };
  }

  angular.module('uchpClientAngularApp')
    .directive('updateOnEnter', updateOnEnter);
}
