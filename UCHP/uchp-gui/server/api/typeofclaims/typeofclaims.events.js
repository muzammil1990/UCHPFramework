/**
 * Typeofclaims model events
 */

'use strict';

import {EventEmitter} from 'events';
import Typeofclaims from './typeofclaims.model';
var TypeofclaimsEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
TypeofclaimsEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Typeofclaims.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    TypeofclaimsEvents.emit(event + ':' + doc._id, doc);
    TypeofclaimsEvents.emit(event, doc);
  }
}

export default TypeofclaimsEvents;
