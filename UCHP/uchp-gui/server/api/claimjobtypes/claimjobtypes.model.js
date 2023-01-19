'use strict';

import mongoose from 'mongoose';

var ClaimjobtypesSchema = new mongoose.Schema({
  key: String,
  displayText: String
});

export default mongoose.model('Claimjobtypes', ClaimjobtypesSchema);
