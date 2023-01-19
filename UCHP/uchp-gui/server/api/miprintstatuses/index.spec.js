'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var miprintstatusesCtrlStub = {
  index: 'miprintstatusesCtrl.index',
  show: 'miprintstatusesCtrl.show',
  create: 'miprintstatusesCtrl.create',
  update: 'miprintstatusesCtrl.update',
  destroy: 'miprintstatusesCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var miprintstatusesIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './miprintstatuses.controller': miprintstatusesCtrlStub
});

describe('Miprintstatuses API Router:', function() {

  it('should return an express router instance', function() {
    miprintstatusesIndex.should.equal(routerStub);
  });

  describe('GET /api/miprintstatuses', function() {

    it('should route to miprintstatuses.controller.index', function() {
      routerStub.get
        .withArgs('/', 'miprintstatusesCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/miprintstatuses/:id', function() {

    it('should route to miprintstatuses.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'miprintstatusesCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/miprintstatuses', function() {

    it('should route to miprintstatuses.controller.create', function() {
      routerStub.post
        .withArgs('/', 'miprintstatusesCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/miprintstatuses/:id', function() {

    it('should route to miprintstatuses.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'miprintstatusesCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/miprintstatuses/:id', function() {

    it('should route to miprintstatuses.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'miprintstatusesCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/miprintstatuses/:id', function() {

    it('should route to miprintstatuses.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'miprintstatusesCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
