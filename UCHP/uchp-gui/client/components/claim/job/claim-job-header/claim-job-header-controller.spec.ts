/**
 * Created by a038509 on 2017-01-09.
 */

/// <reference path="../../../../app/_all.ts" />

describe('Component: claimJobHeader', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('claimJobHeader', {
      $attrs: {}
    }, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have Date Util Service', () => {
    var ctrl = $componentController('claimJobHeader', null, {});
    expect(ctrl['DateUtilService']).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('claimJobHeader', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have $log', () => {
    var ctrl = $componentController('claimJobHeader', null, {});
    expect(ctrl['$log']).toBeDefined();
  });

  it('should have $scope', () => {
    var ctrl = $componentController('claimJobHeader', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });
});
