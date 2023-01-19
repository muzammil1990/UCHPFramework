/**
 * Created by a232625 on 2016-07-19.
 */
/// <reference path="../../../app/_all.ts" />

import IUserStore = uchpClientAngularApp.IUserStore;
//import IInjectorService = angular.auto.IInjectorService;
describe('Service: UserStore', () => {
  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var UserStore: IUserStore;

  // initialize service
  beforeEach(inject(($injector: angular.auto.IInjectorService) => {
      UserStore = <IUserStore>$injector.get('UserStore');
    })
  );

  it('should exist', () => {
    expect(UserStore).toBeDefined();
  });

  it('should have a public API', () => {
    expect(UserStore.getUserByName).toBeDefined();
    expect(UserStore.getUserByToken).toBeDefined();
    expect(UserStore.getUser).toBeDefined();
    expect(UserStore.getAllUsers).toBeDefined();
  });

});
