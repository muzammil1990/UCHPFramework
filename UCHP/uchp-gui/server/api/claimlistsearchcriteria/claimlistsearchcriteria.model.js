'use strict';

import mongoose from 'mongoose';

var ClaimlistsearchcriteriaSchema = new mongoose.Schema({
  claimSearchTypes: [],
  typeOfClaims: [],
  miTypes: [],
  miPrintStatuses: [],
  appealStatuses: []
});

export default mongoose.model('Claimlistsearchcriteria', ClaimlistsearchcriteriaSchema);
