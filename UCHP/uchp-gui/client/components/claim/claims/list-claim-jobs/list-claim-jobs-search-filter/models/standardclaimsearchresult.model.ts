/**
 * Created by a148094 on 04-08-2016.
 */

module uchpClientAngularApp {
  'use strict';

  export class StandardClaimSearchResultModel {
    appealStatus: Number;
    autoFailReason: Number;
    chassisNo: String;
    chassisSeries: String;
    claimHeaderId: Number;
    claimJobId: Number;
    claimJobStateId: String;
    currency: String;
    grandTotal: String;
    headCompanyNo: Number;
    jobNo: Number;
    refNo: Number;
    objVersion: Number;
    registrationDate: String;
    repairOrderNo: String;
    repairingDealer: String;
    repairingDealerName: String;
    repairingImporter: Number;
    repairingImporterName: String;
    typeOfClaim: Number;
    isLastJob: Boolean;
    marketType: String;
    activeTmaDecision: Number;
    companyNo: Number;
    miDate: String;
    miName: String;
    miType: Number;
    remindDealerToPrint: Boolean;
    selectedClaimJob: Boolean;
    returnMtrlClaimJob: Boolean;
    removeMtrlClaimJob: Boolean;
    noActionSelectedClaimJob: Boolean;
    noActionClaimJobId: String;
  }
}
