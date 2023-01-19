/**
 * Created by a232625 on 2016-07-12.
 */

/// <reference path="../../../../app/_all.ts" />

describe('Component: CreateOutstandingClaimSummaryForm', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('createOutstandingClaimSummaryForm', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have Brand Store', () => {
    var ctrl = $componentController('createOutstandingClaimSummaryForm', null, {});
    expect(ctrl['BrandStore']).toBeDefined();
  });

  it('should have Outstanding Claim Summar yDocument Store', () => {
      var ctrl = $componentController('createOutstandingClaimSummaryForm', null, {});
      expect(ctrl['OutstandingClaimSummaryDocumentStore']).toBeDefined();
    });

});
