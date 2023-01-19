/// <reference path="../../../../../app/_all.ts" />

describe('Component: ViewRepairHistorySearchFilter', () => {
  var
    $componentController: IComponentControllerService,
    $rootScope: ng.IRootScopeService,
    $q: ng.IQService,
    $httpBackend: ng.IHttpBackendService;

  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach( function() {
    inject((_$componentController_: IComponentControllerService, $injector: IInjectorService) => {
      $componentController = _$componentController_;
      $q = <ng.IQService>$injector.get('$q');
      $rootScope = <ng.IRootScopeService>$injector.get('$rootScope');
      $httpBackend = <ng.IHttpBackendService>$injector.get('$httpBackend');
    });
  });

  it('should exist', () => {
    var ctrl = $componentController('viewRepairHistorySearchFilter', null, {});
    expect(ctrl).toBeDefined();
  });

  it('should have Vehicle information Store', () => {
    var ctrl = $componentController('viewRepairHistorySearchFilter', null, {});
    expect(ctrl['VehicleInformationStore']).toBeDefined();
  });

  it('should have Brand Store', () => {
    var ctrl = $componentController('viewRepairHistorySearchFilter', null, {});
    expect(ctrl['BrandStore']).toBeDefined();
  });

  it('should have view Repair history object', () => {
    var ctrl = $componentController('viewRepairHistorySearchFilter', null, {});
    expect(typeof( ctrl['viewRepairHistoryForm'] ) === 'object' ).toBe(true);
  });

});
