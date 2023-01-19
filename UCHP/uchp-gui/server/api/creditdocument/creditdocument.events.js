/**
 * Creditdocument model events
 */

'use strict';

import {EventEmitter} from 'events';
import Creditdocument from './creditdocument.model';
var CreditdocumentEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
CreditdocumentEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Creditdocument.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    CreditdocumentEvents.emit(event + ':' + doc._id, doc);
    CreditdocumentEvents.emit(event, doc);
  }
}

export default CreditdocumentEvents;
