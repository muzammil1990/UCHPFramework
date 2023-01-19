/**
 * Creditdocument model events
 */

'use strict';

import {EventEmitter} from 'events';
import Returncodes from './returncodes.model';
var ReturncodesEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ReturncodesEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Returncodes.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ReturncodesEvents.emit(event + ':' + doc._id, doc);
    ReturncodesEvents.emit(event, doc);
  }
}

export default ReturncodesEvents;
