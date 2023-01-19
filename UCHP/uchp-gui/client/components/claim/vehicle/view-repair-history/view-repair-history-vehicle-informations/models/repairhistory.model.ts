/// <reference path="../../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  export class RepairHistoryModel {
    claimJobId: Number;
    repairDate: Date;
    repairingDealer: String;
    repairingImporter: Number;
    repairOrderNo: String;
    refNo: Number;
    ageVehicle: Number;
    vehicleMileage: Number;
    operatingHours: Number;
    jobNo: Number;
    causalPartNo: String;
    causalPartNoPrefix: String;
    scc: String;
    mainOperationNo: String;
    operatingMeasureType: Number;
    headClaimHeaderId: Number;
    headCompanyNo: Number;
  }
}
