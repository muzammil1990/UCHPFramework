/// <reference path="../../../../../app/_all.ts" />

describe('Component: ViewRepairHistoryVehicleInformations', () => {
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
    var ctrl = $componentController('viewRepairHistoryVehicleInformations', null, {});
    expect(ctrl).toBeDefined();
  });

  it('should have Vehicle information Store', () => {
    var ctrl = $componentController('viewRepairHistoryVehicleInformations', null, {});
    expect(ctrl['VehicleInformationStore']).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('viewRepairHistoryVehicleInformations', null, {});
    expect(ctrl['dispatcherService']).toBeDefined();
  });

  it('should have getVehicleInformationSubscription method', () => {
    var ctrl = $componentController('viewRepairHistoryVehicleInformations', null, {});
    expect(ctrl['getVehicleInformationSubscription']).toBeDefined();
  });

  it('should have viewRepairHistoryVehicleInformationsArray', () => {
    var ctrl = $componentController('viewRepairHistoryVehicleInformations', null, {});
    expect(Object.prototype.toString.call( ctrl['vehicleHeaderInformations'] ) === '[object Array]' ).toBe(true);
  });

});
