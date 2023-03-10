/**
 * Manageclaim model events
 */

'use strict';

import {EventEmitter} from 'events';
import Manageclaim from './manageclaim.model';
var ManageclaimEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ManageclaimEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Manageclaim.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ManageclaimEvents.emit(event + ':' + doc._id, doc);
    ManageclaimEvents.emit(event, doc);
  }
}

export default ManageclaimEvents;
