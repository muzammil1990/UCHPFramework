/**
 * Created by a038509 on 2016-10-25.
 */

/// <reference path="../../../../../app/_all.ts" />

describe('Component: newClaimJobForm', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('newClaimJobForm', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('newClaimJobForm', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have New Claim Job Store', () => {
    var ctrl = $componentController('newClaimJobForm', null, {});
    expect(ctrl['NewClaimJobStore']).toBeDefined();
  });

  it('should have Select Companies Store', () => {
    var ctrl = $componentController('newClaimJobForm', null, {});
    expect(ctrl['SelectCompaniesStore']).toBeDefined();
  });

  it('should have User Store', () => {
    var ctrl = $componentController('newClaimJobForm', null, {});
    expect(ctrl['UserStore']).toBeDefined();
  });

  it('should have Brand Store', () => {
    var ctrl = $componentController('newClaimJobForm', null, {});
    expect(ctrl['BrandStore']).toBeDefined();
  });

  it('should have $log', () => {
    var ctrl = $componentController('newClaimJobForm', null, {});
    expect(ctrl['$log']).toBeDefined();
  });

  it('should have New Claim Service', () => {
    var ctrl = $componentController('newClaimJobForm', null, {});
    expect(ctrl['NewClaimService']).toBeDefined();
  });

  it('should have $state', () => {
    var ctrl = $componentController('newClaimJobForm', null, {});
    expect(ctrl['$state']).toBeDefined();
  });

  it('should have $translate', () => {
    var ctrl = $componentController('newClaimJobForm', null, {});
    expect(ctrl['$translate']).toBeDefined();
  });

  it('should have $scope', () => {
    var ctrl = $componentController('newClaimJobForm', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });
});
