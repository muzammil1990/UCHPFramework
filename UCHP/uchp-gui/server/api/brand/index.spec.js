'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var brandCtrlStub = {
  index: 'brandCtrl.index',
  show: 'brandCtrl.show',
  create: 'brandCtrl.create',
  update: 'brandCtrl.update',
  destroy: 'brandCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var brandIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './brand.controller': brandCtrlStub
});

describe('Brand API Router:', function() {

  it('should return an express router instance', function() {
    brandIndex.should.equal(routerStub);
  });

  describe('GET /api/crediting/creditdocuments/brandlist', function() {

    it('should route to brand.controller.index', function() {
      routerStub.get
        .withArgs('/', 'brandCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/crediting/creditdocuments/brandlist/:id', function() {

    it('should route to brand.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'brandCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/crediting/creditdocuments/brandlist', function() {

    it('should route to brand.controller.create', function() {
      routerStub.post
        .withArgs('/', 'brandCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/crediting/creditdocuments/brandlist/:id', function() {

    it('should route to brand.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'brandCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/crediting/creditdocuments/brandlist/:id', function() {

    it('should route to brand.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'brandCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/crediting/creditdocuments/brandlist/:id', function() {

    it('should route to brand.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'brandCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
