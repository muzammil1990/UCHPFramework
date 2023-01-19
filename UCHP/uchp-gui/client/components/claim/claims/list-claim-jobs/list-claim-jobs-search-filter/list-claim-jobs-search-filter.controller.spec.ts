/// <reference path="../../../../../app/_all.ts" />

describe('Component: listClaimJobsSearchFilter', () => {
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
    var ctrl = $componentController('listClaimJobsSearchFilter', null, {});
    expect(ctrl).toBeDefined();
  });

  it('should have Standard Claim Search Result Store', () => {
    var ctrl = $componentController('listClaimJobsSearchFilter', null, {});
    expect(ctrl['StandardClaimSearchResultStore']).toBeDefined();
  });

  it('should have Claim Search Criteria Store', () => {
    var ctrl = $componentController('listClaimJobsSearchFilter', null, {});
    expect(ctrl['ClaimSearchCriteriaStore']).toBeDefined();
  });

  it('should have Standard Claim Search Object', () => {
    var ctrl = $componentController('listClaimJobsSearchFilter', null, {});
    expect(typeof( ctrl['listClaimJobsForm'] ) === 'object' ).toBe(true);
  });
  it('should have DispatcherService', () => {
    var ctrl = $componentController('listClaimJobsSearchFilter', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });
  it('should have User Store', () => {
    var ctrl = $componentController('listClaimJobsSearchFilter', null, {});
    expect(ctrl['UserStore']).toBeDefined();
  });
  it('should have Select Companies Store', () => {
    var ctrl = $componentController('listClaimJobsSearchFilter', null, {});
    expect(ctrl['SelectCompaniesStore']).toBeDefined();
  });
  it('should have $translate', () => {
    var ctrl = $componentController('listClaimJobsSearchFilter', null, {});
    expect(ctrl['$translate']).toBeDefined();
  });
  it('should have $log', () => {
    var ctrl = $componentController('listClaimJobsSearchFilter', null, {});
    expect(ctrl['$log']).toBeDefined();
  });
  it('should have $scope', () => {
    var ctrl = $componentController('listClaimJobsSearchFilter', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });
});
