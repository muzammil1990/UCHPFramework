/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/crediting/creditdocuments/contenttype              ->  index
 * POST    /api/crediting/creditdocuments/contenttype              ->  create
 * GET     /api/crediting/creditdocuments/contenttype/:id          ->  show
 * PUT     /api/crediting/creditdocuments/contenttype/:id          ->  update
 * DELETE  /api/crediting/creditdocuments/contenttype/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Contenttype from './contenttype.model';

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

// Gets a list of Contenttypes
export function index(req, res) {
  return Contenttype.find(req.params.id).exec()
    .then(function (contenttypedocs) {
      return res.status(200).json({metadata: {count: contenttypedocs.length}, translations: contenttypedocs});
    })
    .catch(handleError(res));
}

// Gets a single Contenttype from the DB
export function show(req, res) {
  return Contenttype.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Contenttype in the DB
export function create(req, res) {
  return Contenttype.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Contenttype in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Contenttype.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Contenttype from the DB
export function destroy(req, res) {
  return Contenttype.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
