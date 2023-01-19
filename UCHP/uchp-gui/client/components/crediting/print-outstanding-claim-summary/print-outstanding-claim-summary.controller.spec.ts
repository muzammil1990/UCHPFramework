/**
 * Created by a232625 on 2016-07-12.
 */

/// <reference path="../../../app/_all.ts" />

describe('Component: PrintOutstandingClaimSummary', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('printOutstandingClaimSummary', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have Select Companies Store', () => {
    var ctrl = $componentController('printOutstandingClaimSummary', null, {});
    expect(ctrl['SelectCompaniesStore']).toBeDefined();
  });

  it('should have $log', () => {
    var ctrl = $componentController('printOutstandingClaimSummary', null, {});
    expect(ctrl['$log']).toBeDefined();
  });

  it('should have User Store', () => {
    var ctrl = $componentController('printOutstandingClaimSummary', null, {});
    expect(ctrl['UserStore']).toBeDefined();
  });

  it('should have $state', () => {
    var ctrl = $componentController('printOutstandingClaimSummary', null, {});
    expect(ctrl['$state']).toBeDefined();
  });

  it('should have $translate', () => {
    var ctrl = $componentController('printOutstandingClaimSummary', null, {});
    expect(ctrl['$translate']).toBeDefined();
  });

  it('should have $translatePartialLoader', () => {
    var ctrl = $componentController('printOutstandingClaimSummary', null, {});
    expect(ctrl['$translatePartialLoaderProvider']).toBeDefined();
  });
});
