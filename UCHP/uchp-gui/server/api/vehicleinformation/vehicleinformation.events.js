/**
 * Vehicleinformation model events
 */

'use strict';

import {EventEmitter} from 'events';
import Vehicleinformation from './vehicleinformation.model';
var VehicleinformationEvents = new EventEmitter();

// Set max event listeners (0 == unlimited)
VehicleinformationEvents.setMaxListeners(0);

// Model events
var events = {
  'save': 'save',
  'remove': 'remove'
};

// Register the event emitter to the model events
for (var e in events) {
  var event = events[e];
  Vehicleinformation.schema.post(e, emitEvent(event));
}

function emitEvent(event) {
  return function(doc) {
    VehicleinformationEvents.emit(event + ':' + doc._id, doc);
    VehicleinformationEvents.emit(event, doc);
  }
}

export default VehicleinformationEvents;
