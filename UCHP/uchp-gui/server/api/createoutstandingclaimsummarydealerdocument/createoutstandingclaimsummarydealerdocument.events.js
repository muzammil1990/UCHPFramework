/**
 * Createoutstandingclaimsummarydealerdocument model events
 */

'use strict';

import {EventEmitter} from 'events';
import Createoutstandingclaimsummarydealerdocument from './createoutstandingclaimsummarydealerdocument.model';
var CreateoutstandingclaimsummarydealerdocumentEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
CreateoutstandingclaimsummarydealerdocumentEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Createoutstandingclaimsummarydealerdocument.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    CreateoutstandingclaimsummarydealerdocumentEvents.emit(event + ':' + doc._id, doc);
    CreateoutstandingclaimsummarydealerdocumentEvents.emit(event, doc);
  }
}

export default CreateoutstandingclaimsummarydealerdocumentEvents;
