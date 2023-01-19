import IModalService = uchpClientAngularApp.IModalService;
/**
 * Created by a232625 on 2016-07-15.
 */
/// <reference path="../../_all.ts" />

describe('Service: ModalService', () => {
  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var
    ModalService: IModalService;

  // initialize service
  beforeEach(inject(($injector: IInjectorService) => {
      ModalService = <IModalService>$injector.get('ModalService');
    })
  );

  it('should exist', () => {
    expect(ModalService).toBeDefined();
  });

  it('should have a public API', () => {
    expect(ModalService.open).toBeDefined();
  });

  it('modal open should return object', () => {
    let returned_object = ModalService.open({title: '', message: '', subMessage: ''} , '', '', false, null, '');
    expect(typeof returned_object).toBe('object');
  });

  it('modal open object should have API', () => {
    let returned_object = ModalService.open({title: '', message: '', subMessage: ''} , '', '', false, null, '');
    expect(returned_object.close).toBeDefined();
    expect(returned_object.opened).toBeDefined();
    expect(returned_object.dismiss).toBeDefined();
    expect(returned_object.rendered).toBeDefined();
    expect(returned_object.result).toBeDefined();
  });

});
