/// <reference path="../../../../../../app/_all.ts" />

describe('Component: listClaimJobsAppealStatus', () => {
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
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl).toBeDefined();
  });

  it('should have Select Companies Store', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['SelectCompaniesStore']).toBeDefined();
  });

  it('should have Dispatcher ervice', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have Notification Service', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['NotificationService']).toBeDefined();
  });

  it('should have localStorage Service', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['localStorageService']).toBeDefined();
  });

  it('should have Mi Document Store', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['MiDocumentStore']).toBeDefined();
  });

  it('should have Print Delivery Note Document Store', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['PrintDeliveryNoteDocumentStore']).toBeDefined();
  });

  it('should have Tab Claim Search Result Store', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['TabClaimSearchResultStore']).toBeDefined();
  });

  it('should have $log', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['$log']).toBeDefined();
  });

  it('should have $scope', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });

  it('should have $translate', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['$translate']).toBeDefined();
  });

  it('should have User Store', () => {
    var ctrl = $componentController('listClaimJobsAppealStatus', null, {});
    expect(ctrl['UserStore']).toBeDefined();
  });
});

