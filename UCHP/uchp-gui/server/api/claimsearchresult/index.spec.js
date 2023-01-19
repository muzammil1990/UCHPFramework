'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var claimsearchresultCtrlStub = {
  index: 'claimsearchresultCtrl.index',
  show: 'claimsearchresultCtrl.show',
  create: 'claimsearchresultCtrl.create',
  update: 'claimsearchresultCtrl.update',
  destroy: 'claimsearchresultCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var claimsearchresultIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './claimsearchresult.controller': claimsearchresultCtrlStub
});

describe('Claimsearchresult API Router:', function() {

  it('should return an express router instance', function() {
    claimsearchresultIndex.should.equal(routerStub);
  });

  describe('GET /api/claimsearchresult', function() {

    it('should route to claimsearchresult.controller.index', function() {
      routerStub.get
        .withArgs('/', 'claimsearchresultCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/claimsearchresult/:id', function() {

    it('should route to claimsearchresult.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'claimsearchresultCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/claimsearchresult', function() {

    it('should route to claimsearchresult.controller.create', function() {
      routerStub.post
        .withArgs('/', 'claimsearchresultCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/claimsearchresult/:id', function() {

    it('should route to claimsearchresult.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'claimsearchresultCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/claimsearchresult/:id', function() {

    it('should route to claimsearchresult.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'claimsearchresultCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/claimsearchresult/:id', function() {

    it('should route to claimsearchresult.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'claimsearchresultCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
