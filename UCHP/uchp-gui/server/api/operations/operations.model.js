'use strict';

import mongoose from 'mongoose';

var OperationsSchema = new mongoose.Schema({
  metadata: Object,
  operationSearchResultList: Array
});

export default mongoose.model('Operations', OperationsSchema);
