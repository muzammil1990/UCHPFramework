'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var searchvehiclecampaignlistCtrlStub = {
  index: 'searchvehiclecampaignlistCtrl.index',
  show: 'searchvehiclecampaignlistCtrl.show',
  create: 'searchvehiclecampaignlistCtrl.create',
  update: 'searchvehiclecampaignlistCtrl.update',
  destroy: 'searchvehiclecampaignlistCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var searchvehiclecampaignlistIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './searchvehiclecampaignlist.controller': searchvehiclecampaignlistCtrlStub
});

describe('Searchvehiclecampaignlist API Router:', function() {

  it('should return an express router instance', function() {
    searchvehiclecampaignlistIndex.should.equal(routerStub);
  });

  describe('GET /api/claim/vehicle/searchVehicleCampaignList', function() {

    it('should route to searchvehiclecampaignlist.controller.index', function() {
      routerStub.get
        .withArgs('/', 'searchvehiclecampaignlistCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/claim/vehicle/searchVehicleCampaignList/:id', function() {

    it('should route to searchvehiclecampaignlist.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'searchvehiclecampaignlistCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/claim/vehicle/searchVehicleCampaignList', function() {

    it('should route to searchvehiclecampaignlist.controller.create', function() {
      routerStub.post
        .withArgs('/', 'searchvehiclecampaignlistCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/claim/vehicle/searchVehicleCampaignList/:id', function() {

    it('should route to searchvehiclecampaignlist.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'searchvehiclecampaignlistCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/claim/vehicle/searchVehicleCampaignList/:id', function() {

    it('should route to searchvehiclecampaignlist.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'searchvehiclecampaignlistCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/claim/vehicle/searchVehicleCampaignList/:id', function() {

    it('should route to searchvehiclecampaignlist.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'searchvehiclecampaignlistCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
