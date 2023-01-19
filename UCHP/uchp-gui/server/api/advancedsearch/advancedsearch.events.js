/**
 * Advancedsearch model events
 */

'use strict';

import {EventEmitter} from 'events';
import Advancedsearch from './advancedsearch.model';
var AdvancedsearchEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
AdvancedsearchEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Advancedsearch.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    AdvancedsearchEvents.emit(event + ':' + doc._id, doc);
    AdvancedsearchEvents.emit(event, doc);
  }
}

export default AdvancedsearchEvents;
