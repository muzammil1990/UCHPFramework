/**
 * Claimsearchtypes model events
 */

'use strict';

import {EventEmitter} from 'events';
import Claimsearchtypes from './claimsearchtypes.model';
var ClaimsearchtypesEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ClaimsearchtypesEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Claimsearchtypes.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ClaimsearchtypesEvents.emit(event + ':' + doc._id, doc);
    ClaimsearchtypesEvents.emit(event, doc);
  }
}

export default ClaimsearchtypesEvents;
