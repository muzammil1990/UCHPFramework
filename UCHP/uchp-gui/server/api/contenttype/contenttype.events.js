/**
 * Contenttype model events
 */

'use strict';

import {EventEmitter} from 'events';
import Contenttype from './contenttype.model';
var ContenttypeEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
ContenttypeEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Contenttype.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    ContenttypeEvents.emit(event + ':' + doc._id, doc);
    ContenttypeEvents.emit(event, doc);
  }
}

export default ContenttypeEvents;
