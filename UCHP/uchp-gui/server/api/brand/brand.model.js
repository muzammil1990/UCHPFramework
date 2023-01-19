'use strict';

import mongoose from 'mongoose';

var BrandSchema = new mongoose.Schema({
  key: String,
  displayText: String
});

export default mongoose.model('Brand', BrandSchema);
