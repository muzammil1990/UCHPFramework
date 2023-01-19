/**
 * Created by a038509 on 2017-01-10.
 */

/// <reference path="../../app/_all.ts" />

describe('Component: help', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('help', null, bindings);
    expect(ctrl).toBeDefined();
  });

});
