/**
 * Created by a038509 on 2016-09-01.
 */

/// <reference path="../../../../app/_all.ts" />

describe('Component: claimJobItem', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('claimJobItem', {
      $attrs: {}
    }, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('claimJobItem', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have $stateParams', () => {
    var ctrl = $componentController('claimJobItem', null, {});
    expect(ctrl['$stateParams']).toBeDefined();
  });

  it('should have $log', () => {
    var ctrl = $componentController('claimJobItem', null, {});
    expect(ctrl['$log']).toBeDefined();
  });

  it('should have $scope', () => {
    var ctrl = $componentController('claimJobItem', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });

  it('should have $window', () => {
    var ctrl = $componentController('claimJobItem', null, {});
    expect(ctrl['$window']).toBeDefined();
  });

  it('should have ModalService', () => {
    var ctrl = $componentController('claimJobItem', null, {});
    expect(ctrl['modal']).toBeDefined();
  });
});
