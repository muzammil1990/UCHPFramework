/**
 * Created by a232625 on 2016-07-22.
 */
'use strict';

import {EventEmitter} from 'events';
import Usersetting from './usersetting.model';
var UsersettingEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
UsersettubgEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Usersetting.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    UsersettingEvents.emit(event + ':' + doc._id, doc);
    UsersettingEvents.emit(event, doc);
  }
}

export default UsersettingEvents;
