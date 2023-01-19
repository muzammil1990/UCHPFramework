/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /uchp/api/api/claimjobstatuses              ->  index
 * POST    /uchp/api/api/claimjobstatuses              ->  create
 * GET     /uchp/api/api/claimjobstatuses/:id          ->  show
 * PUT     /uchp/api/api/claimjobstatuses/:id          ->  update
 * DELETE  /uchp/api/api/claimjobstatuses/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Claimjobstatuses from './claimjobstatuses.model';

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

// Gets a list of Claimjobstatusess
export function index(req, res) {
  return Claimjobstatuses.find().exec()
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Gets a single Claimjobstatuses from the DB
export function show(req, res) {
  return Claimjobstatuses.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Claimjobstatuses in the DB
export function create(req, res) {
  return Claimjobstatuses.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Claimjobstatuses in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Claimjobstatuses.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Claimjobstatuses from the DB
export function destroy(req, res) {
  return Claimjobstatuses.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
