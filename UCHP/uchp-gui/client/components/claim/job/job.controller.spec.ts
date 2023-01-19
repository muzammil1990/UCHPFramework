/**
 * Created by a232625 on 2016-09-06.
 */

/// <reference path="../../../app/_all.ts" />

describe('Component: claimJob', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('claimJob', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have ClaimJobStore', () => {
    var ctrl = $componentController('claimJob', null, {});
    expect(ctrl['ClaimJobStore']).toBeDefined();
  });

  it('should have $stateParams', () => {
    var ctrl = $componentController('claimJob', null, {});
    expect(ctrl['$stateParams']).toBeDefined();
  });

  it('should have $log', () => {
    var ctrl = $componentController('claimJob', null, {});
    expect(ctrl['$log']).toBeDefined();
  });

  it('should have $scope', () => {
    var ctrl = $componentController('claimJob', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });

  it('should have $translate', () => {
    var ctrl = $componentController('claimJob', null, {});
    expect(ctrl['$translate']).toBeDefined();
  });

  it('should have $translatePartialLoaderProvider', () => {
    var ctrl = $componentController('claimJob', null, {});
    expect(ctrl['$translatePartialLoaderProvider']).toBeDefined();
  });
});
