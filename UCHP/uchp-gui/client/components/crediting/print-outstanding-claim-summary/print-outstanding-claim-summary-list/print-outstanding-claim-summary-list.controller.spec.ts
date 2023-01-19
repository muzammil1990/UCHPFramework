/**
 * Created by a232625 on 2016-07-12.
 */

/// <reference path="../../../../app/_all.ts" />

describe('Component: PrintOutstandingClaimSummaryList', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have OutstandingClaimSummaryDocumentStore', () => {
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, {});
    expect(ctrl['OutstandingClaimSummaryDocumentStore']).toBeDefined();
  });

  it('should have searchOutstandingClaimSummaryDocsSubscription', () => {
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, {});
    expect(ctrl['searchOutstandingClaimSummaryDocsSubscription']).toBeDefined();
  });

  it('should have gridOptions', () => {
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, {});
    expect(ctrl['gridOptions']).toBeDefined();
  });

  it('should have local Storage Service', () => {
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, {});
    expect(ctrl['localStorageService']).toBeDefined();
  });

  it('should have Notification Service', () => {
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, {});
    expect(ctrl['NotificationService']).toBeDefined();
  });

  it('should have Select Companies Store', () => {
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, {});
    expect(ctrl['SelectCompaniesStore']).toBeDefined();
  });

  it('should have $translate', () => {
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, {});
    expect(ctrl['$translate']).toBeDefined();
  });
  it('should have $scope', () => {
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });
  it('should have $log', () => {
    var ctrl = $componentController('printOutstandingClaimSummaryList', null, {});
    expect(ctrl['$log']).toBeDefined();
  });
});
