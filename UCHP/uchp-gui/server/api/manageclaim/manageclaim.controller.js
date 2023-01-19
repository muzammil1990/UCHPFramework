/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/claim/manageclaim/existingclaim/1/1/1?userCompanyNo=1 ->  index
 * POST    /api/claim/manageclaim/existingclaim/              ->  create
 * GET     /api/claim/manageclaim/existingclaim/:id          ->  show
 * PUT     /api/claim/manageclaim/existingclaim/:id          ->  update
 * DELETE  /api/claim/manageclaim/existingclaim/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Manageclaim from './manageclaim.model';

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

// Gets a list of Manageclaims
export function index(req, res) {
  return Manageclaim.findOne().exec()
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Gets a single Manageclaim from the DB
export function show(req, res) {
  return Manageclaim.findOne().exec()
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Manageclaim in the DB
export function create(req, res) {
  return Manageclaim.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Manageclaim in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Manageclaim.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Manageclaim from the DB
export function destroy(req, res) {
  return Manageclaim.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
