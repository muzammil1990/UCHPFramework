/**
 * Created by a038509 on 2017-02-08.
 */
module uchpClientAngularApp {
  'use strict';

  class MaintainCostAdjustmentFactorController {

    private language: any;
    static $inject = [
      'DispatcherService'
    ];
    constructor(private DispatcherService: IDispatcherService
    ) {
      // TODO: not implemented, below are 'placeholder code'
      var that = this;
      that.language = {
        countryCode: 'AR',
        modifiedBy: 'B263276',
        modifyTime: 1288323623006,
        languageCode: 'es',
        languageCode_3: 'esp'
      };
    }

    updateLanguage() {
      var that = this;
      that.DispatcherService.publish('language:Updated', that.language);
    }

    deleteLanguage() {
      var that = this;
      that.language = {
        countryCode: '',
        modifiedBy: '',
        modifyTime: 0,
        languageCode: '',
        languageCode_3: ''
      };
      that.DispatcherService.publish('language:Deleted', that.language);
    }
  }

  angular.module('uchpClientAngularApp')
    .component('maintainCostAdjustmentFactor', {
      controller: [
        'DispatcherService',
        MaintainCostAdjustmentFactorController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/maintain/maintain-cost-adjustment-factor/maintain-cost-adjustment-factor.html'
    });
}
