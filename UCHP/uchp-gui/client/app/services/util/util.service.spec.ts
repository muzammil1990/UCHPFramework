/**
 * Created by a232625 on 2016-07-18.
 */
/// <reference path="../../_all.ts" />

import IUtilService = uchpClientAngularApp.IUtilService;
describe('Service: AuthService', () => {
  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var UtilService: IUtilService;

  // initialize service
  beforeEach(inject(($injector: angular.auto.IInjectorService) => {
      UtilService = <IUtilService>$injector.get('UtilService');
    })
  );

  it('should exist', () => {
    expect(UtilService).toBeDefined();
  });

  it('should have a public API', () => {
    expect(UtilService.safeCb).toBeDefined();
    expect(UtilService.urlParse).toBeDefined();
    expect(UtilService.isSameOrigin).toBeDefined();
  });

});
