/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/claim/manageclaim/existingclaim/1/1/1/1/tabs/LabourTab%2CMaterialTab%2CTravelTab%2CHistoryTab%2CCostTab%2CRemarksTab%2CCostTransactionTab%2CInspectorReportTab?isNoVehicle=true&repairingImporter=1&repairingDealer=1&appealState=1&userCompanyNo=1 ->  index
 * POST    /api/claim/manageclaim/existingclaim/              ->  create
 * GET     /api/claim/manageclaim/existingclaim/:id          ->  show
 * PUT     /api/claim/manageclaim/existingclaim/:id          ->  update
 * DELETE  /api/claim/manageclaim/existingclaim/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Claimjob from './claimjob.model';

function respondWithResult(res, statusCode) {
  statusCode = statusCode || 200;
  return function(entity) {
    if (entity) {
      res.status(statusCode).json(entity);
    }
  };
}

function saveUpdates(updates) {
  return function(entity) {
    var updated = _.merge(entity, updates);
    return updated.save()
      .then(updated => {
        return updated;
      });
  };
}

function removeEntity(res) {
  return function(entity) {
    if (entity) {
      return entity.remove()
        .then(() => {
          res.status(204).end();
        });
    }
  };
}

function handleEntityNotFound(res) {
  return function(entity) {
    if (!entity) {
      res.status(404).end();
      return null;
    }
    return entity;
  };
}

function handleError(res, statusCode) {
  statusCode = statusCode || 500;
  return function(err) {
    res.status(statusCode).send(err);
  };
}

// Gets a list of Claimjobs
export function index(req, res) {
  return Claimjob.find().exec()
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Gets a single Claimjob from the DB
export function show(req, res) {
  return Claimjob.findOne().exec()
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Claimjob in the DB
export function create(req, res) {
  return Claimjob.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Claimjob in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Claimjob.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Claimjob from the DB
export function destroy(req, res) {
  return Claimjob.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
