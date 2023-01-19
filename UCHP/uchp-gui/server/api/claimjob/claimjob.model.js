'use strict';

import mongoose from 'mongoose';

var ClaimjobSchema = new mongoose.Schema({
  labourList: [],
  includedMaterialList: [],
  newPartNo: Number,
  newPartDescription: String,
  travelList: [],
  costList: [],
  otherCostCategoryList: [],
  applyVATBasedOnMaterial: Boolean,
  historyList: [],
  appealStateList: [],
  costTransactionSummaryDTO: [],
  inspectorReportStatuses: [],
  matchedInspectorReport: Object,
  matchedInspectorReportList: [],
  released: Boolean
});

export default mongoose.model('Claimjob', ClaimjobSchema);
