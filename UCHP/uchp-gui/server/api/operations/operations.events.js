/**
 * Creditdocument model events
 */

'use strict';

import {EventEmitter} from 'events';
import Operations from './operations.model';
var OperationsEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
OperationsEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Operations.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    OperationsEvents.emit(event + ':' + doc._id, doc);
    OperationsEvents.emit(event, doc);
  }
}

export default OperationsEvents;
