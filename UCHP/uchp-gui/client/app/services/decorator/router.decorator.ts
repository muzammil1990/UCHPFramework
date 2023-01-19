/**
 * Created by a232625 on 2016-07-20.
 */
///<reference path="../../_all.ts" />


module uchpClientAngularApp {
  'use strict';

  angular.module('uchpClientAngularApp').run(function ($rootScope, $state, AuthService) {
    // Redirect to login if route requires auth and the user is not signed in, or doesn't have required role
    $rootScope.$on('$stateChangeStart', function (event, next) {
      if (!next.authenticate) {
        return;
      }

      if (typeof next.authenticate === 'string') {
        console.log(next.authenticate);
        AuthService.hasRole(next.authenticate, _.noop).then(has => {
          if (has) {
            return;
          }
          event.preventDefault();
          return AuthService.isSignedIn(_.noop).then(is => {
            $state.go(is ? 'main' : 'main'); //here we could point to sign in page if not signed
          });
        });
      } else {

        if (AuthService.isSignedIn()) {
          return;
        } else {
          event.preventDefault();
          $state.go('/');
        }
      }
    });
  });

}
