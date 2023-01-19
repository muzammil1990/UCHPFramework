/**
 * Appealstatuses model events
 */

'use strict';

import {EventEmitter} from 'events';
import Appealstatuses from './appealstatuses.model';
var AppealstatusesEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
AppealstatusesEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Appealstatuses.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    AppealstatusesEvents.emit(event + ':' + doc._id, doc);
    AppealstatusesEvents.emit(event, doc);
  }
}

export default AppealstatusesEvents;
