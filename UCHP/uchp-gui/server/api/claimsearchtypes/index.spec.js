'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var claimsearchtypesCtrlStub = {
  index: 'claimsearchtypesCtrl.index',
  show: 'claimsearchtypesCtrl.show',
  create: 'claimsearchtypesCtrl.create',
  update: 'claimsearchtypesCtrl.update',
  destroy: 'claimsearchtypesCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var claimsearchtypesIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './claimsearchtypes.controller': claimsearchtypesCtrlStub
});

describe('Claimsearchtypes API Router:', function() {

  it('should return an express router instance', function() {
    claimsearchtypesIndex.should.equal(routerStub);
  });

  describe('GET /api/claimsearchtypes', function() {

    it('should route to claimsearchtypes.controller.index', function() {
      routerStub.get
        .withArgs('/', 'claimsearchtypesCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/claimsearchtypes/:id', function() {

    it('should route to claimsearchtypes.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'claimsearchtypesCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/claimsearchtypes', function() {

    it('should route to claimsearchtypes.controller.create', function() {
      routerStub.post
        .withArgs('/', 'claimsearchtypesCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/claimsearchtypes/:id', function() {

    it('should route to claimsearchtypes.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'claimsearchtypesCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/claimsearchtypes/:id', function() {

    it('should route to claimsearchtypes.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'claimsearchtypesCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/claimsearchtypes/:id', function() {

    it('should route to claimsearchtypes.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'claimsearchtypesCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
