'use strict';

import mongoose from 'mongoose';

var ClaimjobstatusesSchema = new mongoose.Schema({
  key: String,
  displayText: String
});

export default mongoose.model('Claimjobstatuses', ClaimjobstatusesSchema);
