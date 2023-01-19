/**
 * Claimjobstatuses model events
 */

'use strict';

import {EventEmitter} from 'events';
import Claimjobstatuses from './claimjobstatuses.model';
var ClaimjobstatusesEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ClaimjobstatusesEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Claimjobstatuses.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ClaimjobstatusesEvents.emit(event + ':' + doc._id, doc);
    ClaimjobstatusesEvents.emit(event, doc);
  }
}

export default ClaimjobstatusesEvents;
