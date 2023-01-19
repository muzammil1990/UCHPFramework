/// <reference path="../../../../../app/_all.ts" />

describe('Component: ViewRepairHistoryList', () => {
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
    var ctrl = $componentController('viewRepairHistoryList', null, {});
    expect(ctrl).toBeDefined();
  });

  it('should have Vehicle information Store', () => {
    var ctrl = $componentController('viewRepairHistoryList', null, {});
    expect(ctrl['VehicleInformationStore']).toBeDefined();
  });

  it('should have dispatcher service', () => {
    var ctrl = $componentController('viewRepairHistoryList', null, {});
    expect(ctrl['dispatcherService']).toBeDefined();
  });

  it('should have getRepairHistoryVehicleInformationSubscription', () => {
    var ctrl = $componentController('viewRepairHistoryList', null, {});
    expect(ctrl['getRepairHistoryVehicleInformationSubscription']).toBeDefined();
  });

  it('should have listRepairHistoryVehicleInformationSubscription', () => {
    var ctrl = $componentController('viewRepairHistoryList', null, {});
    expect(ctrl['listRepairHistoryVehicleInformationSubscription']).toBeDefined();
  });

  it('should have modal Service', () => {
    var ctrl = $componentController('viewRepairHistoryList', null, {});
    expect(ctrl['modal']).toBeDefined();
  });


  it('should have grid options object', () => {
    var ctrl = $componentController('viewRepairHistoryList', null, {});
    expect(typeof( ctrl['gridOptions'] ) === 'object' ).toBe(true);
  });

  it('should have vehicleInformations array', () => {
    var ctrl = $componentController('viewRepairHistoryList', null, {});
    expect(Object.prototype.toString.call( ctrl['vehicleInformations'] ) === '[object Array]' ).toBe(true);
  });

  it('should have vehicleInformationsRepairHistoryList array', () => {
    var ctrl = $componentController('viewRepairHistoryList', null, {});
    expect(Object.prototype.toString.call( ctrl['vehicleInformationsRepairHistoryList'] ) === '[object Array]' ).toBe(true);
  });

});
