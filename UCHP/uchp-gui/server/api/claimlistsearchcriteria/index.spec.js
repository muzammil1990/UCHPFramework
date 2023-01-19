'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var claimlistsearchcriteriaCtrlStub = {
  index: 'claimlistsearchcriteriaCtrl.index',
  show: 'claimlistsearchcriteriaCtrl.show',
  create: 'claimlistsearchcriteriaCtrl.create',
  update: 'claimlistsearchcriteriaCtrl.update',
  destroy: 'claimlistsearchcriteriaCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var claimlistsearchcriteriaIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './claimlistsearchcriteria.controller': claimlistsearchcriteriaCtrlStub
});

describe('Claimlistsearchcriteria API Router:', function() {

  it('should return an express router instance', function() {
    claimlistsearchcriteriaIndex.should.equal(routerStub);
  });

  describe('GET /api/claimlistsearchcriterias', function() {

    it('should route to claimlistsearchcriteria.controller.index', function() {
      routerStub.get
        .withArgs('/', 'claimlistsearchcriteriaCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/claimlistsearchcriterias/:id', function() {

    it('should route to claimlistsearchcriteria.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'claimlistsearchcriteriaCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/claimlistsearchcriterias', function() {

    it('should route to claimlistsearchcriteria.controller.create', function() {
      routerStub.post
        .withArgs('/', 'claimlistsearchcriteriaCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/claimlistsearchcriterias/:id', function() {

    it('should route to claimlistsearchcriteria.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'claimlistsearchcriteriaCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/claimlistsearchcriterias/:id', function() {

    it('should route to claimlistsearchcriteria.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'claimlistsearchcriteriaCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/claimlistsearchcriterias/:id', function() {

    it('should route to claimlistsearchcriteria.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'claimlistsearchcriteriaCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
