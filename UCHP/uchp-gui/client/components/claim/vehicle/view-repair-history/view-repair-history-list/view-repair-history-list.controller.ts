/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';
  var targetService = 'viewRepairHistroy';
  class ViewRepairHistoryListController {
    private vehicleInformationsRepairHistoryList: Array < VehicleInformationModel >;
    private vehicleInformations: Array < VehicleInformationModel >;
    private listVehicleInformations: Array < VehicleInformationModel >;
    private gridOptions: any;
    private listRepairHistoryVehicleInformationSubscription: any;
    private getRepairHistoryVehicleInformationSubscription: any;
    private getMultipleVehicleInformationSubscription: any;
    private showTable: Boolean = false;
    private loading: Boolean = false;

    static $inject = [
      'VehicleInformationStore',
      '$log',
      'DispatcherService',
      '$scope',
      'ModalService',
      'NotificationService'
    ];

    constructor(private VehicleInformationStore: IVehicleInformationStore,
                private $log: ng.ILogService,
                private dispatcherService: IDispatcherService,
                private $scope: ng.IScope,
                private modal: IModalService,
                private NotificationService: INotificationService
    ) {
      var that = this;
      that.vehicleInformations = [];
      that.listVehicleInformations = [];

      that.$log.debug('ViewRepairHistoryList Initialized');

      that.listRepairHistoryVehicleInformationSubscription =
        that.dispatcherService.subscribe('vehicleInformation:getVehicleInformation',
          that.repairHistoryVehicleInformationResultHandler.bind(this));

      that.getMultipleVehicleInformationSubscription =
        that.dispatcherService.subscribe('vehicleInformation:getMultipleVehicleInfomations',
          that.repairHistoryVehicleInformationResultHandler.bind(this));

      that.getRepairHistoryVehicleInformationSubscription =
        that.dispatcherService.subscribe('vehicleInformation:getChassisData',
          that.repairHistoryvehicleInformationResultHandler.bind(this));

      that.$scope.$on('$stateChangeStart', function () {
        that.listRepairHistoryVehicleInformationSubscription.unsubscribe();
        that.getMultipleVehicleInformationSubscription.unsubscribe();
        that.getRepairHistoryVehicleInformationSubscription.unsubscribe();
      });

      that.vehicleInformationsRepairHistoryList = [];
      that.gridOptions = {
        columnDefs: [
          {
            cellFilter: 'date:"dd/MM/yyyy"',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'repairDate'
          }, {
            cellTemplate: '<div class="ui-grid-cell-contents"><a ui-sref="app.claim-job({claimId: row.entit' +
            'y.refNo})">{{ row.entity.repairingImporter }} - {{ row.entity.repairingDealer }}' +
            ' - {{ row.entity.refNo }} - {{ row.entity.jobNo }}</div>',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'repairingImporter,repairingDealer,refNo,jobNo',
            name: 'Claim job id'
          }, {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'repairOrderNo'
          }, {
            displayName: 'Milage (km)',
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'vehicleMileage'
          }, {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'ageVehicle',
            name: 'Age',
            width: 40
          }, {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'mainOperationNo',
            name: 'Main op',
            width: 70
          }, {
            cellTemplate: ` <div class="ui-grid-cell-contents">{{ row.entity.causalPartNoPrefix }} - {{ row.entity.causalPartNo }}</div>`,
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'causalPartNoPrefix,causalPartNo',
            name: 'Causal Part'
          }, {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'causalPartDescription',
            name: 'Causal Part name'
          }, {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'scc',
            name: 'SCC',
            width: 70
          }
        ],
        data: 'ctrl.vehicleInformationsRepairHistoryList',
        enableColumnMenus: false,
        enableColumnResize: true,
        enableFiltering: true,
        // Should be changed to 2 (WHEN_NEEDED) when available in ui grid api see:
        // https://github.com/angular-ui/ui-grid/blob/master/src/js/core/constants.js
        enableHorizontalScrollbar: 0,
        enablePaging: true,
        enableVerticalScrollbar: 0,
        headerRowHeight: 55,
        multiSelect: false,
        /*For Pagination*/
        paginationPageSize: 10,
        /*Getting Data from creditDocsSearchResults*/
        paginationPageSizes: [
          10, 25, 50
        ],
        rowHeight: 26,
        showFooter: false
      };
    }

    repairHistoryvehicleInformationResultHandler(data) {
      console.log('Service with Params::: ' + targetService + 'Chassis No:::' +
        data.chassisNo + 'Chassis Series:::' + data.chassisSeries);
      if (targetService === 'viewRepairHistroy') {
        this.VehicleInformationStore.getMultipleVehicleInformations(data.chassisNo, data.chassisSeries, targetService);
      }
    }

    repairHistoryVehicleInformationResultHandler(data) {
      var that = this;
      var count;
      console.log('Data::: ' + data);
      console.log('Data::: Vehicle Information Search Results' + data.vehicleInformationSearchResults.length);
      if (data && angular.isArray(data.vehicleInformationSearchResults)) {
        if (data.vehicleInformationSearchResults.length < 1) {
          that.$log.debug('Result length is equal to ZERO...');
          that.NotificationService.trigger({
            'data': {
              'primaryCode': '199999',
              'secondaryCode': 0
            }
          });
          that.vehicleInformationsRepairHistoryList = [];
          that.vehicleInformations = [];
          that.listVehicleInformations = [];
          that.loading = false;
          that.showTable = false;
        } else if (data.vehicleInformationSearchResults.length === 1) {
          count = data.metadata ? data.metadata.count : data.vehicleInformationSearchResults.length;
          that.gridOptions.totalItems = count;
          that.loading = false;
          that.showTable = true;
          that.gridOptions.paginationPageSizes = that.gridOptions.paginationPageSizes.filter(item => item < count);
          that.$log.debug('Result length is equal to 1...' + data.vehicleInformationSearchResults);
          that.vehicleInformationsRepairHistoryList = data.vehicleInformationSearchResults[0].repairHistoryList;
          that.vehicleInformations = data.vehicleInformationSearchResults[0];
        } else if (data.vehicleInformationSearchResults.length > 1) {
          count = data.metadata ? data.metadata.count : data.vehicleInformationSearchResults.length;
          that.gridOptions.totalItems = count;
          that.loading = false;
          that.showTable = true;
          that.gridOptions.paginationPageSizes = that.gridOptions.paginationPageSizes.filter(item => item < count);
          that.$log.debug('Result length is Greater than 1...' + data.vehicleInformationSearchResults.length);
          that.listVehicleInformations = data.vehicleInformationSearchResults;
          that.modal.open(null, 'ViewCoverage', '', true, this.listVehicleInformations);
        }
      }
    }

  }

  angular.module('uchpClientAngularApp')
    .component('viewRepairHistoryList', {
      controller: [
        'VehicleInformationStore',
        '$log',
        'DispatcherService',
        '$scope',
        'ModalService',
        'NotificationService',
        ViewRepairHistoryListController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/vehicle/view-repair-history/view-repair-history-list/view-repair-history-list.html'
    });
}
