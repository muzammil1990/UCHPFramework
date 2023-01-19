'use strict';

import mongoose from 'mongoose';

var ViewcoverageSchema = new mongoose.Schema({
  coverageId: String,
  companyNo: Number,
  coverageName: String,
  coverageDescription: String,
  coverageTextName: String,
  coverageTextDescription: String,
  countryCode: String,
  languageCode: String,
  coverageStartDate: Date,
  coverageEndDate: Date,
  coverageAttachmentId: String,
  coverageAttachmentName: String,
  active: Boolean

});

export default mongoose.model('Viewcoverage', ViewcoverageSchema);
