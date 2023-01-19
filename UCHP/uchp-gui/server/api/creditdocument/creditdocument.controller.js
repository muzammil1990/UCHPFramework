/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/crediting/creditdocuments/creditingdocuments              ->  index
 * POST    /api/crediting/creditdocuments/creditingdocuments              ->  create
 * GET     /api/crediting/creditdocuments/creditingdocuments/:id          ->  show
 * PUT     /api/crediting/creditdocuments/creditingdocuments/:id          ->  update
 * DELETE  /api/crediting/creditdocuments/creditingdocuments/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Creditdocument from './creditdocument.model';

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

// Gets a list of Creditdocuments
export function index(req, res) {
  let reqQuery = req.query;
  let mongoQuery = {'$or': [], '$and': []};
  if (reqQuery['mimeType'] === 'application/pdf') mongoQuery['mimeType'] = {$eq: 'PDF'};
  if (reqQuery['mimeType'] === 'application/vnd.ms-excel') mongoQuery['mimeType'] = {$eq: 'Excel'};
  if (typeof reqQuery['importerNo'] !== 'undefined') {
    if (reqQuery['importerNo'].length) mongoQuery['importerNo'] = {$eq: reqQuery['importerNo']};
  }
  if (typeof reqQuery['dealerNo'] !== 'undefined') {
    if (reqQuery['dealerNo'].length) mongoQuery['dealerNo'] = {$eq: reqQuery['dealerNo']};
  }
  if (typeof reqQuery['creditDocNo'] !== 'undefined') {
    if (reqQuery['creditDocNo'].length) mongoQuery['creditDocumentId'] = {$eq: reqQuery['creditDocNo']};
  }
  if (typeof reqQuery['make'] !== 'undefined') {
    if (reqQuery['make'] !== "-1") mongoQuery['make'] = {$eq: reqQuery['make']};
  }
  if (typeof reqQuery['creditingDateFrom'] !== "undefined" || typeof reqQuery['creditingDateTo'] !== "undefined") mongoQuery['creditingDate'] = {};
  if (typeof reqQuery['creditingDateFrom'] !== "undefined") mongoQuery['creditingDate']['$gte'] = new Date(reqQuery['creditingDateFrom'].replace(/"/g,'')).toISOString();
  if (typeof reqQuery['creditingDateTo'] !== "undefined") mongoQuery['creditingDate']['$lt'] = new Date(reqQuery['creditingDateTo'].replace(/"/g,'')).toISOString();

  if (typeof reqQuery['repairingDealer'] !== 'undefined') {
    let repairingDealerNameStatement = {
        "dealerName": {$regex: ".*" + reqQuery['repairingDealer'] + ".*" }
    };
    let repairingDealerNoStatement = {
        "dealerNo": reqQuery['repairingDealer']
    };
    ( !mongoQuery['$or'].length ) ? mongoQuery['$or'].push(repairingDealerNameStatement, repairingDealerNoStatement) : mongoQuery['$and'].push(repairingDealerNameStatement, repairingDealerNoStatement);
  }

  if (typeof reqQuery['repairingImporter'] !== 'undefined') {
    let repairingImporterNameStatement = {
      "importerName": {$regex: ".*" + reqQuery['repairingImporter'] + ".*"}
    };
    let repairingImporterNoStatement =  {
      "importerNo": reqQuery['repairingImporter']
    };
    ( !mongoQuery['$or'].length ) ? mongoQuery['$or'].push(repairingImporterNameStatement, repairingImporterNoStatement) : mongoQuery['$and'].push(repairingImporterNameStatement, repairingImporterNoStatement);
  }

  if ( !mongoQuery['$or'].length ) delete mongoQuery.$or;
  if ( !mongoQuery['$and'].length ) delete mongoQuery.$and;
  
  return Creditdocument.find(mongoQuery).exec()
    .then(function (docs) {
      return res.status(200).json({metadata: {count: docs.length}, creditingDocuments: docs});
    })
    .catch(handleError(res));
}

//make the call to the api with the ID to validate


// Gets a single Creditdocument from the DB
export function show(req, res) {
 /* Creditdocument.findOne({id: req.params.id}).exec(function (err, creditdocument) {
    if(err) { return handleError(res, err); }
    if(!creditdocument) { return res.status(404).send('Not Found'); }
    return res.json(creditdocument);
  });*/
}

// Creates a new Creditdocument in the DB
export function create(req, res) {
  return Creditdocument.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Creditdocument in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Creditdocument.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Creditdocument from the DB
export function destroy(req, res) {
  return Creditdocument.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
