/**
 * Mitypes model events
 */

'use strict';

import {EventEmitter} from 'events';
import Mitypes from './mitypes.model';
var MitypesEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
MitypesEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Mitypes.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    MitypesEvents.emit(event + ':' + doc._id, doc);
    MitypesEvents.emit(event, doc);
  }
}

export default MitypesEvents;
