/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/claim/manageclaim/returncodes              ->  index
 * POST    /api/claim/manageclaim/returncodes             ->  create
 * GET     /api/claim/manageclaim/returncodes          ->  show
 * PUT     /api/claim/manageclaim/returncodes/:id          ->  update
 * DELETE  /api/claim/manageclaim/returncodes/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Returncodes from './returncodes.model';

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

// Gets a list of Returncodes
export function index(req, res) {
  return Returncodes.find(req.params.id).exec()
    .then(function (returncodes) {
      return res.status(200).json(returncodes);
    })
    .catch(handleError(res));
}

// Gets a single Returncodes from the DB
export function show(req, res) {
  return Returncodes.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Returncodes in the DB
export function create(req, res) {
  return Returncodes.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Returncodes in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Returncodes.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Returncodes from the DB
export function destroy(req, res) {
  return Returncodes.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
