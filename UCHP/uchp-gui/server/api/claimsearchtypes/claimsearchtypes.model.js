'use strict';

import mongoose from 'mongoose';

var ClaimsearchtypesSchema = new mongoose.Schema({
  key: String,
  displayText: String
});

export default mongoose.model('Claimsearchtypes', ClaimsearchtypesSchema);
