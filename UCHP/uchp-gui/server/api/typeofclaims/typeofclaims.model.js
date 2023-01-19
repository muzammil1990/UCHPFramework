'use strict';

import mongoose from 'mongoose';

var TypeofclaimsSchema = new mongoose.Schema({
  key: String,
  displayText: String
});

export default mongoose.model('Typeofclaims', TypeofclaimsSchema);
