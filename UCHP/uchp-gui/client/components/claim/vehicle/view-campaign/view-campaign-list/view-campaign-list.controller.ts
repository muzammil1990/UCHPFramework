/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class ViewCampaignListController {
    private gridOptions: any;
    private searchViewCampaignSubscription: any;
    private vehicleInformationsCampaignList: Array < SearchVehicleCampaignListModel >;
    private showTable: Boolean = false;

    static $inject = [
      '$log',
      'ViewCampaignStore',
      'DispatcherService',
      '$scope',
      'NotificationService'
    ];

    constructor(private $log: ng.ILogService,
                private ViewCampaignStore: IViewCampaignStore,
                private DispatcherService: IDispatcherService,
                private $scope: ng.IScope,
                private NotificationService: INotificationService
    ) {
      var that = this;
      that.$log.debug('ViewCampaignList Initialized');

      that.searchViewCampaignSubscription =
        that.DispatcherService
          .subscribe('searchVehicleCampaignList:SearchVehicleCampaign',
            this.viewCampaignResultHandler.bind(this));

      that.$scope.$on('$stateChangeStart', function () {
        that.searchViewCampaignSubscription.unsubscribe();
      });

      that.vehicleInformationsCampaignList = [];
      that.gridOptions = {
        columnDefs: [
          {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'sccCode',
            name: 'SCC'
          }, {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'title',
            name: 'Description'
          }, {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'lastRepairDate',
            name: 'Closing date'
          }, {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'sccType',
            name: 'sccType'
          }, {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'responsible',
            name: 'Responsible Warranty Area'
          }, {
            enableFiltering: false,
            enableHiding: false,
            enableSorting: true,
            field: 'campaignId',
            name: ' Responsible dealer'
          }
        ],
        data: 'ctrl.vehicleInformationsCampaignList',
        enableColumnMenus: false,
        enableColumnResize: true,
        enableFiltering: true,
        enablePaging: true,
        headerRowHeight: 55,
        multiSelect: false,
        /*For Pagination*/
        paginationPageSize: 10,
        /*Getting Data from creditDocsSearchResults*/
        paginationPageSizes: [
          10, 25, 50
        ],
        rowHeight: 35,
        showFooter: false
      };

    }

    viewCampaignResultHandler(data) {
      console.log('Data ::: ' + data);
      console.log('viewCampaignResultHandler Data ::: ' + data.vehicleInformationsSearchResults.length);

      if (data.vehicleInformationsSearchResults.length >= 1) {
        this.showTable = true;
        this.vehicleInformationsCampaignList = data.vehicleInformationsSearchResults[0].campaignList;
      }
    }

  }

  angular.module('uchpClientAngularApp')
    .component('viewCampaignList', {
      controller: [
        '$log',
        'ViewCampaignStore',
        'DispatcherService',
        '$scope',
        'NotificationService',
        ViewCampaignListController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/vehicle/view-campaign/view-campaign-list/view-campaign-list.html'
    });
}
