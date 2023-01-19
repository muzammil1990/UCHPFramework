'use strict';

import mongoose from 'mongoose';

var ContenttypeSchema = new mongoose.Schema({
  key: String,
  displayText: String
});

export default mongoose.model('Contenttype', ContenttypeSchema);
