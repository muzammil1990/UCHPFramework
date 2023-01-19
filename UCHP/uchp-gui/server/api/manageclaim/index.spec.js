/*'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var manageclaimCtrlStub = {
  index: 'manageclaimCtrl.index',
  show: 'manageclaimCtrl.show',
  create: 'manageclaimCtrl.create',
  update: 'manageclaimCtrl.update',
  destroy: 'manageclaimCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var manageclaimIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './manageclaim.controller': manageclaimCtrlStub
});

describe('Createoutstandingclaimsummarydealerdocument API Router:', function() {

  it('should return an express router instance', function() {
    manageclaimIndex.should.equal(routerStub);
  });

  describe('GET /api/claim/manageclaim/manageclaims', function() {

    it('should route to manageclaim.controller.index', function() {
      routerStub.get
        .withArgs('/', 'manageclaimCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/claim/manageclaim/manageclaims/:id', function() {

    it('should route to manageclaim.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'manageclaimCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/claim/manageclaim/manageclaims', function() {

    it('should route to manageclaim.controller.create', function() {
      routerStub.post
        .withArgs('/', 'manageclaimCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/claim/manageclaim/manageclaims/:id', function() {

    it('should route to manageclaim.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'manageclaimCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/claim/manageclaim/manageclaims/:id', function() {

    it('should route to manageclaim.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'manageclaimCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/claim/manageclaim/manageclaims/:id', function() {

    it('should route to manageclaim.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'manageclaimCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
*/
