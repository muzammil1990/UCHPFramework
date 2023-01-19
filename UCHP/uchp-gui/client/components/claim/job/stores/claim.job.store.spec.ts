/// <reference path="../../../../app/_all.ts" />
import IClaimJobItemStore = uchpClientAngularApp.IClaimJobItemStore;
import ClaimJobModel = uchpClientAngularApp.ClaimJobModel;

describe('Service: ClaimJobStoreService', () => {

  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var
    ClaimJobItemStore: IClaimJobItemStore,
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
      var httpMock = $httpBackend;
      ClaimJobItemStore = <IClaimJobItemStore>$injector.get('ClaimJobItemStore');
      httpMock.when('GET', /i18n/).respond('');
    })
  );

  it('should exist', () => {
    expect(ClaimJobItemStore).toBeDefined();
  });

  it('should have a public API', () => {
    expect(ClaimJobItemStore.getClaimJob).toBeDefined();
  });


  it('should should run digest cycle', () => {
    var httpMock;
    httpMock = $httpBackend;
    httpMock.when('GET', /i18n/).respond('')
      .respond({});
    $rootScope.$digest();
    expect(true).toBe(true);
  });

});
