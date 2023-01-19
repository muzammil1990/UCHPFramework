/// <reference path="../../../../app/_all.ts" />

import IOutstandingClaimSummaryDocumentStore = uchpClientAngularApp.IOutstandingClaimSummaryDocumentStore;

describe('Service: OutstandingClaimSummaryDocumentStoreService', () => {

  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var
    OutstandingClaimSummaryDocumentStore: IOutstandingClaimSummaryDocumentStore,
    RESTAdapterService: IRESTAdapterService,
    CacheService: ICacheService,
    $rootScope: ng.IRootScopeService,
    $q: ng.IQService,
    $httpBackend: ng.IHttpBackendService,
    outstandingClaimSummaryDocsCacheKey = 'OutstandingClaimSummaryDocumentStoreService.outstandingclaimsummaryDocuments',
    outstandingClaimSummaryDocsUrl = '/crediting/creditdocuments/outstandingclaimsummarydealerdocuments';

  // TODO: extend with more complete tests
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
    OutstandingClaimSummaryDocumentStore = <IOutstandingClaimSummaryDocumentStore>$injector.get('OutstandingClaimSummaryDocumentStore');
    })
  );

  it('should have a public API', () => {
    expect(OutstandingClaimSummaryDocumentStore.getOutstandingClaimSummaryDocuments).toBeDefined();
    expect(OutstandingClaimSummaryDocumentStore.getOutstandingClaimSummaryDocument).toBeDefined();
    expect(OutstandingClaimSummaryDocumentStore['createOutstandingClaimSummaryDocument']).toBeDefined();
  });

  it('should exist', () => {
    expect(OutstandingClaimSummaryDocumentStore).toBeDefined();
  });

  it('should GET documents from getOutstandingClaimSummaryDocuments', (done) => {
    var httpMock;
    httpMock = $httpBackend;
    var documentResponse = [{
      $$hashKey: "uiGrid-000L",
      __v: 0,
      _id: "5799a5443e569168242e6924",
      companyName: "VTC",
      companyNo: 1,
      creditDocumentId: "123456",
      creditOccId: 10006,
      creditingDate: "2016-04-04T12:45:17.735Z",
      creditingDocumentIssuerTypeId: 106,
      creditingDocumentTypeId: 1006,
      dealerName: "Dealer 1008 - UK",
      dealerNo: "1008",
      importerName: "Importer 1320 - UK",
      importerNo: 1320,
      make: 120,
      mimeType: "PDF",
      modifiedBy: "SystemAdmin",
      modifyTime: "2016-04-04T12:45:17.735Z"
    }];

    httpMock.when('GET', /i18n/).respond('');
    $httpBackend.whenGET(/uchp\/api\/api\/crediting\/creditdocuments\/outstandingclaimsummarydealerdocuments/).respond(function () {
      return documentResponse
    }());

    var promise = OutstandingClaimSummaryDocumentStore.getOutstandingClaimSummaryDocuments({});

    promise.then(function(resp) {
      expect(resp).toBe(documentResponse);
    });

    $rootScope.$digest();
    done();
  });

  it('should get file name from header', () => {
    var fileName = OutstandingClaimSummaryDocumentStore
      .getFileNameFromHeader('Content-Disposition: attachment; filename="example.pdf"');

    expect(fileName['name']).toBe('example');
  });

});
