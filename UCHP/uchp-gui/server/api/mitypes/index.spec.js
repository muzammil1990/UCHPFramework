'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var mitypesCtrlStub = {
  index: 'mitypesCtrl.index',
  show: 'mitypesCtrl.show',
  create: 'mitypesCtrl.create',
  update: 'mitypesCtrl.update',
  destroy: 'mitypesCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var mitypesIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './mitypes.controller': mitypesCtrlStub
});

describe('Mitypes API Router:', function() {

  it('should return an express router instance', function() {
    mitypesIndex.should.equal(routerStub);
  });

  describe('GET /api/mitypes', function() {

    it('should route to mitypes.controller.index', function() {
      routerStub.get
        .withArgs('/', 'mitypesCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/mitypes/:id', function() {

    it('should route to mitypes.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'mitypesCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/mitypes', function() {

    it('should route to mitypes.controller.create', function() {
      routerStub.post
        .withArgs('/', 'mitypesCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/mitypes/:id', function() {

    it('should route to mitypes.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'mitypesCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/mitypes/:id', function() {

    it('should route to mitypes.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'mitypesCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/mitypes/:id', function() {

    it('should route to mitypes.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'mitypesCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
