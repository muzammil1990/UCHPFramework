/**
 * Claimsearchresult model events
 */

'use strict';

import {EventEmitter} from 'events';
import Claimsearchresult from './claimsearchresult.model';
var ClaimsearchresultEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ClaimsearchresultEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Claimsearchresult.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ClaimsearchresultEvents.emit(event + ':' + doc._id, doc);
    ClaimsearchresultEvents.emit(event, doc);
  }
}

export default ClaimsearchresultEvents;
