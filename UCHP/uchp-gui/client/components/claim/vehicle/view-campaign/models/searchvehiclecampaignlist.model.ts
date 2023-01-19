/**
 * Created by a148094 on 20-09-2016.
 */

/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  export class SearchVehicleCampaignListModel {
    campaignId: Number;
    sccCode: String;
    sccType: String;
    companyNo: Number;
    title: String;
    campaignStateTime: String;
    campOwner: Number;
    campaignState: Number;
    currency: String;
    responsible: String;
    materialRegressCode: String;
    localCampaign: Boolean;
    startDate: String;
    problemDescription: String;
    suggestedAction: String;
    problemSolved: Number;
    problemSolvComment: String;
    defectCode: String;
    functionGroup: String;
    lastClaimSubmDate: String;
    lastRepairDate: String;
    debitCode: Number;
    vptContrMtrlPerc: Number;
    vptContrMtrlCost: Number;
    vptContrLabourPerc: Number;
    vptContrLabourCost: Number;
    vptContrOtherCostPerc: Number;
    vptContrOtherCost: Number;
    hasVehicleSelection: Boolean;
    vehTypeMinMileage: Number;
    vehTypeMaxMileage: Number;
    vehTypeMinOperationHours: Number;
    vehTypeMaxOperationHours: Number;
    impRegionUsedToMatchClaimJob: Boolean;
    vehMinAge: Number;
    vehMaxAge: Number;
    forceTSU: Boolean;
    anyMaterialAllowed: Boolean;
    forceTSUModifiedBy: String;
    forceTSUModifyTime: String;
    forceTSUComment: String;
    stairsId: Number;
    modifiedBy: String;
    modifyTime: String;
/*    materialList: [];
    causalPartList: [];
    labourList: [];
    otherCostList: [];
    vehicleList: [];*/
  }
}
