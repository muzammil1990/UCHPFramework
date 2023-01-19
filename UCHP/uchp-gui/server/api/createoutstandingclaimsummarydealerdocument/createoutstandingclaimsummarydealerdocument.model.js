'use strict';

import mongoose from 'mongoose';

var CreateoutstandingclaimsummarydealerdocumentSchema = new mongoose.Schema({
  name: String,
  info: String,
  active: Boolean
});

export default mongoose.model('Createoutstandingclaimsummarydealerdocument', CreateoutstandingclaimsummarydealerdocumentSchema);
