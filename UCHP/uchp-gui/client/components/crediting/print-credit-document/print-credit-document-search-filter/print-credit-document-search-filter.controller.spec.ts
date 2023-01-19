/**
 * Created by a232625 on 2016-07-12.
 */

/// <reference path="../../../../app/_all.ts" />

describe('Component: PrintCreditDocumentSearchFilter', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject(($injector: IInjectorService, _$componentController_: any) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('printCreditDocumentSearchFilter', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have Brand Store', () => {
    var ctrl = $componentController('printCreditDocumentSearchFilter', null, {});
    expect(ctrl['BrandStore']).toBeDefined();
  });

  it('should have Content Type Store', () => {
    var ctrl = $componentController('printCreditDocumentSearchFilter', null, {});
    expect(ctrl['ContentTypeStore']).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('printCreditDocumentSearchFilter', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have Credit Document Store', () => {
    var ctrl = $componentController('printCreditDocumentSearchFilter', null, {});
    expect(ctrl['CreditDocumentStore']).toBeDefined();
  });

  it('should have Date Picker Options', () => {
    var ctrl = $componentController('printCreditDocumentSearchFilter', null, {});
    expect(ctrl['datePickerOptions']).toBeDefined();
  });

  it('should have Credit Document Form', () => {
    var ctrl = $componentController('printCreditDocumentSearchFilter', null, {});
    expect(ctrl['creditDocumentForm']).toBeDefined();
  });

  it('should have Credit Documents Data', () => {
    var ctrl = $componentController('printCreditDocumentSearchFilter', null, {});
    expect(ctrl['creditDocumentsData']).toBeDefined();
  });
});
