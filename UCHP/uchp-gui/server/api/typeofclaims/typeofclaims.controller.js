/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /uchp/api/api/typeOfClaims              ->  index
 * POST    /uchp/api/api/typeOfClaims              ->  create
 * GET     /uchp/api/api/typeOfClaims/:id          ->  show
 * PUT     /uchp/api/api/typeOfClaims/:id          ->  update
 * DELETE  /uchp/api/api/typeOfClaims/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Typeofclaims from './typeofclaims.model';

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

// Gets a list of Typeofclaimss
export function index(req, res) {
  return Typeofclaims.find().exec()
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Gets a single Typeofclaims from the DB
export function show(req, res) {
  return Typeofclaims.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Typeofclaims in the DB
export function create(req, res) {
  return Typeofclaims.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Typeofclaims in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Typeofclaims.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Typeofclaims from the DB
export function destroy(req, res) {
  return Typeofclaims.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
