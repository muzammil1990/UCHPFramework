/**
 * Created by a148094 on 13-06-2016.
 */

/// <reference path="../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  export class VehicleInformationModel {
    vin: String;
    chassisSeries: String;
    chassisNo: String;
    countryOfOperation: String;
    registrationNo: String;
    deliveringImporter: Number;
    deliveringDate: String;
    deliveringDealer: String;
    latestKnownMileageDate: String;
    latestKnownMileageKm: Number;
    latestKnownMileageMiles: Number;
    vehicleOperation: Number;
    vehicleOperationDesc: String;
    companyNo: Number;
    customerNo: String;
    customerName: String;
    marketingType: String;
    ageVehicle: Number;
    contractNo: String;
    demoDeliveringDate: String;
    shippedInvoicedDate: String;
    operatingMeasureType: Number;
    latestKnownOperatingHours: Number;
    brand: Number;
    deliveryMileageKm: Number;
    deliveryMileageMiles: Number;
    unitNo: String;
    operatingHoursDate: String;
    repairingImporter: Number;
    repairingDealer: String;
    coverageList: Array<[ViewCoverageModel]>;
    repairHistoryList: Array<[RepairHistoryModel]>;
    campaignList: Array<[SearchVehicleCampaignListModel]>;
  }
}
