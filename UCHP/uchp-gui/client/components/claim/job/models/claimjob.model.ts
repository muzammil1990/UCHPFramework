/**
 * Created by a232625 on 2016-08-05.
 */
/// <reference path="../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  export class ClaimJobModel {
    labourList: [any];
    includedMaterialList: [any];
    newPartNo: Number;
    newPartDescription: String;
    travelList: [any];
    costList: [any];
    otherCostCategoryList: [any];
    applyVATBasedOnMaterial: Boolean;
    historyList: [any];
    appealStateList: [any];
    costTransactionSummaryDTO: [any];
    inspectorReportStatuses: [any];
    matchedInspectorReport: Object;
    matchedInspectorReportList: [any];
    released: Boolean;
  }
}
