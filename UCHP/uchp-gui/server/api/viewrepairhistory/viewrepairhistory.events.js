/**
 * Viewrepairhistory model events
 */

'use strict';

import {EventEmitter} from 'events';
import Viewrepairhistory from './viewrepairhistory.model';
var ViewrepairhistoryEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ViewrepairhistoryEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Viewrepairhistory.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ViewrepairhistoryEvents.emit(event + ':' + doc._id, doc);
    ViewrepairhistoryEvents.emit(event, doc);
  }
}

export default ViewrepairhistoryEvents;
