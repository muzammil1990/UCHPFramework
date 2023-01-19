/**
 * Created by a232625 on 2016-07-22.
 */
'use strict';

import mongoose from 'mongoose';

var UsergroupSchema = new mongoose.Schema({
  userid: String,
  usergroup: String,
  objversion: Number,
  companyno: Number
});

export default mongoose.model('Usergroup', UsergroupSchema);
