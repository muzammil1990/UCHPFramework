/**
 * Created by a232625 on 2016-07-22.
 */
'use strict';

import mongoose from 'mongoose';

var UsersettingSchema = new mongoose.Schema({
  settingtype: String,
  value: String,
  description: String,
  userid: String,
  usersettingcompanyno: Number
});

export default mongoose.model('Usersetting', UsersettingSchema);
