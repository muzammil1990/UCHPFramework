/**
 * Created by a232625 on 2016-07-22.
 */
'use strict';

import {EventEmitter} from 'events';
import Usergroup from './Usergroup.model';
var UsergroupEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
UsergroupEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Usergroup.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    UsergroupEvents.emit(event + ':' + doc._id, doc);
    UsergroupEvents.emit(event, doc);
  }
}

export default UsergroupEvents;
