'use strict';

import mongoose from 'mongoose';

var CreditdocumentSchema = new mongoose.Schema({
  companyNo: Number,
  companyName: String,
  importerNo: Number,
  importerName: String,
  dealerNo: String,
  dealerName: String,
  creditDocumentId: String,
  creditingDate: Date,
  creditingDocumentTypeId: Number,
  creditingDocumentIssuerTypeId: Number,
  creditOccId: Number,
  mimeType: String,
  modifiedBy: String,
  modifyTime: Date,
  make: Number
});

export default mongoose.model('Creditdocument', CreditdocumentSchema);
