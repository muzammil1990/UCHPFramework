/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';

  class ClaimJobItemMaterialController {
    private fresh: any;
    private replaced: boolean;
    private form: any;
    private includedMaterialList: any;
    private causalPart: any;
    private claimParams: any;
    private setDefectCodeSubscription: any;
    private claimJobSubscription: any;
    private newPart: any;
    private defectcodes: any;
    private factoryMaterialNetTotal: any;
    private currency: any;

    static $inject = [
      'ClaimJobItemStore',
      '$scope',
      'NewClaimService',
      'DefectCodesStore',
      'ModalService',
      'DispatcherService',
      'ClaimJobStore'
    ];
    constructor(private ClaimJobItemStore: IClaimJobItemStore,
                private $scope: ng.IScope,
                private NewClaimService: INewClaimService,
                private DefectCodesStore: IDefectCodesStore,
                private modal: IModalService,
                private DispatcherService: IDispatcherService,
                private ClaimJobStore: IClaimJobStore
    ) {
      var that = this;
      that.claimJobSubscription = that.DispatcherService.subscribe('claimJob:getClaimJobItemMaterialTab',
        that.claimJobResultHandler.bind(this));
      that.setDefectCodeSubscription = that.DispatcherService.subscribe('modalclose:setDefectCode',
        that.setDefectCodeHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.setDefectCodeSubscription.unsubscribe();
        that.claimJobSubscription.unsubscribe();
      });
      $scope.$watch('ctrl.form.$pristine', function(v){
        if (v || that.form.$invalid) {
          return;
        }
        that.form.$setPristine();
        that.updateReadyObject();
      }, true);
      that.newPart = {
        approvedMaterialNetCost: 0,
        claimMaterialId: 0,
        costAdjustFactor: 0,
        defectCode: '',
        defectCodeDescription: '',
        deletedInGUI: false,
        designation: '',
        exchangePart: false,
        functionGroup: '',
        jobClaimJobId: '',
        jobHeadCompanyNo: '',
        materialDebitList: [
          {
            claimMaterialDebitId: '',
            colNo: 0,
            coverageId: '',
            debitCode: 0,
            debitCodeCategory: 0,
            debitCodeType: 0,
            materialClaimMaterialId: 0,
            modifiedBy: '',
            modifyTime: '',
            netCost: 0,
            netCostImporter: 0,
            rejectCode: ''
          }
        ],
        materialDescr: '',
        materialNetCost: 0,
        materialNetCostOriginal: 0,
        materialType: 0,
        modifiedBy: '',
        modifyTime: '',
        partMarkup: 0,
        partNo: '',
        partNoPrefix: '',
        priceSource: 0,
        quantity: 0,
        rejectCode: '',
        rejectCodeDescription: '',
        rejectedMaterialNetCost: 0,
        repaired: false,
        serialNo: '',
        supersededPartNo: '',
        supersededPartNoPrefix: '',
        unitPrice: 0
      };
      if (that.fresh) {
        that.includedMaterialList = [];
        that.causalPart = {};
        that.causalPart = angular.copy(that.newPart);
        that.includedMaterialList.push(angular.copy(that.newPart));
        that.replaced = true;
        // MOCKED defectcodes
        // TODO: refactor get these codes
        that.defectcodes = [
          {
            companyNo: 1,
            defectCode: '100',
            description: 'Grating noise 0',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 0',
            typeOfConcern: 1
          }, {
            companyNo: 1,
            defectCode: '101',
            description: 'Grating noise 1',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 1',
            typeOfConcern: 1
          }, {
            companyNo: 1,
            defectCode: '102',
            description: 'Grating noise 2',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 2',
            typeOfConcern: 1
          }, {
            companyNo: 1,
            defectCode: '103',
            description: 'Grating noise 3',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 3',
            typeOfConcern: 1
          }, {
            companyNo: 1,
            defectCode: '104',
            description: 'Grating noise 4',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 4',
            typeOfConcern: 1
          }, {
            companyNo: 1,
            defectCode: '105',
            description: 'Grating noise 5',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 5',
            typeOfConcern: 1
          }, {
            companyNo: 1,
            defectCode: '106',
            description: 'Grating noise 6',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 6',
            typeOfConcern: 1
          }, {
            companyNo: 1,
            defectCode: '107',
            description: 'Grating noise 7',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 7',
            typeOfConcern: 1
          }, {
            companyNo: 1,
            defectCode: '108',
            description: 'Grating noise 8',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 8',
            typeOfConcern: 1
          }, {
            companyNo: 1,
            defectCode: '109',
            description: 'Grating noise 9',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 9',
            typeOfConcern: 1
          }, {
            companyNo: 1,
            defectCode: '1010',
            description: 'Grating noise 10',
            modifiedBy: 'SystemAdmin',
            modifyTime: '2012-12-11T23:00:00.000+0000',
            objVersion: 1,
            preferredDescription: 'Grating noise 10',
            typeOfConcern: 1
          }
        ];
      }
    }

    claimJobResultHandler(claimJobItemData: any) {
      var that = this;
      that.getDefectCodes();
      that.includedMaterialList = claimJobItemData && claimJobItemData.includedMaterialList &&
      angular.isArray(claimJobItemData.includedMaterialList) ? claimJobItemData.includedMaterialList : [];
      that.causalPart = claimJobItemData && claimJobItemData.causalPart ? claimJobItemData.causalPart : {};
      that.factoryMaterialNetTotal = claimJobItemData && claimJobItemData.costTransactionSummaryDTO &&
      claimJobItemData.costTransactionSummaryDTO.factoryMaterialNetTotal ?
        claimJobItemData.costTransactionSummaryDTO.factoryMaterialNetTotal : '';
      that.currency = claimJobItemData && claimJobItemData.costTransactionSummaryDTO &&
      claimJobItemData.costTransactionSummaryDTO.currency ? claimJobItemData.costTransactionSummaryDTO.currency : '';
    }

    getDefectCodes() {
      var that = this;
      that.claimParams = that.ClaimJobStore.getClaimParams();
      that.DefectCodesStore.getDefectCodes({
        companyNo: that.claimParams.companyNo,
        typeOfConcern: 1
      })
      .then(function (defectcodes: any) {
        if (defectcodes && angular.isArray(defectcodes)) {
          that.defectcodes = defectcodes;
        }
      });
    }

    updateReadyObject() {
      var that = this;
      that.NewClaimService.addMaterial(that.causalPart, that.includedMaterialList);
      that.DispatcherService.publish('claimJob:SaveEnabled', {});
    }


    showDefectCodeModal() {
      var that = this;
      that.modal.open(null, 'setDefectCode', 'md', true, null,
        'defectcodemodal.html', null, that.defectcodes);
    }

    setDefectCodeHandler(defectcode) {
      var that = this;
      that.causalPart.defectCodeDescription = defectcode.description;
      that.causalPart.defectCode = defectcode.defectCode;
    }

    deleteRowInGui($index) {
      var that = this;
      that.includedMaterialList[$index].deletedInGUI = true;
      console.log(that.includedMaterialList);
    };

    checkDefectCodeMatch() {
      var that = this;
      let index = _.findIndex(that.defectcodes, function(o) {
        return o.defectCode === that.causalPart.defectCode;
      });
      if (index !== -1) {
        that.causalPart.defectCodeDescription = that.defectcodes[index].description;
      } else {
        that.causalPart.defectCodeDescription = '';
      }
    };

    addRow() {
      var that = this;
      that.claimParams = that.ClaimJobStore.getClaimParams();
      that.newPart.jobClaimJobId = that.claimParams.claimJobId;
      that.newPart.jobHeadCompanyNo = that.claimParams.companyNo;
      that.includedMaterialList.push(angular.copy(that.newPart));
    };

    copyRow() {
      var that = this;
      let lastIndex = this.includedMaterialList - 1;
      let newRow = angular.copy(that.includedMaterialList[lastIndex]);
      newRow.deletedInGUI = false;
      newRow.repaired = false;
      that.includedMaterialList.push(newRow);
    };
  }

  angular
    .module('uchpClientAngularApp')
    .component('claimJobItemMaterial', {
      controller: [
        'ClaimJobItemStore',
        '$scope',
        'NewClaimService',
        'DefectCodesStore',
        'ModalService',
        'DispatcherService',
        'ClaimJobStore',
        ClaimJobItemMaterialController
      ],
      bindings: {
        fresh: '<'
      },
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/job/claim-job-item/claim-job-item-material/claim-job-item-material.html'
    });
}
