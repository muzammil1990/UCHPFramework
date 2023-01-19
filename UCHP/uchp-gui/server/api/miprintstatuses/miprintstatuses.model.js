'use strict';

import mongoose from 'mongoose';

var MiprintstatusesSchema = new mongoose.Schema({
  key: String,
  displayText: String
});

export default mongoose.model('Miprintstatuses', MiprintstatusesSchema);
