'use strict';

import mongoose from 'mongoose';

// const where we can assign proper value to mongoose Schema if '' as null is not enough
var NULL = '';

var PreparenewclaimSchema = new mongoose.Schema({
  claimRepairHeaderDTO: Array,
  claimSupplementDTO: Array,
  tmaDecisionInfoPVO: Array,
  claimJobTypes: Array,
  selectedClaimJobs: [],
  allMINames: [],
  casenoSourceList: [],
  appealStateList: [],
  claimValidationErrorTextDTOList: NULL,
  vehicleOperationAuths: [],
  workingWithSelectedClaimJobs: Boolean,
  activeTab: NULL,
  matchedInspectorReport: Object,
  matchedInspectorReportList: [],
  inspectorReportAndVehicleMap: {},
  activeJobHasErrors: Boolean,
  activeJobIdFromVerticalTab: NULL,
  jobListSize: Number,
  labourTabLoaded: Boolean,
  claimJobInfoTabLoaded: Boolean,
  remarksTabLoaded: Boolean,
  assessmentMode: String,
  assessmentCostType: Number,
  rejectedDebitCodes: NULL,
  disableVehicleInfo: Boolean,
  popupClaim: Boolean,
  supplementChanged: Boolean,
  claimChanged: Boolean,
  noChange: Boolean,
  warningFlag: Boolean,
  campaign: Object,
  selectedCausalPartId: NULL,
  technicalDataUrl: NULL,
  technicalDataUrl2: NULL,
  uchpCampaignVehicleMaster: Boolean,
  allowCopyClaimJob: false,
  viewCampaignUrl: String,
  applyVATBasedOnMaterial: Boolean,
  dbAssessmentDTO: Object,
  inspectorReportStatuses: [],
  companyList: [],
  makeList: [],
  mileageUnitList: Array,
  yesNoList: [],
  otherCostCategoryList: []
});

export default mongoose.model('Preparenewclaim', PreparenewclaimSchema);
