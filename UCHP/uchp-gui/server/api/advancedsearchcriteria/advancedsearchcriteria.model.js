'use strict';

import mongoose from 'mongoose';

var AdvancedsearchcriteriaSchema = new mongoose.Schema({
  claimJobStatuses: [],
  claimJobTypes: [],
  claimSearchTypes: [],
  tmaStatuses: []
});

export default mongoose.model('Advancedsearchcriteria', AdvancedsearchcriteriaSchema);
