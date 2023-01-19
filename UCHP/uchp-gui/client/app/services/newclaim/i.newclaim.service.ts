/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';
  export interface INewClaimService {
    get(data: any);
    addRemarks(data: any);
    addMaterial(causalPart: any, includedMaterialList: any);
    addCosts(costs: any);
    addLabour(labourList: any);
    addHistory(historyList: any);
    addAttachments(attachmentList: any);
    addExistingClaim(claimJob: any);
  }
}
