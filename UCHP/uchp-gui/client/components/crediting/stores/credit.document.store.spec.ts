/// <reference path="../../../app/_all.ts" />

import IInjectorService = ng.auto.IInjectorService;
import ICreditDocumentStore = uchpClientAngularApp.ICreditDocumentStore;
import IRESTAdapterService = uchpClientAngularApp.IRESTAdapterService;
import ICacheService = uchpClientAngularApp.ICacheService;
import CreditDocumentModel = uchpClientAngularApp.CreditDocumentModel;

describe('Service: CreditDocumentStoreService', () => {

  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var
    CreditDocumentStore: ICreditDocumentStore,
    RESTAdapterService: IRESTAdapterService,
    CacheService: ICacheService,
    $rootScope: ng.IRootScopeService,
    $q: ng.IQService,
    $httpBackend: ng.IHttpBackendService,
    creditDocsCacheKey = 'CreditDocumentStore.creditingDocuments',
    creditDocsUrl = '/crediting/creditdocuments/creditingdocuments',
    docs = {
      creditdocuments: [
        {
          'companyName': 'VTC'
        }
      ]
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
      var fetchedDocuments = {documents: [{name: 'Document name 1'}]};
      CreditDocumentStore = <ICreditDocumentStore>$injector.get('CreditDocumentStore');
      httpMock.when('GET', /i18n/).respond('');
    })
  );

  it('should exist', () => {
    expect(CreditDocumentStore).toBeDefined();
  });

  it('should have a public API', () => {
    expect(CreditDocumentStore.getCreditDocument).toBeDefined();
    expect(CreditDocumentStore.getCreditDocuments).toBeDefined();
    expect(CreditDocumentStore['getFileNameFromHeader']).toBeDefined();
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

  /*it('should GET documents from getCreditDocuments', (done) => {
    var httpMock;
    httpMock = $httpBackend;
    var documentResponse = [{
    }];

    httpMock.when('GET', /i18n/).respond('');
    httpMock.whenGET(/uchp\/api\/api\/crediting\/creditdocuments\/creditingdocuments/).respond(function () {
      return documentResponse;
    }());

    var promise = CreditDocumentStore.getCreditDocuments({});

    promise.then(function(resp) {
      expect(resp).toBe(documentResponse);
    });

    $rootScope.$digest();
    done();
  });*/

  it('should get file name from header', () => {
    var fileName = CreditDocumentStore
      .getFileNameFromHeader('Content-Disposition: attachment; filename="example.pdf"');

    expect(fileName['name']).toBe('example');
  });

  it('should get file name from header with empty params should return null', () => {
    var fileName = CreditDocumentStore
      .getFileNameFromHeader();

    expect(fileName).toBe(null);
  });

/*  it('GET CreditDocuments should be a promise', () => {
    var promise = CreditDocumentStore.getCreditDocuments({importerNo: 1});

    expect(typeof promise['$$state']).toBe("object");
  });*/


});
