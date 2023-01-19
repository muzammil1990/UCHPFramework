/**
 * Created by a148094 on 2016-10-17.
 */

/// <reference path="../../../../../app/_all.ts" />

describe('Component: SearchCampaignList', () => {
  var $componentController;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject((_$componentController_: IComponentControllerService) => {
      $componentController = _$componentController_;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('viewCampaignSearchFilter', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should have Select Companies Store', () => {
    var ctrl = $componentController('viewCampaignSearchFilter', null, {});
    expect(ctrl['SelectCompaniesStore']).toBeDefined();
  });

  it('should have View Campaign Store', () => {
    var ctrl = $componentController('viewCampaignSearchFilter', null, {});
    expect(ctrl['ViewCampaignStore']).toBeDefined();
  });

});
