/// <reference path="../../../../../../app/_all.ts" />

describe('Component: listClaimJobsSearchTab', () => {
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
    var ctrl = $componentController('listClaimJobsSearchTab', null, {});
    expect(ctrl).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('listClaimJobsSearchTab', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have $scope', () => {
    var ctrl = $componentController('listClaimJobsSearchTab', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });

});
