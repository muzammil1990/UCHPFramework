/**
 * Created by a038509 on 2017-01-10.
 */

/// <reference path="../../app/_all.ts" />

describe('Component: navbar', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('navbar', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have Dispatcher Service', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['DispatcherService']).toBeDefined();
  });

  it('should have $window', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['$window']).toBeDefined();
  });

  it('should have $stateParams', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['$stateParams']).toBeDefined();
  });

  it('should have $scope', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['$scope']).toBeDefined();
  });

  it('should have $timeout', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['$timeout']).toBeDefined();
  });

  it('should have $cookies', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['$cookies']).toBeDefined();
  });

  it('should have $rootScope', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['$rootScope']).toBeDefined();
  });

  it('should have OAuth', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['oauth']).toBeDefined();
  });

  it('should have $state', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['$state']).toBeDefined();
  });

  it('should have Notification Service', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['NotificationService']).toBeDefined();
  });

  it('should have Select Companies Store', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['SelectCompaniesStore']).toBeDefined();
  });

  it('should have User Store', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['UserStore']).toBeDefined();
  });

  it('should have $log', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['$log']).toBeDefined();
  });

  it('should have $translate', () => {
    var ctrl = $componentController('navbar', null, {});
    expect(ctrl['$translate']).toBeDefined();
  });
});
