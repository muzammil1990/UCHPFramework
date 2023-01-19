/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/claim/claims/standardClaimSearchResult?claimSearchType=2&companyNo=1&typeOfClaim=4&appealStatus=1&userCompanyNo=1 ->  index
 * POST    /api/claim/claims/standardClaimSearchResult/              ->  create
 * GET     /api/claim/claims/standardClaimSearchResult/:id          ->  show
 * PUT     /api/claim/claims/standardClaimSearchResult/:id          ->  update
 * DELETE  /api/claim/claims/standardClaimSearchResult/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Standardclaimsearchresult from './standardclaimsearchresult.model';
import Claimsearchresult from '../claimsearchresult/claimsearchresult.model';

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

// Gets a list of Standardclaimsearchresults
export function index(req, res) {
  let reqQuery = req.query;
  let mongoQuery = {'$or': [], '$and': []};

  if (typeof reqQuery['refNo'] !== 'undefined') {
    if (reqQuery['refNo'].length) mongoQuery['refNo'] = {$eq: reqQuery['refNo']};
  }

  if (typeof reqQuery['repairingImporter'] !== 'undefined') {
    if (reqQuery['repairingImporter'].length) mongoQuery['repairingImporter'] = {$eq: reqQuery['repairingImporter']};
  }

  if (typeof reqQuery['repairingDealer'] !== 'undefined') {
    if (reqQuery['repairingDealer'].length) mongoQuery['repairingDealer'] = {$eq: reqQuery['repairingDealer']};
  }

  if (typeof reqQuery['companyNo'] !== 'undefined') {
    if (reqQuery['companyNo'].length) mongoQuery['headCompanyNo'] = {$eq: reqQuery['companyNo']};
  }

  if (typeof reqQuery['registrationDateFrom'] !== "undefined" || typeof reqQuery['registrationDateTo'] !== "undefined") mongoQuery['registrationDate'] = {};
  if (typeof reqQuery['registrationDateFrom'] !== "undefined") mongoQuery['registrationDate']['$gte'] = new Date(reqQuery['registrationDateFrom'].replace(/"/g,'')).toISOString();
  if (typeof reqQuery['registrationDateTo'] !== "undefined") mongoQuery['registrationDate']['$lt'] = new Date(reqQuery['registrationDateTo'].replace(/"/g,'')).toISOString();

  if (typeof reqQuery['chassisSeries'] !== 'undefined') {
    if (reqQuery['chassisSeries'].length) mongoQuery['chassisSeries'] = {$eq: reqQuery['chassisSeries']};
  }

  if (typeof reqQuery['chassisNo'] !== 'undefined') {
    if (reqQuery['chassisNo'].length) mongoQuery['chassisNo'] = {$eq: reqQuery['chassisNo']};
  }

  if (typeof reqQuery['repairOrderNo'] !== 'undefined') {
    if (reqQuery['repairOrderNo'].length) mongoQuery['repairOrderNo'] = {$eq: reqQuery['repairOrderNo']};
  }

  if ( !mongoQuery['$or'].length ) delete mongoQuery.$or;
  if ( !mongoQuery['$and'].length ) delete mongoQuery.$and;


  Claimsearchresult.find(mongoQuery).exec()
    .then( results => {
        if (!results) {
          return res.status(401).end();
        }
        var results_json = {
          metadata: {count: results.length},
          claimSearchResults: results
        };
        return res.status(200).json(results_json);
      }
    )
    .catch(handleError(res));
}

// Gets a single Standardclaimsearchresult from the DB
export function show(req, res) {
      return Standardclaimsearchresult.findOne().exec()
        .then( results => {
            if (!results) {
              return res.status(401).end();
            }
            return res.status(200).json({
              metadata: {count: results.length},
              claimSearchResults: results
            });
          }
        )
        .catch(handleError(res));
}

// Creates a new Standardclaimsearchresult in the DB
export function create(req, res) {
  return Standardclaimsearchresult.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Standardclaimsearchresult in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Standardclaimsearchresult.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Standardclaimsearchresult from the DB
export function destroy(req, res) {
  return Standardclaimsearchresult.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
