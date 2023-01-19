'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var vehicleinformationCtrlStub = {
  index: 'vehicleinformationCtrl.index',
  show: 'vehicleinformationCtrl.show',
  create: 'vehicleinformationCtrl.create',
  update: 'vehicleinformationCtrl.update',
  destroy: 'vehicleinformationCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var vehicleinformationIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './vehicleinformation.controller': vehicleinformationCtrlStub
});

describe('Vehicleinformation API Router:', function() {

  it('should return an express router instance', function() {
    vehicleinformationIndex.should.equal(routerStub);
  });

  describe('GET /api/claim/vehicle/coverages', function() {

    it('should route to vehicleinformation.controller.index', function() {
      routerStub.get
        .withArgs('/', 'vehicleinformationCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/claim/vehicle/coverages/:id', function() {

    it('should route to vehicleinformation.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'vehicleinformationCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/claim/vehicle/coverages', function() {

    it('should route to vehicleinformation.controller.create', function() {
      routerStub.post
        .withArgs('/', 'vehicleinformationCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/claim/vehicle/coverages/:id', function() {

    it('should route to vehicleinformation.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'vehicleinformationCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/claim/vehicle/coverages/:id', function() {

    it('should route to vehicleinformation.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'vehicleinformationCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/claim/vehicle/coverages/:id', function() {

    it('should route to vehicleinformation.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'vehicleinformationCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
