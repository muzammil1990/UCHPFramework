/**
 * Created by a232625 on 2016-07-20.
 */
/// <reference path="../../_all.ts" />

import IAuthService = uchpClientAngularApp.IAuthService;
describe('Service: AuthService', () => {
  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var AuthService: IAuthService;

  // initialize service
  beforeEach(inject(($injector: angular.auto.IInjectorService) => {
      AuthService = <IAuthService>$injector.get('AuthService');
    })
  );

  it('should exist', () => {
    expect(AuthService).toBeDefined();
  });

  it('should have a public API', () => {
    expect(AuthService.isSignedIn).toBeDefined();
  });

});
