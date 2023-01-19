'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var contenttypeCtrlStub = {
  index: 'contenttypeCtrl.index',
  show: 'contenttypeCtrl.show',
  create: 'contenttypeCtrl.create',
  update: 'contenttypeCtrl.update',
  destroy: 'contenttypeCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var contenttypeIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './contenttype.controller': contenttypeCtrlStub
});

describe('Contenttype API Router:', function() {

  it('should return an express router instance', function() {
    contenttypeIndex.should.equal(routerStub);
  });

  describe('GET /api/crediting/creditdocuments/contenttype', function() {

    it('should route to contenttype.controller.index', function() {
      routerStub.get
        .withArgs('/', 'contenttypeCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/crediting/creditdocuments/contenttype/:id', function() {

    it('should route to contenttype.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'contenttypeCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/crediting/creditdocuments/contenttype', function() {

    it('should route to contenttype.controller.create', function() {
      routerStub.post
        .withArgs('/', 'contenttypeCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/crediting/creditdocuments/contenttype/:id', function() {

    it('should route to contenttype.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'contenttypeCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/crediting/creditdocuments/contenttype/:id', function() {

    it('should route to contenttype.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'contenttypeCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/crediting/creditdocuments/contenttype/:id', function() {

    it('should route to contenttype.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'contenttypeCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
