/// <reference path="../../_all.ts" /> reusable modal service API:
// https://angular-ui.github.io/bootstrap/#/modal whole service should be
// refactored in free time with .html templates (DRY)

module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @export
   * @interface ModalContent
   */
  export interface ModalContent {
    /**
     *
     *
     * @type {string}
     * @memberOf ModalContent
     */
    title: string;
    /**
     *
     *
     * @type {string}
     * @memberOf ModalContent
     */
    message: string;
    /**
     *
     *
     * @type {string}
     * @memberOf ModalContent
     */
    subMessage: string;
    /**
     *
     *
     * @type {number}
     * @memberOf ModalContent
     */
    operationformat: number;
    /**
     *
     *
     * @type {number}
     * @memberOf ModalContent
     */
    rowindex: number;
  }

  class ModalService implements IModalService {
    // private listVehicleInformations: Array < VehicleInformationModel >; private
    // getviewCoverageVehicleInformationSubscription: any;
    private sizeModal;
    private modalInstance: any;

    /**
     *
     *
     * @static
     *
     * @memberOf ModalService
     */
    static $inject = ['$uibModal', 'DispatcherService', 'VehicleInformationStore'];

    /**
     * Creates an instance of ModalService.
     *
     * @param {any} $uibModal
     * @param {IDispatcherService} DispatcherService
     * @param {IVehicleInformationStore} VehicleInformationStore
     *
     * @memberOf ModalService
     */
    constructor(private $uibModal: any,
                private DispatcherService: IDispatcherService,
                private VehicleInformationStore: IVehicleInformationStore
    ) {}

    // TODO: break out gridoptions and size as params
    close() {
      this.modalInstance.close();
    }

    open(content: ModalContent,
         topic: string,
         size: string,
         keyboard: boolean,
         listGridResult: Object,
         customTemplate: string,
         labourGrid: any,
         simpleList: any
    ) {
      var that = this;
      var customTmpl = 'templates/' + customTemplate || 'modal.html';
      return that.modalInstance = that.$uibModal.open({
        backdrop: 'static',
        controller: [
          '$uibModalInstance',
          function ($uibModalInstance) {
            var vm = this;
            if (simpleList != null) {
              vm.simpleList = simpleList;
            }
            if (listGridResult != null) {
              that.sizeModal = 'lg';
              vm.listVehicleInformations = listGridResult;

              vm.gridOptions = {
                columnDefs: [
                  /*{
                   field: 'vin', displayName: '', width: '40',
                   cellTemplate: '<a ui-sref="app.view-repair-history({ chassisNo: row.entity.chassisNo })">' +
                   '<span class="glyphicon glyphicon-link" aria-hidden="true"></span>Link</a>',
                   enableHiding: false, enableFiltering: false, enableSorting: false
                   },*/
                  {
                    field: 'vin',
                    displayName: '',
                    width: '40',
                    cellTemplate: '<label ng-click="grid.appScope.vm.getVehicleInfo(row.entity.chassisNo, row.ent' +
                    'ity.chassisSeries)" class="vehicle-modal-link"><span class="glyphicon glyphico' +
                    'n-link" aria-hidden="true"></span>Link</label>',
                    enableHiding: false,
                    enableFiltering: false,
                    enableSorting: false
                  }, {
                    cellTemplate: '<div>{{ row.entity.chassisSeries }} - {{ row.entity.chassisNo }}</div>',
                    displayName: 'Chassis Id',
                    enableFiltering: false,
                    name: 'chassis',
                    type: 'boolean'
                  }, {
                    displayText: 'Vin',
                    enableFiltering: false,
                    enableHiding: false,
                    enableSorting: true,
                    name: 'vin'
                  }, {
                    enableFiltering: false,
                    enableHiding: false,
                    enableSorting: true,
                    field: 'brand'
                  }, {
                    enableFiltering: false,
                    enableHiding: false,
                    enableSorting: true,
                    field: 'marketingType'
                  }, {
                    enableFiltering: false,
                    enableHiding: false,
                    enableSorting: true,
                    field: 'unitNo'
                  }, {
                    enableFiltering: false,
                    enableHiding: false,
                    enableSorting: true,
                    field: 'customerNo'
                  }, {
                    enableFiltering: false,
                    enableHiding: false,
                    enableSorting: true,
                    field: 'deliveringDate'
                  }, {
                    enableFiltering: false,
                    enableHiding: false,
                    enableSorting: true,
                    field: 'repairingImporter'
                  }, {
                    enableFiltering: false,
                    enableHiding: false,
                    enableSorting: true,
                    field: 'repairingDealer'
                  }
                ],
                data: vm.listVehicleInformations,
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
            } else if (content) {
              vm.content = content;
              that.sizeModal = size || 'md';
            } else if (labourGrid != null) {
              that.sizeModal = '';
              vm.labourInformations = labourGrid;

              vm.gridOptions = {
                columnDefs: [
                  {
                    displayName: 'Operation Code',
                    enableFiltering: false,
                    enableHiding: false,
                    enableSorting: true,
                    field: 'operationCode',
                    width: 120
                  }, {
                    enableFiltering: false,
                    enableHiding: false,
                    enableSorting: true,
                    field: 'description'
                  }
                ],
                data: vm.labourInformations,
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

            vm.closeModal = function () {
              $uibModalInstance.close();
            };
            /**
             *
             */
            vm.ok = function () {
              that.DispatcherService.publish('modalclose:' + topic, true);
              $uibModalInstance.close();
            };

            /**
             *
             */
            vm.cancel = function () {
              that.DispatcherService.publish('modalclose:' + topic, false);
              $uibModalInstance.dismiss('cancel');
            };

            /**
             *
             */
            vm.setCode = function (code) {
              that.DispatcherService.publish('modalclose:' + topic, code);
              $uibModalInstance.close();
            };

            /**
             *
             *
             * @param {any} chassisNo
             * @param {any} chassisSeries
             */
            vm.getVehicleInfo = function (chassisNo, chassisSeries) {
              if ((chassisNo) && (chassisSeries)) {
                that.DispatcherService.publish('modalclose:' + topic, false);
                $uibModalInstance.dismiss('cancel');
                that.DispatcherService.publish('vehicleInformation:getChassisData', {chassisNo, chassisSeries});
                that.DispatcherService.publish('modalclose:' + topic, false);
                $uibModalInstance.dismiss('cancel');
              }
            };
          }
        ],
        controllerAs: 'vm',
        keyboard: keyboard || true,
        listGridResult: listGridResult || {},
        size: size || that.sizeModal,
        templateUrl: 'app/services/modal/' + customTmpl
      });
    }
    /**
     *
     *
     * @param {any} data
     *
     * @memberOf ModalService
     */
    viewCoveragevehicleInformationResultHandler(data) {
      console.log(data);
      console.log(data.chassisNo);
    }

  }

  angular
    .module('uchpClientAngularApp')
    .service('ModalService', ModalService);
}
