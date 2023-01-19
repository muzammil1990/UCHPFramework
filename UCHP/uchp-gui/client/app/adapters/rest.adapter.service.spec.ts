/// <reference path="../_all.ts" />

describe('Service: RESTAdapterService', () => {
  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var RESTAdapterService: IRESTAdapterService;

  // initialize service
  beforeEach(inject(($injector: IInjectorService) => {
    RESTAdapterService = <IRESTAdapterService>$injector.get('RESTAdapterService');
    })
  );

  it('should exist', () => {
    expect(RESTAdapterService).toBeDefined();
  });

  it('should have a public API', () => {
    expect(RESTAdapterService.get).toBeDefined();
    expect(RESTAdapterService.put).toBeDefined();
    expect(RESTAdapterService.post).toBeDefined();
    expect(RESTAdapterService.delete).toBeDefined();
  });

});
