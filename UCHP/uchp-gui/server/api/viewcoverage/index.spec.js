'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var viewcoverageCtrlStub = {
  index: 'viewcoverageCtrl.index',
  show: 'viewcoverageCtrl.show',
  create: 'viewcoverageCtrl.create',
  update: 'viewcoverageCtrl.update',
  destroy: 'viewcoverageCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var viewcoverageIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './viewcoverage.controller': viewcoverageCtrlStub
});

describe('Viewcoverage API Router:', function() {

  it('should return an express router instance', function() {
    viewcoverageIndex.should.equal(routerStub);
  });

  describe('GET /api/claim/vehicle/coverages', function() {

    it('should route to viewcoverage.controller.index', function() {
      routerStub.get
        .withArgs('/', 'viewcoverageCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/claim/vehicle/coverages/:id', function() {

    it('should route to viewcoverage.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'viewcoverageCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/claim/vehicle/coverages', function() {

    it('should route to viewcoverage.controller.create', function() {
      routerStub.post
        .withArgs('/', 'viewcoverageCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/claim/vehicle/coverages/:id', function() {

    it('should route to viewcoverage.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'viewcoverageCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/claim/vehicle/coverages/:id', function() {

    it('should route to viewcoverage.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'viewcoverageCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/claim/vehicle/coverages/:id', function() {

    it('should route to viewcoverage.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'viewcoverageCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
