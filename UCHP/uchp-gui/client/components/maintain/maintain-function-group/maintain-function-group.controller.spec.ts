/**
 * Created by a038509 on 2017-02-08.
 */

/// <reference path="../../../app/_all.ts" />

describe('Component: Maintain Function Group', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('maintainFunctionGroup', {
      $attrs: {}
    }, bindings);
    expect(ctrl).toBeDefined();
  });
  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('maintainFunctionGroup', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });
});