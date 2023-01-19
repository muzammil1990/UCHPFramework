/**
 * Created by a232625 on 2016-07-12.
 */

/// <reference path="../../../../app/_all.ts" />

describe('Component: PrintOutstandingClaimSummarySearchFilter', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var ctrl = $componentController('printOutstandingClaimSummarySearchFilter', null, {});
    expect(ctrl).toBeDefined();
  });

  it('should have Brand Store', () => {
    var ctrl = $componentController('printOutstandingClaimSummarySearchFilter', null, {});
    expect(ctrl['BrandStore']).toBeDefined();
  });

  it('should have Content Type Store', () => {
    var ctrl = $componentController('printOutstandingClaimSummarySearchFilter', null, {});
    expect(ctrl['ContentTypeStore']).toBeUndefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('printOutstandingClaimSummarySearchFilter', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have Outstanding Claim Summary Document Store', () => {
    var ctrl = $componentController('printOutstandingClaimSummarySearchFilter', null, {});
    expect(ctrl['OutstandingClaimSummaryDocumentStore']).toBeDefined();
  });

  it('should have Date Picker Options', () => {
    var ctrl = $componentController('printOutstandingClaimSummarySearchFilter', null, {});
    expect(ctrl['datePickerOptions']).toBeDefined();
  });

  it('should have outstanding Claim Summary Document Form', () => {
    var ctrl = $componentController('printOutstandingClaimSummarySearchFilter', null, {});
    expect(ctrl['outstandingClaimSummaryDocumentForm']).toBeDefined();
  });

  it('should have search Data', () => {
    var ctrl = $componentController('printOutstandingClaimSummarySearchFilter', null, {});
    expect(ctrl['searchData']).toBeDefined();
  });


});
