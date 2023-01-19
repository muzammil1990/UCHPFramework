/**
 * Advancedsearchcriteria model events
 */

'use strict';

import {EventEmitter} from 'events';
import Advancedsearchcriteria from './advancedsearchcriteria.model';
var AdvancedsearchcriteriaEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
AdvancedsearchcriteriaEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Advancedsearchcriteria.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    AdvancedsearchcriteriaEvents.emit(event + ':' + doc._id, doc);
    AdvancedsearchcriteriaEvents.emit(event, doc);
  }
}

export default AdvancedsearchcriteriaEvents;
