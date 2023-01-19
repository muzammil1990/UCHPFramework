'use strict';

import mongoose from 'mongoose';

var ViewrepairhistorySchema = new mongoose.Schema({
    claimJobId: Number,
    repairDate: Date,
    repairingDealer: String,
    repairingImporter: Number,
    repairOrderNo: String,
    refNo: Number,
    ageVehicle: Number,
    vehicleMileage: Number,
    operatingHours: Number,
    jobNo: Number,
    causalPartNo: String,
    causalPartNoPrefix: String,
    scc: String,
    mainOperationNo: String,
    operatingMeasureType: Number,
    headClaimHeaderId: Number,
    headCompanyNo: Number
});

export default mongoose.model('Viewrepairhistory', ViewrepairhistorySchema);
