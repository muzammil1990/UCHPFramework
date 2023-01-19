/**
 * Created by a038509 on 2016-09-01.
 */

/// <reference path="../../../../../app/_all.ts" />

describe('Component: claimJobItemRemarks', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('claimJobItemRemarks', null, bindings);
    expect(ctrl).toBeDefined();
  });

});
