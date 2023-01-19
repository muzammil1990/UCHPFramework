'use strict';

import mongoose from 'mongoose';

var AppealstatusesSchema = new mongoose.Schema({
  key: String,
  displayText: String
});

export default mongoose.model('Appealstatuses', AppealstatusesSchema);
