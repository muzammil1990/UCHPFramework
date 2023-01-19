/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newCreditdocument;

describe('Creditdocument API:', function() {

  describe('GET /api/crediting/creditdocuments/creditingdocuments', function() {
    var creditdocuments;

    beforeEach(function(done) {
      request(app)
        .get('/api/crediting/creditdocuments/creditingdocuments')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          creditdocuments = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      creditdocuments.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/crediting/creditdocuments/creditingdocuments', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/crediting/creditdocuments/creditingdocuments')
        .send({
          name: 'New Creditdocument',
          info: 'This is the brand1 new creditdocument!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newCreditdocument = res.body;
          done();
        });
    });

    it('should respond with the newly created creditdocument', function() {
      newCreditdocument.name.should.equal('New Creditdocument');
      newCreditdocument.info.should.equal('This is the brand1 new creditdocument!!!');
    });

  });

  describe('GET /api/crediting/creditdocuments/creditingdocuments/:id', function() {
    var creditdocument;

    beforeEach(function(done) {
      request(app)
        .get('/api/crediting/creditdocuments/creditingdocuments/' + newCreditdocument._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          creditdocument = res.body;
          done();
        });
    });

    afterEach(function() {
      creditdocument = {};
    });

    it('should respond with the requested creditdocument', function() {
      creditdocument.name.should.equal('New Creditdocument');
      creditdocument.info.should.equal('This is the brand1 new creditdocument!!!');
    });

  });

  describe('PUT /api/crediting/creditdocuments/creditingdocuments/:id', function() {
    var updatedCreditdocument;

    beforeEach(function(done) {
      request(app)
        .put('/api/crediting/creditdocuments/creditingdocuments/' + newCreditdocument._id)
        .send({
          name: 'Updated Creditdocument',
          info: 'This is the updated creditdocument!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedCreditdocument = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedCreditdocument = {};
    });

    it('should respond with the updated creditdocument', function() {
      updatedCreditdocument.name.should.equal('Updated Creditdocument');
      updatedCreditdocument.info.should.equal('This is the updated creditdocument!!!');
    });

  });

  describe('DELETE /api/crediting/creditdocuments/creditingdocuments/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/crediting/creditdocuments/creditingdocuments/' + newCreditdocument._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when creditdocument does not exist', function(done) {
      request(app)
        .delete('/api/crediting/creditdocuments/creditingdocuments/' + newCreditdocument._id)
        .expect(404)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

  });

});
*/
