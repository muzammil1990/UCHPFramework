/**
 * TmaStatuses model events
 */

'use strict';

import {EventEmitter} from 'events';
import TmaStatuses from './tmaStatuses.model';
var TmaStatusesEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
TmaStatusesEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  TmaStatuses.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    TmaStatusesEvents.emit(event + ':' + doc._id, doc);
    TmaStatusesEvents.emit(event, doc);
  }
}

export default TmaStatusesEvents;
