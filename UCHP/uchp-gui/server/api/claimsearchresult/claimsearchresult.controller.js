/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/claimsearchresult              ->  index
 * POST    /api/claimsearchresult              ->  create
 * GET     /api/claimsearchresult/:id          ->  show
 * PUT     /api/claimsearchresult/:id          ->  update
 * DELETE  /api/claimsearchresult/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Claimsearchresult from './claimsearchresult.model';

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

// Gets a list of Claimsearchresults
export function index(req, res) {

  return Claimsearchresult.find(req.params.id).exec()
    .then(function (contenttypedocs) {
      return res.status(200).json({metadata: {count: docs.length}, translations: docs});
    })
    .catch(handleError(res));
/*  return Claimsearchresult.find().exec()
    .then(respondWithResult(res))
    .catch(handleError(res));*/
}

// Gets a single Claimsearchresult from the DB
export function show(req, res) {
  return Claimsearchresult.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Claimsearchresult in the DB
export function create(req, res) {
  return Claimsearchresult.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Claimsearchresult in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Claimsearchresult.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Claimsearchresult from the DB
export function destroy(req, res) {
  return Claimsearchresult.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
