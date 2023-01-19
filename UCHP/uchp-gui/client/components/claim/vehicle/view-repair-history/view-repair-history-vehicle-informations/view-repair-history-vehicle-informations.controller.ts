'use strict';
module uchpClientAngularApp {

  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class ViewRepairHistoryVehicleInformationsController {
    private getVehicleInformationSubscription: any;
    private getMultipleVehicleInformationSubscription: any;
    private vehicleHeaderInformations: Array < VehicleInformationModel >;

    static $inject = [
      'VehicleInformationStore',
      '$log',
      'DispatcherService',
      '$scope'
    ];

    constructor(private VehicleInformationStore: IVehicleInformationStore,
                private $log: ng.ILogService,
                private dispatcherService: IDispatcherService,
                private $scope: ng.IScope) {

      var that = this;
      that.$log.debug('ViewRepairHistoryVehicleInformationsController Initialized');
      that.vehicleHeaderInformations = [];
      that.$log.debug('ViewCoverageListController Initialized');
      that.getVehicleInformationSubscription = that.dispatcherService.subscribe('vehicleInformation:getVehicleInformation',
        this.vehicleInformationResultHandler.bind(this));
      that.getMultipleVehicleInformationSubscription = that.dispatcherService.subscribe('vehicleInformation:getMultipleVehicleInfomations',
        this.vehicleInformationRepairHistoryResultHandler.bind(this));
    }

    vehicleInformationResultHandler(data) {
      var that = this;
      that.$log.debug('vehicleHeaderInformations length ::: ' + data.vehicleInformationSearchResults);
      if (data.vehicleInformationSearchResults.length > 0) {
        console.log('Length ::: ' + data.vehicleInformationSearchResults.length);
        this.vehicleHeaderInformations = data.vehicleInformationSearchResults[0];
      }
    }
    vehicleInformationRepairHistoryResultHandler(data) {
      var that = this;
      that.$log.debug('vehicleHeaderInformations length ::: ' + data.vehicleInformationSearchResults);
      if (data.vehicleInformationSearchResults.length > 0) {
        console.log('Length ::: ' + data.vehicleInformationSearchResults.length);
        this.vehicleHeaderInformations = data.vehicleInformationSearchResults[0];
      }
    }

  }

  angular.module('uchpClientAngularApp')
    .component('viewRepairHistoryVehicleInformations', {
      templateUrl: 'components/claim/vehicle/view-repair-history/view-repair-history-vehicle-informations/' +
      'view-repair-history-vehicle-informations.html',
      controller: ['VehicleInformationStore', '$log', 'DispatcherService',
        '$scope', ViewRepairHistoryVehicleInformationsController],
      controllerAs: 'ctrl'
    });
}

