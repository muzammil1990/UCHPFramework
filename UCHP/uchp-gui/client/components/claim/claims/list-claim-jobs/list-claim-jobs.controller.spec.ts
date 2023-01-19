/**
 * Created by a038509 on 2016-08-26.
 */

/// <reference path="../../../../app/_all.ts" />

describe('Component: listClaimJobs', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('listClaimJobs', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have UserStore', () => {
    var ctrl = $componentController('listClaimJobs', null, {});
    expect(ctrl['UserStore']).toBeDefined();
  });

  it('should have $log', () => {
    var ctrl = $componentController('listClaimJobs', null, {});
    expect(ctrl['$log']).toBeDefined();
  });

  it('should have $translatePartialLoader', () => {
    var ctrl = $componentController('listClaimJobs', null, {});
    expect(ctrl['$translatePartialLoaderProvider']).toBeDefined();
  });

  it('should have $translate', () => {
    var ctrl = $componentController('listClaimJobs', null, {});
    expect(ctrl['$translate']).toBeDefined();
  });
});
