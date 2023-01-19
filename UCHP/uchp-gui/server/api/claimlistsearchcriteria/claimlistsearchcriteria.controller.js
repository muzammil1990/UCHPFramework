/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/claimlistsearchcriteria              ->  index
 * POST    /api/claimlistsearchcriteria              ->  create
 * GET     /api/claimlistsearchcriteria/:id          ->  show
 * PUT     /api/claimlistsearchcriteria/:id          ->  update
 * DELETE  /api/claimlistsearchcriteria/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Claimlistsearchcriteria from './claimlistsearchcriteria.model';
import Claimsearchtypes from '../claimsearchtypes/claimsearchtypes.model';
import Typeofclaims from '../typeofclaims/typeofclaims.model';
import Mitypes from '../mitypes/mitypes.model';
import Miprintstatuses from '../miprintstatuses/miprintstatuses.model';
import Appealstatuses from '../appealstatuses/appealstatuses.model';


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

// Gets a list of Claimlistsearchcriterias
export function index(req, res) {

  Claimsearchtypes.find().exec()
    .then(ClaimSearchTypesList => {
      Typeofclaims.find().exec()
        .then(TypeofclaimsList => {
          Mitypes.find().exec()
            .then(MitypesList => {
              Miprintstatuses.find().exec()
                .then(MiprintstatusesList => {
                  Appealstatuses.find().exec()
                    .then(AppealstatusesList => {
                      return Claimlistsearchcriteria.find().exec()
                        .then(claimsearchcriteriadocs => {
                          if (!claimsearchcriteriadocs) {
                            return res.status(401).end();
                          }
                          claimsearchcriteriadocs[0]['claimSearchTypes'] = ClaimSearchTypesList;
                          claimsearchcriteriadocs[0]['typeOfClaims'] = TypeofclaimsList;
                          claimsearchcriteriadocs[0]['miTypes'] = MitypesList;
                          claimsearchcriteriadocs[0]['miPrintStatuses'] = MiprintstatusesList;
                          claimsearchcriteriadocs[0]['appealStatuses'] = AppealstatusesList;
                          return res.status(200).json({
                            metadata: {count: claimsearchcriteriadocs.length},
                            translations: claimsearchcriteriadocs,
                            claimSearchTypes: ClaimSearchTypesList,
                            typeOfClaims: TypeofclaimsList,
                            miTypes: MitypesList,
                            miPrintStatuses: MiprintstatusesList,
                            appealStatuses: AppealstatusesList
                          });

                        });
                    })
                })
            })
        })
    })
    .catch(handleError(res));

/*  return Claimlistsearchcriteria.find().exec()
    .then(function (claimsearchcriteriadocs) {
      return res.status(200).json({metadata: {count: claimsearchcriteriadocs.length}, translations: claimsearchcriteriadocs});
      console.log(claimsearchcriteriadocs);
    })

    .catch(handleError(res));*/

/*  return Claimlistsearchcriteria.find().exec()
    .then(respondWithResult(res))
    .catch(handleError(res));*/
}

// Gets a single Claimlistsearchcriteria from the DB
export function show(req, res) {
  return Claimlistsearchcriteria.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Claimlistsearchcriteria in the DB
export function create(req, res) {
  return Claimlistsearchcriteria.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Claimlistsearchcriteria in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Claimlistsearchcriteria.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Claimlistsearchcriteria from the DB
export function destroy(req, res) {
  return Claimlistsearchcriteria.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
