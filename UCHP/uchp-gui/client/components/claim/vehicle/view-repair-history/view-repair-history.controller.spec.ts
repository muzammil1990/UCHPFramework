/// <reference path="../../../../app/_all.ts" />

describe('Component: ViewRepairHistoryComponent', () => {

  var
    $componentController: IComponentControllerService,
    $rootScope: ng.IRootScopeService,
    $httpBackend: ng.IHttpBackendService;

  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService, _$compile_: any, $injector: IInjectorService) => {
      $componentController = _$componentController_;
      $rootScope = <ng.IRootScopeService>$injector.get('$rootScope');
      $httpBackend = <ng.IHttpBackendService>$injector.get('$httpBackend');
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('viewRepairHistoryList', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should run digest cycle', () => {
    var httpMock;
    httpMock = $httpBackend;
    httpMock.when('GET', /i18n/).respond('')
      .respond({});
    $rootScope.$digest();
    expect(true).toBe(true);
  });


});
