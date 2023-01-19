/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /uchp/api/api/claim/advancedsearchclaims/advancedSearchCriteria              ->  index
 * POST    /uchp/api/api/claim/advancedsearchclaims/advancedSearchCriteria              ->  create
 * GET     /uchp/api/api/claim/advancedsearchclaims/advancedSearchCriteria/:id          ->  show
 * PUT     /uchp/api/api/claim/advancedsearchclaims/advancedSearchCriteria/:id          ->  update
 * DELETE  /uchp/api/api/claim/advancedsearchclaims/advancedSearchCriteria/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Advancedsearchcriteria from './advancedsearchcriteria.model';
import Claimsearchtypes from '../claimsearchtypes/claimsearchtypes.model';
import Claimjobtypes from '../claimjobtypes/claimjobtypes.model';
import TmaStatuses from '../tmaStatuses/tmaStatuses.model';
import Claimjobstatuses from '../claimjobstatuses/claimjobstatuses.model';



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

// Gets a list of Advancedsearchcriterias
export function index(req, res) {
  Claimsearchtypes.find().exec()
    .then(ClaimSearchTypesList => {
      Claimjobtypes.find().exec()
        .then(ClaimJobTypesList => {
          TmaStatuses.find().exec()
            .then(TmaStatusesList => {
              Claimjobstatuses.find().exec()
                .then(ClaimjobstatusesList => {

                  return Advancedsearchcriteria.find().exec()
                    .then(advancedsearchcriteriadocs => {
                      if (!advancedsearchcriteriadocs) {
                        return res.status(401).end();
                      }
                      advancedsearchcriteriadocs[0]['claimJobStatuses'] = ClaimjobstatusesList;
                      advancedsearchcriteriadocs[0]['claimJobTypes'] = ClaimJobTypesList;
                      advancedsearchcriteriadocs[0]['claimSearchTypes'] = ClaimSearchTypesList;
                      advancedsearchcriteriadocs[0]['tmaStatuses'] = TmaStatusesList;

                      return res.status(200).json({
                        metadata: {count: advancedsearchcriteriadocs.length}, translations: advancedsearchcriteriadocs,
                        claimJobStatuses: ClaimjobstatusesList,
                        claimJobTypes: ClaimJobTypesList,
                        claimSearchTypes: ClaimSearchTypesList,
                        tmaStatuses: TmaStatusesList
                      });
                    });
                })
            })
        })
    })
    .catch(handleError(res));
}

// Gets a single Advancedsearchcriteria from the DB
export function show(req, res) {
  return Advancedsearchcriteria.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(respondWithResult(res))
    .catch(handleError(res));

}

// Creates a new Advancedsearchcriteria in the DB
export function create(req, res) {
  return Advancedsearchcriteria.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Advancedsearchcriteria in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Advancedsearchcriteria.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Advancedsearchcriteria from the DB
export function destroy(req, res) {
  return Advancedsearchcriteria.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
