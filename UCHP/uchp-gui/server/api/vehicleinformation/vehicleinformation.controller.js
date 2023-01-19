/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/claim/vehicle/coverages              ->  index
 * POST    /api/claim/vehicle/coverages              ->  create
 * GET     /api/claim/vehicle/coverages/:id          ->  show
 * PUT     /api/claim/vehicle/coverages/:id          ->  update
 * DELETE  /api/claim/vehicle/coverages/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Vehicleinformation from './vehicleinformation.model';
import Viewcoverage from '../viewcoverage/viewcoverage.model';
import Repairhistory from '../viewrepairhistory/viewrepairhistory.model';

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

// Gets a list of Vehicleinformations
export function index(req, res) {
  var reqQuery = req.query;
  var mongoQuery = {};
  if (typeof reqQuery['userCompanyNo'] !== 'undefined') mongoQuery['companyNo'] = {$eq: reqQuery['userCompanyNo']};
  if (typeof reqQuery['regNo'] !== 'undefined') mongoQuery['registrationNo'] = {$eq: reqQuery['regNo']};
  if (typeof reqQuery['chassisNo'] !== 'undefined') mongoQuery['chassisNo'] = {$eq: reqQuery['chassisNo']};
  if (typeof reqQuery['chassisSeries'] !== 'undefined') mongoQuery['chassisSeries'] = {$eq: reqQuery['chassisSeries']};
  if (typeof reqQuery['vin'] !== 'undefined') mongoQuery['vin'] = {$eq: reqQuery['vin']};
  if (typeof reqQuery['make'] !== 'undefined') mongoQuery['brand'] = {$eq: reqQuery['make']};
  if (typeof reqQuery['marketingType'] !== 'undefined') mongoQuery['marketingType'] = {$eq: reqQuery['marketingType']};

 /* return Vehicleinformation.find(mongoQuery).exec()
    .then(function (docs) {
      return res.status(200).json({metadata: {count: docs.length}, vehicleInformations: docs});
    })
    .catch(handleError(res));
*/
  Repairhistory.find().exec()
    .then(RepairHistoryList => {
      Viewcoverage.find().exec()
        .then(CoverageList => {
          return Vehicleinformation.find(mongoQuery).exec()
            .then(docs => {
              if (!docs) {
                return res.status(401).end();
              }
              if (req.baseUrl.endsWith('/claim/vehicle/coverages')) {
                docs[0]['coverageList'] = CoverageList;
              } else if (req.baseUrl.endsWith('/claim/vehicle/repairhistory')){
                docs[0]['repairHistoryList'] = RepairHistoryList;
              }

              return res.status(200).json({metadata: {count: docs.length}, vehicleInformations: docs});
              //  res.json({metadata: {count: docs.length}, Vehicleinformation: docs});
            });
        })
    })
    .catch(handleError(res));

}

// Gets a single Vehicleinformation from the DB
export function show(req, res) {
  return Vehicleinformation.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Vehicleinformation in the DB
export function create(req, res) {
  return Vehicleinformation.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Vehicleinformation in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Vehicleinformation.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Vehicleinformation from the DB
export function destroy(req, res) {
  return Vehicleinformation.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
