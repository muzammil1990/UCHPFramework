'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var appealstatusesCtrlStub = {
  index: 'appealstatusesCtrl.index',
  show: 'appealstatusesCtrl.show',
  create: 'appealstatusesCtrl.create',
  update: 'appealstatusesCtrl.update',
  destroy: 'appealstatusesCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var appealstatusesIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './appealstatuses.controller': appealstatusesCtrlStub
});

describe('Appealstatuses API Router:', function() {

  it('should return an express router instance', function() {
    appealstatusesIndex.should.equal(routerStub);
  });

  describe('GET /api/apppealstatuses', function() {

    it('should route to appealstatuses.controller.index', function() {
      routerStub.get
        .withArgs('/', 'appealstatusesCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/apppealstatuses/:id', function() {

    it('should route to appealstatuses.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'appealstatusesCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/apppealstatuses', function() {

    it('should route to appealstatuses.controller.create', function() {
      routerStub.post
        .withArgs('/', 'appealstatusesCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/apppealstatuses/:id', function() {

    it('should route to appealstatuses.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'appealstatusesCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/apppealstatuses/:id', function() {

    it('should route to appealstatuses.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'appealstatusesCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/apppealstatuses/:id', function() {

    it('should route to appealstatuses.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'appealstatusesCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
