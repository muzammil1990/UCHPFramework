
/**
 * Created by a232625 on 2016-07-12.
 */

/// <reference path="../../../../app/_all.ts" />

describe('Component: PrintCreditDocumentList', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('printCreditDocumentList', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('printCreditDocumentList', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have Credit Document Store', () => {
    var ctrl = $componentController('printCreditDocumentList', null, {});
    expect(ctrl['CreditDocumentStore']).toBeDefined();
  });

});
