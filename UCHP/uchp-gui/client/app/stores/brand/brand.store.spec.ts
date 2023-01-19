/**
 * Created by a232625 on 2016-07-13.
 */
/// <reference path="../../_all.ts" />

import IBrandStore = uchpClientAngularApp.IBrandStore;

describe('Service: BrandStoreService', () => {

  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var
    BrandStore: IBrandStore,
    RESTAdapterService: IRESTAdapterService,
    CacheService: ICacheService,
    $rootScope: ng.IRootScopeService,
    $q: ng.IQService,
    $httpBackend: ng.IHttpBackendService;

  // instantiate dependencies
  beforeEach(inject(($injector: IInjectorService) => {
      RESTAdapterService = <IRESTAdapterService>$injector.get('RESTAdapterService');
      CacheService = <ICacheService>$injector.get('CacheService');
      $q = <ng.IQService>$injector.get('$q');
      $rootScope = <ng.IRootScopeService>$injector.get('$rootScope');
      $httpBackend = <ng.IHttpBackendService>$injector.get('$httpBackend');
    })
  );

  // initialize service
  beforeEach(inject(($injector: IInjectorService) => {
      BrandStore = <IBrandStore>$injector.get('BrandStore');
    })
  );

  it('should exist', () => {
    expect(BrandStore).toBeDefined();
  });

});
