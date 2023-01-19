/**
 * Standardclaimsearchresult model events
 */

'use strict';

import {EventEmitter} from 'events';
import Standardclaimsearchresult from './standardclaimsearchresult.model';
var StandardclaimsearchresultEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
StandardclaimsearchresultEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Standardclaimsearchresult.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    StandardclaimsearchresultEvents.emit(event + ':' + doc._id, doc);
    StandardclaimsearchresultEvents.emit(event, doc);
  }
}

export default StandardclaimsearchresultEvents;
