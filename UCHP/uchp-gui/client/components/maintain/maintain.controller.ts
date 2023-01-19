/**
 * Created by a038509 on 2017-02-08.
 */
module uchpClientAngularApp {
  'use strict';

  class MaintainController {

    private countryLanguage: boolean;
    private followUpCode: boolean;
    private debitCode: boolean;
    private authorizedCurrency: boolean;
    private costAdjustmentFactor: boolean;
    private defectCode: boolean;
    private returnCode: boolean;
    private rejectCode: boolean;
    private currencyRate: boolean;
    private functionGroup: boolean;
    private materialGroup: boolean;

    static $inject = [
      'DispatcherService',
      '$translate',
      '$translatePartialLoader'
    ];

    constructor(private DispatcherService: IDispatcherService,
                private $translate: ng.translate.ITranslateService,
                private $translatePartialLoaderProvider: ng.translate.ITranslatePartialLoaderProvider
    ) {
      // TODO: not implemented, below are 'placeholder code'
      var that = this;
      $translatePartialLoaderProvider.addPart('maintain');
      $translate.refresh();
      that.countryLanguage = true;
      that.followUpCode = false;
      that.debitCode = false;
      that.authorizedCurrency = false;
      that.costAdjustmentFactor = false;
      that.defectCode = false;
      that.returnCode = false;
      that.rejectCode = false;
      that.currencyRate = false;
      that.functionGroup = false;
      that.materialGroup = false;
    }
  }

  angular.module('uchpClientAngularApp')
    .component('maintain', {
      controller: [
        'DispatcherService',
        '$translate',
        '$translatePartialLoader',
        MaintainController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/maintain/maintain.html'
    });
}
