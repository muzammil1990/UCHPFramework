'use strict';

import mongoose from 'mongoose';

var VehicleinformationSchema = new mongoose.Schema({
  vin: String,
  chassisSeries: String,
  chassisNo: String,
  countryOfOperation: String,
  registrationNo: String,
  deliveringImporter: Number,
  deliveringDate: String,
  deliveringDealer: String,
  latestKnownMileageDate: String,
  latestKnownMileageKm: Number,
  latestKnownMileageMiles: Number,
  vehicleOperation: Number,
  vehicleOperationDesc: String,
  companyNo: Number,
  customerNo: String,
  customerName: String,
  marketingType: String,
  ageVehicle: Number,
  contractNo: String,
  demoDeliveringDate: String,
  shippedInvoicedDate: String,
  operatingMeasureType: Number,
  latestKnownOperatingHours: Number,
  brand: Number,
  deliveryMileageKm: Number,
  deliveryMileageMiles: Number,
  unitNo: String,
  operatingHoursDate: String,
  repairingImporter: Number,
  repairingDealer: String,
  coverageList: [],
  repairHistoryList: [],
  campaignList: []
});

export default mongoose.model('Vehicleinformation', VehicleinformationSchema);
