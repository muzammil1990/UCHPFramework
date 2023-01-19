/**
 * Created by a038509 on 2017-02-08.
 */

/// <reference path="../../../app/_all.ts" />

describe('Component: Maintain Debit Code', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('maintainDefectCode', {
      $attrs: {}
    }, bindings);
    expect(ctrl).toBeDefined();
  });
  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('maintainDefectCode', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });
});
