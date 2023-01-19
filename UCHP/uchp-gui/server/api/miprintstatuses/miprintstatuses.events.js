/**
 * Miprintstatuses model events
 */

'use strict';

import {EventEmitter} from 'events';
import Miprintstatuses from './miprintstatuses.model';
var MiprintstatusesEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
MiprintstatusesEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Miprintstatuses.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    MiprintstatusesEvents.emit(event + ':' + doc._id, doc);
    MiprintstatusesEvents.emit(event, doc);
  }
}

export default MiprintstatusesEvents;
