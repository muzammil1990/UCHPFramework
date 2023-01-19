/// <reference path="../../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';
  var targetService = 'viewCoverage';
  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class ViewCoverageListController {
    private vehicleInformationsCoverageList: Array < VehicleInformationModel >;
    private vehicleInformations: Array < VehicleInformationModel >;
    private listVehicleInformations: Array < VehicleInformationModel >;
    private gridOptions: any;
    private gridApi: any;
    private sort: String = null;
    private descendingSortOrder: Boolean = null;
    private paginationOptions: any;
    private getVehicleInformationSubscription: any;
    private getviewCoverageVehicleInformationSubscription: any;
    private getMultipleVehicleInformationSubscription: any;
    private showTable: Boolean = false;
    private loading: Boolean = false;
    static $inject = [
      'VehicleInformationStore',
      '$log',
      'DispatcherService',
      '$scope',
      'ModalService',
      'DispatcherService',
      'NotificationService',
      'localStorageService',
      'uiGridConstants'
    ];
    constructor(private VehicleInformationStore: IVehicleInformationStore,
                private $log: ng.ILogService,
                private dispatcherService: IDispatcherService,
                private $scope: ng.IScope,
                private modal: IModalService,
                private DispatcherService: IDispatcherService,
                private NotificationService: INotificationService,
                private localStorageService) {
      var that = this;
      that.vehicleInformations = [];
      that.listVehicleInformations = [];
      that.$log.debug('ViewCoverageListController Initialized');
      that.getVehicleInformationSubscription = that.dispatcherService.subscribe('vehicleInformation:getVehicleInformation',
        this.vehicleInformationResultHandler.bind(this));
      that.getMultipleVehicleInformationSubscription = that.dispatcherService.subscribe('vehicleInformation:getMultipleVehicleInfomations',
        this.vehicleInformationResultHandler.bind(this));
      that.getviewCoverageVehicleInformationSubscription = that.dispatcherService.subscribe('vehicleInformation:getChassisData',
        this.viewCoveragevehicleInformationResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.getVehicleInformationSubscription.unsubscribe();
        that.getMultipleVehicleInformationSubscription.unsubscribe();
        that.getviewCoverageVehicleInformationSubscription.unsubscribe();
      });
      that.vehicleInformationsCoverageList = [];
      that.paginationOptions = {
        pageNumber: 1,
        pageSize: that.localStorageService.get('pageSize') || 10,
        sort: that.sort
      };
      that.gridOptions = {
        columnDefs: [
          {
            enableFiltering: false,
            headerTooltip: true,
            enableHiding: false,
            field: 'coverageId',
            name: 'Coverage Code',
            width: 140
          }, {
            enableFiltering: false,
            headerTooltip: true,
            enableHiding: false,
            field: 'coverageDescription',
            name: 'Description'
          }, {
            cellFilter: 'filename',
            headerTooltip: true,
            cellTemplate: '<div class="text-center ui-grid-cell-contents" ng-if="row.entity.coverageAttachmentId != null" ' +
            'ng-click="grid.appScope.ctrl.getviewCoverageDocument(row.entity.coverageId, row.entity.coverageAttachmentId)"> ' +
            '<i class="print-download-file fa fa-file-pdf-o"></i></div>',
            displayName: 'Attachment',
            enableFiltering: false,
            enableHiding: false,
            field: 'coverageAttachmentName',
            width: 120
          }, {
            cellFilter: 'date:"dd/MM/yyyy"',
            headerTooltip: true,
            enableFiltering: false,
            enableHiding: false,
            field: 'coverageStartDate',
            name: 'Start Date'
          }, {
            cellFilter: 'date:"dd/MM/yyyy"',
            headerTooltip: true,
            enableFiltering: false,
            enableHiding: false,
            field: 'coverageEndDate',
            name: 'End Date'
          }
        ],
        data: 'ctrl.vehicleInformationsCoverageList',
        enableColumnMenus: false,
        enableColumnResize: false,
        enableFiltering: that.vehicleInformationsCoverageList.length > 0,
        enablePaging: that.vehicleInformationsCoverageList.length > 0,
        headerRowHeight: 55,
        multiSelect: false,
        onRegisterApi: function (gridApi) {
          that.gridApi = gridApi;
          that.gridApi.core.on.sortChanged(that.$scope, function (grid, sortColumns) {
            if (sortColumns.length === 0) {
              that.sort = null;
            } else {
              that.loading = true;
              // TODO: backend should harmonize props, eg creditOccId should be CREDIT_OCC_ID
              var sortColumn = sortColumns[0].field;
              that.toggleSort();
            }
            that.DispatcherService.publish('viewcoverage:SortViewCoverageDocuments', {
              'pageNumber': that.paginationOptions.pageNumber,
              'pageSize': that.paginationOptions.pageSize,
              'sort': that.descendingSortOrder,
              'sortColumn': sortColumn // CREDIT_DOCUMENT_ID
            });
          });
          gridApi.pagination.on.paginationChanged(that.$scope, function (newPage, pageSize) {
            that.loading = true;
            that.paginationOptions.pageNumber = newPage;
            that.paginationOptions.pageSize = pageSize;
            that.localStorageService.set('pageSize', pageSize);
            that.DispatcherService.publish('viewcoverage:SwitchViewCoverageDocuments', {
              'pageNumber': that.paginationOptions.pageNumber,
              'pageSize': that.paginationOptions.pageSize
            });
          });
        },
        /*Getting Data from creditDocsSearchResults*/
        paginationPageSizes: [
          10, 25, 50
        ],
        paginationPageSize: that.localStorageService.get('pageSize') || 10,
        rowHeight: 26,
        showFooter: false,
        useExternalPagination: true,
        useExternalSorting: true
      };
    }

    toggleSort() {
      if (!this.sort) {
        this.descendingSortOrder = false;
        this.sort = 'asc';
      } else if (this.sort === 'asc') {
        this.descendingSortOrder = true;
        this.sort = 'desc';
      } else if (this.sort === 'desc') {
        this.descendingSortOrder = false;
        this.sort = 'asc';
      }
    }

    getviewCoverageDocument(coverageId: string, coverageAttachmentId: string) {
      if (coverageId) {
        this.VehicleInformationStore.getviewCoverageDocument(coverageId, coverageAttachmentId);
      }
    };

    viewCoveragevehicleInformationResultHandler(data) {
      console.log('Service with Params ::: ' + targetService + 'Chassis No :::' +
        data.chassisNo + 'Chassis Series :::' + data.chassisSeries);
      if (targetService === 'viewCoverage') {
        this.VehicleInformationStore.getMultipleVehicleInformations(data.chassisNo, data.chassisSeries, targetService);
      }
    }

    //Subscribe Handler function for 'vehicleInformation:getVehicleInformation'
    vehicleInformationResultHandler(data) {
      var that = this;
      var count;
      console.log('Data ::: ' + data);
      console.log('Data ::: Vehicle Information Search Results' + data.vehicleInformationSearchResults.length);
      if (data && angular.isArray(data.vehicleInformationSearchResults)) {
        if (data.vehicleInformationSearchResults.length < 1) {
          that.$log.debug('Result length is equal to ZERO...');
          that.NotificationService.trigger({
            'data': {
              'primaryCode': '199999',
              'secondaryCode': 0
            }
          });
          that.vehicleInformationsCoverageList = [];
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
          that.vehicleInformationsCoverageList = data.vehicleInformationSearchResults[0].coverageList;
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
    .component('viewCoverageList', {
    controller: [
      'VehicleInformationStore',
      '$log',
      'DispatcherService',
      '$scope',
      'ModalService',
      'DispatcherService',
      'NotificationService',
      'localStorageService',
      'uiGridConstants',
      ViewCoverageListController
    ],
    controllerAs: 'ctrl',
    templateUrl: 'components/claim/vehicle/view-coverage/view-coverage-list/view-coverage-list.html'
  });
}
