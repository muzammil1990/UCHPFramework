/**
 * Claimjob model events
 */

'use strict';

import {EventEmitter} from 'events';
import Claimjob from './claimjob.model';
var ClaimjobEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ClaimjobEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Claimjob.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ClaimjobEvents.emit(event + ':' + doc._id, doc);
    ClaimjobEvents.emit(event, doc);
  }
}

export default ClaimjobEvents;
