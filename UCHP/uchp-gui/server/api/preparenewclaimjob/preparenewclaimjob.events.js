/**
 * Preparenewclaimjob model events
 */

'use strict';

import {EventEmitter} from 'events';
import Preparenewclaimjob from './preparenewclaim.model';
var PreparenewclaimjobEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
PreparenewclaimjobEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Preparenewclaimjob.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    PreparenewclaimjobEvents.emit(event + ':' + doc._id, doc);
    PreparenewclaimjobEvents.emit(event, doc);
  }
}

export default PreparenewclaimjobEvents;
