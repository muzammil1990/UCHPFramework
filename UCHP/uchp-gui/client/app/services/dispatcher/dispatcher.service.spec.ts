import IDispatcherService = uchpClientAngularApp.IDispatcherService;
/**
 * Created by a232625 on 2016-07-14.
 */
/// <reference path="../../_all.ts" />

describe('Service: DispatcherService', () => {
  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var
    DispatcherService: IDispatcherService,
    dispatcherActionHandler: Function,
    dispatcherActionSubscription: any,
    actionState: boolean;

  // initialize service
  beforeEach(inject(($injector: IInjectorService) => {
      DispatcherService = <IDispatcherService>$injector.get('DispatcherService');
      dispatcherActionHandler = (action: Object) => (action) ? (actionState = true, true): (actionState = false, false);
      actionState = null;
    })
  );

  it('should exist', () => {
    expect(DispatcherService).toBeDefined();
  });

  it('should have a public API', () => {
    expect(DispatcherService.publish).toBeDefined();
    expect(DispatcherService.subscribe).toBeDefined();
    expect(DispatcherService.publishAndWaitForReply).toBeDefined();
  });

  it('should subscribe', () => {
    dispatcherActionSubscription = DispatcherService.subscribe('test:Action',
      dispatcherActionHandler.bind(this)
    );
    expect(dispatcherActionSubscription['topic']).toBe('test:Action');
  });

  it('should return false for undefined parameter in action handler', () => {
    expect(dispatcherActionHandler(undefined)).toBe(false);
  });

  it('should return true for object parameter in action handler', () => {
    var testState = dispatcherActionHandler({test: 'test'});
    expect(dispatcherActionHandler({test: 'test'})).toBe(true);
  });

  it('should publish undefined and return false', () => {
    DispatcherService.publish('test:Action', undefined);
    expect(actionState).toBe(false);
  });

  it('should publish object and return true', () => {
    DispatcherService.publish('test:Action', {test: 'test'});
    expect(actionState).toBe(true);
  });


});
