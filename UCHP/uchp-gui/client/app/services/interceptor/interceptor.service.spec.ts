/**
 * Created by a232625 on 2016-07-18.
 */
/// <reference path="../../_all.ts" />

import IAuthInterceptorService = uchpClientAngularApp.IAuthInterceptorService;
describe('Service: AuthInterceptorService', () => {
  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var AuthInterceptorService: IAuthInterceptorService;

  // initialize service
  beforeEach(inject(($injector: angular.auto.IInjectorService) => {
      AuthInterceptorService = <IAuthInterceptorService>$injector.get('AuthInterceptorService');
    })
  );

  it('should exist', () => {
    expect(AuthInterceptorService).toBeDefined();
  });

  it('should have a public API', () => {
    expect(AuthInterceptorService.request).toBeDefined();
    expect(AuthInterceptorService.responseError).toBeDefined();
  });

});
