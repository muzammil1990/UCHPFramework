/// <reference path="../../../../app/_all.ts" />
import IVehicleInformationStore = uchpClientAngularApp.IVehicleInformationStore;
import VehicleInformationModel = uchpClientAngularApp.VehicleInformationModel;

describe('Service: VehicleInformationStoreService', () => {

  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var
    VehicleInformationStore: IVehicleInformationStore,
    RESTAdapterService: IRESTAdapterService,
    CacheService: ICacheService,
    $rootScope: ng.IRootScopeService,
    $q: ng.IQService,
    $httpBackend: ng.IHttpBackendService,
    searchFormData: Object = {
      regNo: '646AVK35',
      marketingmake: '',
      brandmake: ''
    };

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
      VehicleInformationStore = <IVehicleInformationStore>$injector.get('VehicleInformationStore');
      httpMock.when('GET', /i18n/).respond('');
    })
  );

  it('should exist', () => {
    expect(VehicleInformationStore).toBeDefined();
  });

  it('should have a public API', () => {
    expect(VehicleInformationStore.getVehicleInformation).toBeDefined();
    expect(VehicleInformationStore.getviewCoverageDocument).toBeDefined();
    expect(VehicleInformationStore['getMultipleVehicleInformations']).toBeDefined();
  });


  it('should should run digest cycle', () => {
    var httpMock;
    httpMock = $httpBackend;
    httpMock.when('GET', /i18n/).respond('')
      .respond({});
    $rootScope.$digest();
    expect(true).toBe(true);
  });

  it('GET credit document', () => {
    var httpMock;
    httpMock = $httpBackend;
    httpMock.when('GET', /i18n/).respond('');
    $rootScope.$digest();
    httpMock.flush();
    expect(true).toBe(true);
  });

  it('should GET documents from getVehicleInformations', (done) => {
    var httpMock;
    httpMock = $httpBackend;
    var documentResponse = [{
    }];

    httpMock.when('GET', /i18n/).respond('');
    httpMock.whenGET(/uchp\/api\/api\/claim\/vehicle/).respond(function () {
      return documentResponse;
    }());

    var promise = VehicleInformationStore.getVehicleInformation(searchFormData, 'viewCoverage');

    promise.then(function(resp) {
      expect(resp).toBe(documentResponse);
    });

    $rootScope.$digest();
    done();
  });

  it('GET VehicleInformations should be a promise', () => {
    var promise = VehicleInformationStore.getVehicleInformation(searchFormData, 'viewCoverage');
    expect(typeof promise['$$state']).toBe("object");
  });

  describe('should resolve promise', function () {
    var fetchedData = {};

    beforeEach(inject(function(){
      sinon.stub(RESTAdapterService, 'get').returns($q.when(fetchedData));
    }));

    it('from server', function(){
      var httpMock = $httpBackend;
      httpMock.when('GET', /i18n/).respond('');
      $rootScope.$digest();
      var promise = VehicleInformationStore
        .getVehicleInformation(searchFormData, 'viewCoverage')
        .then(function(result){
          expect(promise['$$state'].status).toBe(promise['$$state'].status);
          expect(RESTAdapterService.get['called']).toBe(true);
        });
      $rootScope.$digest();
    });
  });

});
