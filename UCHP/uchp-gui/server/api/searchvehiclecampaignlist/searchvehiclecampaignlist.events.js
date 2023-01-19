/**
 * Searchvehiclecampaignlist model events
 */

'use strict';

import {EventEmitter} from 'events';
import Searchvehiclecampaignlist from './searchvehiclecampaignlist.model';
var SearchvehiclecampaignlistEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
SearchvehiclecampaignlistEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Searchvehiclecampaignlist.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    SearchvehiclecampaignlistEvents.emit(event + ':' + doc._id, doc);
    SearchvehiclecampaignlistEvents.emit(event, doc);
  }
}

export default SearchvehiclecampaignlistEvents;
