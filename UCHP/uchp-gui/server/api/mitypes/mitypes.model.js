'use strict';

import mongoose from 'mongoose';

var MitypesSchema = new mongoose.Schema({
  key: String,
  displayText: String
});

export default mongoose.model('Mitypes', MitypesSchema);
