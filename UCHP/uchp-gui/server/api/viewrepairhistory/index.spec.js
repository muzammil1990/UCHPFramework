/*
'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var viewrepairhistoryCtrlStub = {
  index: 'viewrepairhistoryCtrl.index',
  show: 'viewrepairhistoryCtrl.show',
  create: 'viewrepairhistoryCtrl.create',
  update: 'viewrepairhistoryCtrl.update',
  destroy: 'viewrepairhistoryCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var viewrepairhistoryIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './viewrepairhistory.controller': viewrepairhistoryCtrlStub
});

describe('Viewrepairhistory API Router:', function() {

  it('should return an express router instance', function() {
    viewrepairhistoryIndex.should.equal(routerStub);
  });

  describe('GET /api/claim/vehicle/repairhistory', function() {

    it('should route to viewrepairhistory.controller.index', function() {
      routerStub.get
        .withArgs('/', 'viewrepairhistoryCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/claim/vehicle/repairhistory/:id', function() {

    it('should route to viewrepairhistory.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'viewrepairhistoryCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/claim/vehicle/repairhistory', function() {

    it('should route to viewrepairhistory.controller.create', function() {
      routerStub.post
        .withArgs('/', 'viewrepairhistoryCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/claim/vehicle/repairhistory/:id', function() {

    it('should route to viewrepairhistory.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'viewrepairhistoryCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/claim/vehicle/repairhistory/:id', function() {

    it('should route to viewrepairhistory.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'viewrepairhistoryCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/claim/vehicle/repairhistory/:id', function() {

    it('should route to viewrepairhistory.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'viewrepairhistoryCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
*/
