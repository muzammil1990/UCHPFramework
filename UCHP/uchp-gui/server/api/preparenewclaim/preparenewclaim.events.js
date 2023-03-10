/**
 * Preparenewclaim model events
 */

'use strict';

import {EventEmitter} from 'events';
import Preparenewclaim from './preparenewclaim.model';
var PreparenewclaimEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
PreparenewclaimEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Preparenewclaim.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    PreparenewclaimEvents.emit(event + ':' + doc._id, doc);
    PreparenewclaimEvents.emit(event, doc);
  }
}

export default PreparenewclaimEvents;
