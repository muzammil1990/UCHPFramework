/**
 * Created by a038509 on 2016-08-26.
 */

/// <reference path="../../../../app/_all.ts" />

describe('Component: newClaimJob', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
   var bindings = {
      fresh: '@'
    };
    var ctrl = $componentController('newClaimJob', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have $log', () => {
    var ctrl = $componentController('newClaimJob', null, {});
    expect(ctrl['$log']).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('newClaimJob', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have $translatePartialLoader', () => {
    var ctrl = $componentController('newClaimJob', null, {});
    expect(ctrl['$translatePartialLoaderProvider']).toBeDefined();
  });

  it('should have $translate', () => {
    var ctrl = $componentController('newClaimJob', null, {});
    expect(ctrl['$translate']).toBeDefined();
  });
});
