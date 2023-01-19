/**
 * Created by a232625 on 2016-07-12.
 */

/// <reference path="../../../app/_all.ts" />

describe('Component: CreateOutstandingClaimSummary', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('createOutstandingClaimSummary', null, bindings);
    expect(ctrl).toBeDefined();
  });

});
