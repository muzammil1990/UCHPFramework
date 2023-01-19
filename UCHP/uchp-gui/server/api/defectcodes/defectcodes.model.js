'use strict';

import mongoose from 'mongoose';

var DefectcodesSchema = new mongoose.Schema({
  metadata: Object,
  defectCodeDTOList: Array
});

export default mongoose.model('Defectcodes', DefectcodesSchema);
