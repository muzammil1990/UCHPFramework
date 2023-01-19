'use strict';

var proxyquire = require('proxyquire').noPreserveCache();

var createoutstandingclaimsummarydealerdocumentCtrlStub = {
  index: 'createoutstandingclaimsummarydealerdocumentCtrl.index',
  show: 'createoutstandingclaimsummarydealerdocumentCtrl.show',
  create: 'createoutstandingclaimsummarydealerdocumentCtrl.create',
  update: 'createoutstandingclaimsummarydealerdocumentCtrl.update',
  destroy: 'createoutstandingclaimsummarydealerdocumentCtrl.destroy'
};

var routerStub = {
  get: sinon.spy(),
  put: sinon.spy(),
  patch: sinon.spy(),
  post: sinon.spy(),
  delete: sinon.spy()
};

// require the index with our stubbed out modules
var createoutstandingclaimsummarydealerdocumentIndex = proxyquire('./index.js', {
  'express': {
    Router: function() {
      return routerStub;
    }
  },
  './createoutstandingclaimsummarydealerdocument.controller': createoutstandingclaimsummarydealerdocumentCtrlStub
});

describe('Createoutstandingclaimsummarydealerdocument API Router:', function() {

  it('should return an express router instance', function() {
    createoutstandingclaimsummarydealerdocumentIndex.should.equal(routerStub);
  });

  describe('GET /api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments', function() {

    it('should route to createoutstandingclaimsummarydealerdocument.controller.index', function() {
      routerStub.get
        .withArgs('/', 'createoutstandingclaimsummarydealerdocumentCtrl.index')
        .should.have.been.calledOnce;
    });

  });

  describe('GET /api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/:id', function() {

    it('should route to createoutstandingclaimsummarydealerdocument.controller.show', function() {
      routerStub.get
        .withArgs('/:id', 'createoutstandingclaimsummarydealerdocumentCtrl.show')
        .should.have.been.calledOnce;
    });

  });

  describe('POST /api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments', function() {

    it('should route to createoutstandingclaimsummarydealerdocument.controller.create', function() {
      routerStub.post
        .withArgs('/', 'createoutstandingclaimsummarydealerdocumentCtrl.create')
        .should.have.been.calledOnce;
    });

  });

  describe('PUT /api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/:id', function() {

    it('should route to createoutstandingclaimsummarydealerdocument.controller.update', function() {
      routerStub.put
        .withArgs('/:id', 'createoutstandingclaimsummarydealerdocumentCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('PATCH /api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/:id', function() {

    it('should route to createoutstandingclaimsummarydealerdocument.controller.update', function() {
      routerStub.patch
        .withArgs('/:id', 'createoutstandingclaimsummarydealerdocumentCtrl.update')
        .should.have.been.calledOnce;
    });

  });

  describe('DELETE /api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/:id', function() {

    it('should route to createoutstandingclaimsummarydealerdocument.controller.destroy', function() {
      routerStub.delete
        .withArgs('/:id', 'createoutstandingclaimsummarydealerdocumentCtrl.destroy')
        .should.have.been.calledOnce;
    });

  });

});
