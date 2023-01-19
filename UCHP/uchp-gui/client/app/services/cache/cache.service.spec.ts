/// <reference path="../../_all.ts" />

import ICacheFactoryService = angular.ICacheFactoryService;
describe('Service: CacheService', () => {
  // loads app
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  var
    CacheService: ICacheService,
    $cacheFactory: ICacheFactoryService;

  // initialize service
  beforeEach(inject(($injector: IInjectorService) => {
    CacheService = <ICacheService>$injector.get('CacheService');
    $cacheFactory = <ICacheFactoryService>$injector.get('$cacheFactory');
    })
  );

  it('should exist', () => {
    expect(CacheService).toBeDefined();
  });

  it('should initialize with an empty cache-object', () => {
    var cacheObject = $cacheFactory.get('uchpClientCache');
    expect(cacheObject).toBeDefined();
    expect(cacheObject.info()).toEqual({id: 'uchpClientCache', size: 0});
  });

  it('should have a public API', () => {
    expect(CacheService.get).toBeDefined();
    expect(CacheService.put).toBeDefined();
  });

  describe('get() should', () => {
    var cacheObject;
    //create stubs
    beforeEach(() => {
      cacheObject = $cacheFactory.get('uchpClientCache');
      expect(cacheObject).toBeDefined();
      cacheObject.put('key1', 'value1');
      expect(cacheObject.info()).toEqual({id: 'uchpClientCache', size: 1});
    });

    it('return data from cache for existing key', () => {
      var result = CacheService.get('key1');
      expect(result).toBeDefined();
      expect(result).toBe('value1');
    });
    it('return undefined for non existing key', () => {
      var result = CacheService.get('nonExistingKey');
      expect(result).not.toBeDefined();
    });
  });

  describe('put() should', () => {
    var cacheObject;
    //create stubs
    beforeEach(() => {
      cacheObject = $cacheFactory.get('uchpClientCache');
      cacheObject.put('key1', 'value1');
    });

    it('add data to cache for new key', () => {
      expect(cacheObject.info()).toEqual({id: 'uchpClientCache', size: 1});
      CacheService.put('newKey', 'new value');
      expect(cacheObject.info()).toEqual({id: 'uchpClientCache', size: 2});
      expect(cacheObject.get('newKey')).toBe('new value');
    });

    it('overwrite data for existing key', () => {
      expect(cacheObject.get('key1')).toBe('value1');
      CacheService.put('key1', 'some value');
      expect(cacheObject.get('key1')).toBe('some value');
    });
  });
});
