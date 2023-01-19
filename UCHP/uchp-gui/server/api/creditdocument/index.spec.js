'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var creditdocumentCtrlStub = {
  index: 'creditdocumentCtrl.index',
  show: 'creditdocumentCtrl.show',
  create: 'creditdocumentCtrl.create',
  update: 'creditdocumentCtrl.update',
  destroy: 'creditdocumentCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var creditdocumentIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './creditdocument.controller': creditdocumentCtrlStub
});

describe('Creditdocument API Router:', function() {

  it('should return an express router instance', function() {
    creditdocumentIndex.should.equal(routerStub);
  });

  describe('GET /api/crediting/creditdocuments/creditingdocuments', function() {

    it('should route to creditdocument.controller.index', function() {
      routerStub.get
        .withArgs('/', 'creditdocumentCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/crediting/creditdocuments/creditingdocuments/:id', function() {

    it('should route to creditdocument.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'creditdocumentCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/crediting/creditdocuments/creditingdocuments', function() {

    it('should route to creditdocument.controller.create', function() {
      routerStub.post
        .withArgs('/', 'creditdocumentCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/crediting/creditdocuments/creditingdocuments/:id', function() {

    it('should route to creditdocument.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'creditdocumentCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/crediting/creditdocuments/creditingdocuments/:id', function() {

    it('should route to creditdocument.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'creditdocumentCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/crediting/creditdocuments/creditingdocuments/:id', function() {

    it('should route to creditdocument.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'creditdocumentCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
