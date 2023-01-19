/**
 * Viewcoverage model events
 */

'use strict';

import {EventEmitter} from 'events';
import Viewcoverage from './viewcoverage.model';
var ViewcoverageEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ViewcoverageEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Viewcoverage.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ViewcoverageEvents.emit(event + ':' + doc._id, doc);
    ViewcoverageEvents.emit(event, doc);
  }
}

export default ViewcoverageEvents;
