/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/claim/manageclaim/preparenewclaimjob/ ->  index
 * POST    /api/claim/manageclaim/preparenewclaimjob              ->  create
 * GET     /api/claim/manageclaim/preparenewclaimjob/:id          ->  show
 * PUT     /api/claim/manageclaim/preparenewclaimjob/:id          ->  update
 * DELETE  /api/claim/manageclaim/preparenewclaimjob/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Preparenewclaim from './preparenewclaimjob.model';

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
  console.log('entity not found preparencewclaim');
  return function(entity) {
    if (!entity) {
      //res.status(404).end();
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

// Gets a list of Preparenewclaims
export function index(req, res) {
  return Preparenewclaim.findOne().exec()
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Gets a single Preparenewclaim from the DB
export function show(req, res) {
  return Preparenewclaim.findOne().exec()
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Preparenewclaim in the DB
export function create(req, res) {
  console.log('creating preparencewclaim');
  return Preparenewclaim.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Preparenewclaim in the DB
export function update(req, res) {
  console.log('updating preparencewclaim');
  if (req.body._id) {
    delete req.body._id;
  }
  return Preparenewclaim.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Preparenewclaim from the DB
export function destroy(req, res) {
  return Preparenewclaim.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
