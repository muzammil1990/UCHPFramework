/**
 * Claimlistsearchcriteria model events
 */

'use strict';

import {EventEmitter} from 'events';
import Claimlistsearchcriteria from './claimlistsearchcriteria.model';
var ClaimlistsearchcriteriaEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ClaimlistsearchcriteriaEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Claimlistsearchcriteria.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ClaimlistsearchcriteriaEvents.emit(event + ':' + doc._id, doc);
    ClaimlistsearchcriteriaEvents.emit(event, doc);
  }
}

export default ClaimlistsearchcriteriaEvents;
