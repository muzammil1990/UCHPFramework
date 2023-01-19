/// <reference path="../../../../../app/_all.ts" />

describe('Component: listClaimJobsList', () => {
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

  it('ctrl should exist', () => {
    var ctrl = $componentController('listClaimJobsList', null, {});
    expect(ctrl).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('listClaimJobsList', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have $log', () => {
    var ctrl = $componentController('listClaimJobsList', null, {});
    expect(ctrl['$log']).toBeDefined();
  });

  it('should have $scope', () => {
    var ctrl = $componentController('listClaimJobsList', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });

});
