/**
 * Using Rails-like standard naming convention for endpoints.
 * GET     /api/claim/vehicle/searchVehicleCampaignList              ->  index
 * POST    /api/claim/vehicle/searchVehicleCampaignList              ->  create
 * GET     /api/claim/vehicle/searchVehicleCampaignList/:id          ->  show
 * PUT     /api/claim/vehicle/searchVehicleCampaignList/:id          ->  update
 * DELETE  /api/claim/vehicle/searchVehicleCampaignList/:id          ->  destroy
 */

'use strict';

import _ from 'lodash';
import Searchvehiclecampaignlist from './searchvehiclecampaignlist.model';
import Vehicleinformation from '../vehicleinformation/vehicleinformation.model';
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

// Gets a list of Searchvehiclecampaignlists
export function index(req, res) {

  Repairhistory.find().exec()
    .then(RepairHistoryList => {
      Viewcoverage.find().exec()
        .then(CoverageList => {
          Searchvehiclecampaignlist.find().exec()
            .then(searchCampaignList => {
          return Vehicleinformation.find().exec()
            .then(docs => {
              if (!docs) {
                return res.status(401).end();
              }
              if (req.baseUrl.endsWith('/claim/vehicle/coverages')) {
                docs[0]['coverageList'] = CoverageList;
              } else if (req.baseUrl.endsWith('/claim/vehicle/repairhistory')){
                docs[0]['repairHistoryList'] = RepairHistoryList;
              } else if (req.baseUrl.endsWith('/claim/vehicle/searchVehicleCampaignList')){
                docs[0]['campaignList'] = searchCampaignList;
              }
              return res.status(200).json({metadata: {count: docs.length}, vehicleInformations: docs});
            });
        })
        })
    })
    .catch(handleError(res));

/*  return Searchvehiclecampaignlist.find(req.params).exec()
    .then(function (docs) {
      return res.status(200).json({metadata: {count: docs.length}, vehicleInformations: docs});
    })
    .catch(handleError(res));*/

}

// Gets a single Searchvehiclecampaignlist from the DB
export function show(req, res) {
  return Searchvehiclecampaignlist.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Creates a new Searchvehiclecampaignlist in the DB
export function create(req, res) {
  return Searchvehiclecampaignlist.create(req.body)
    .then(respondWithResult(res, 201))
    .catch(handleError(res));
}

// Updates an existing Searchvehiclecampaignlist in the DB
export function update(req, res) {
  if (req.body._id) {
    delete req.body._id;
  }
  return Searchvehiclecampaignlist.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(saveUpdates(req.body))
    .then(respondWithResult(res))
    .catch(handleError(res));
}

// Deletes a Searchvehiclecampaignlist from the DB
export function destroy(req, res) {
  return Searchvehiclecampaignlist.findById(req.params.id).exec()
    .then(handleEntityNotFound(res))
    .then(removeEntity(res))
    .catch(handleError(res));
}
