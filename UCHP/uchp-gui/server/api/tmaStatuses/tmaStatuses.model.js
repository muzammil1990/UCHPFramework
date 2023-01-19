'use strict';

import mongoose from 'mongoose';

var TmaStatusesSchema = new mongoose.Schema({
  key: String,
  displayText: String
});

export default mongoose.model('TmaStatuses', TmaStatusesSchema);
