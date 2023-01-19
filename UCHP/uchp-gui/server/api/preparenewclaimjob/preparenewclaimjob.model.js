'use strict';

import mongoose from 'mongoose';

// const where we can assign proper value to mongoose Schema if '' as null is not enough
var NULL = '';

var PreparenewclaimjobSchema = new mongoose.Schema({
});

export default mongoose.model('Preparenewclaimjob', PreparenewclaimjobSchema);
