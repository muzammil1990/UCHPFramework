'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var claimjobtypesCtrlStub = {
  index: 'claimjobtypesCtrl.index',
  show: 'claimjobtypesCtrl.show',
  create: 'claimjobtypesCtrl.create',
  update: 'claimjobtypesCtrl.update',
  destroy: 'claimjobtypesCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var claimjobtypesIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './claimjobtypes.controller': claimjobtypesCtrlStub
});

describe('Claimjobtypes API Router:', function() {

  it('should return an express router instance', function() {
    claimjobtypesIndex.should.equal(routerStub);
  });

  describe('GET /api/claimjobtypes', function() {

    it('should route to claimjobtypes.controller.index', function() {
      routerStub.get
        .withArgs('/', 'claimjobtypesCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/claimjobtypes/:id', function() {

    it('should route to claimjobtypes.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'claimjobtypesCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/claimjobtypes', function() {

    it('should route to claimjobtypes.controller.create', function() {
      routerStub.post
        .withArgs('/', 'claimjobtypesCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/claimjobtypes/:id', function() {

    it('should route to claimjobtypes.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'claimjobtypesCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/claimjobtypes/:id', function() {

    it('should route to claimjobtypes.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'claimjobtypesCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/claimjobtypes/:id', function() {

    it('should route to claimjobtypes.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'claimjobtypesCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
