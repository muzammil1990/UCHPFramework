'use strict';

import mongoose from 'mongoose';

var ReturncodesSchema = new mongoose.Schema({
  metadata: Object,
  returnCodeDTOList: Array
});

export default mongoose.model('Returncodes', ReturncodesSchema);
