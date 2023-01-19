/**
 * Creditdocument model events
 */

'use strict';

import {EventEmitter} from 'events';
import Defectcodes from './defectcodes.model';
var DefectcodesEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
DefectcodesEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Defectcodes.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    DefectcodesEvents.emit(event + ':' + doc._id, doc);
    DefectcodesEvents.emit(event, doc);
  }
}

export default DefectcodesEvents;
