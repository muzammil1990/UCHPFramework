/**
 * Created by a038509 on 2016-09-01.
 */

/// <reference path="../../../../../app/_all.ts" />

describe('Component: claimJobItemAssessment', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('claimJobItemAssessment', {
      $attrs: {}
    }, bindings);
    expect(ctrl).toBeDefined();
  });
  it('should have Claim Job Item Store', () => {
    var ctrl = $componentController('claimJobItemAssessment', null, {});
    expect(ctrl['ClaimJobItemStore']).toBeDefined();
  });
  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('claimJobItemAssessment', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });
  it('should have $scope', () => {
    var ctrl = $componentController('claimJobItemAssessment', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });
  it('should have New Claim Service', () => {
    var ctrl = $componentController('claimJobItemAssessment', null, {});
    expect(ctrl['NewClaimService']).toBeDefined();
  });
  it('should have Claim Job Store', () => {
    var ctrl = $componentController('claimJobItemAssessment', null, {});
    expect(ctrl['ClaimJobStore']).toBeDefined();
  });
});
