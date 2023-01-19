/**
 * Claimjobtypes model events
 */

'use strict';

import {EventEmitter} from 'events';
import Claimjobtypes from './claimjobtypes.model';
var ClaimjobtypesEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ClaimjobtypesEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Claimjobtypes.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ClaimjobtypesEvents.emit(event + ':' + doc._id, doc);
    ClaimjobtypesEvents.emit(event, doc);
  }
}

export default ClaimjobtypesEvents;
