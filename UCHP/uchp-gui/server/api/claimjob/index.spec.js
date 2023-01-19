/*'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var claimjobCtrlStub = {
  index: 'claimjobCtrl.index',
  show: 'claimjobCtrl.show',
  create: 'claimjobCtrl.create',
  update: 'claimjobCtrl.update',
  destroy: 'claimjobCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var claimjobIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './claimjob.controller': claimjobCtrlStub
});

describe('Createoutstandingclaimsummarydealerdocument API Router:', function() {

  it('should return an express router instance', function() {
    claimjobIndex.should.equal(routerStub);
  });

  describe('GET /api/claim/manageclaim/claimjobs', function() {

    it('should route to claimjob.controller.index', function() {
      routerStub.get
        .withArgs('/', 'claimjobCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/claim/manageclaim/claimjobs/:id', function() {

    it('should route to claimjob.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'claimjobCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/claim/manageclaim/claimjobs', function() {

    it('should route to claimjob.controller.create', function() {
      routerStub.post
        .withArgs('/', 'claimjobCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/claim/manageclaim/claimjobs/:id', function() {

    it('should route to claimjob.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'claimjobCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/claim/manageclaim/claimjobs/:id', function() {

    it('should route to claimjob.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'claimjobCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/claim/manageclaim/claimjobs/:id', function() {

    it('should route to claimjob.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'claimjobCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
*/
