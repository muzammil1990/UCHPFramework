/// <reference path="../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';
  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class OperationListController {
    private operationLists: Array <any>;
    private operationformat: any;
    private rowindex: any;
    private paginationOptions: any;
    private gridOptions: any;
    private gridApi: any;
    private loading: boolean;
    private showTable: boolean;
    private operationNoFrom: any;
    private operationNoTo: any;
    private currentPage: number;
    private startIndex: number;


    static $inject = [
      'OperationListsStore',
      'DispatcherService',
      'ModalService',
      '$scope',
      'NotificationService',
      'uiGridConstants',
    ];
    constructor(private OperationListsStore: IOperationListsStore,
                private DispatcherService: IDispatcherService,
                private modal: IModalService,
                private $scope: ng.IScope,
                private NotificationService: INotificationService) {
      var that = this;
      that.loading = true;
      that.showTable = false;
      that.getOperationLists(false);
      that.paginationOptions = {
        pageNumber: 1,
        pageSize: 15
      };
      that.currentPage = 0;
      that.startIndex = 1;
      that.gridOptions = {
        columnDefs: [
          {
            enableFiltering: false,
            enableSorting: false,
            enableHiding: false,
            headerTooltip: true,
            cellTemplate: '<div class="ui-grid-cell-contents" ' +
            'ng-click="grid.appScope.ctrl.setOperationNumber(row.entity)">' +
            '<a href>' +
            '{{ row.entity.operationNo }}</a></div>',
            field: 'operationNo',
            name: 'Operation Code',
            width: 100
          }, {
            enableFiltering: false,
            cellTemplate: '<div class="ui-grid-cell-contents" ' +
            'ng-click="grid.appScope.ctrl.setOperationNumber(row.entity)">' +
            '{{ row.entity.description }}</div>',
            enableHiding: false,
            enableSorting: false,
            headerTooltip: true,
            field: 'description',
            name: 'Description'
          }
        ],
        data: 'ctrl.operationLists',
        enableColumnMenus: false,
        enableColumnResize: true,
        enableFiltering: true,
        headerRowHeight: 55,
        multiSelect: false,
        onRegisterApi: function (gridApi) {
          that.gridApi = gridApi;
          that.gridApi.pagination.on.paginationChanged(that.$scope, function (newPage) {
            that.loading = true;
            that.paginationOptions.pageNumber = newPage;
            if (that.currentPage < newPage) {
              that.startIndex = that.startIndex + that.paginationOptions.pageSize;
            } else {
              that.startIndex = that.startIndex - that.paginationOptions.pageSize;
            }
            that.currentPage = newPage;
            that.getOperationLists(true);
          });
        },
        paginationPageSizes: [
          15
        ],
        rowHeight: 29,
        paginationPageSize: 15,
        showFooter: false,
        useExternalPagination: true
      };
    }

    getOperationLists(search) {
      var that = this;
      let params: any = {};
      if (search) {
        params = {
          operationNoFrom: that.operationNoFrom,
          operationNoTo: that.operationNoTo,
          numberOfRows: that.paginationOptions.pageSize,
          startIndex: that.startIndex
        };
      }
      params.operationFormat = that.operationformat;
      that
        .OperationListsStore
        .getOperationLists(params)
        .then(function (operationLists: any) {
          if (angular.isArray(operationLists)) {
            that.operationLists = operationLists;
            that.loading = false;
            that.showTable = true;
          }
        });
    }



    closeOperationNumberModal() {
      var that = this;
      that.modal.modalInstance.close();
    }

    setOperationNumber(operation) {
      var that = this;
      let data = {
        operation: operation,
        rowindex: that.rowindex
      };
      that.DispatcherService.publish('modalclose:setOperationNumber', data);
      that.closeOperationNumberModal();
    }

  }

  angular.module('uchpClientAngularApp')
    .component('operationList', {
      bindings: {
        operationformat: '<',
        rowindex: '<'
      },
      controller: [
        'OperationListsStore',
        'DispatcherService',
        'ModalService',
        '$scope',
        'NotificationService',
        'uiGridConstants',
        OperationListController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/operation-list/operation-list.html'
    });
}
