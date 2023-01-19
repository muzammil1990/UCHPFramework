'use strict';

var app = require('../..');
import request from 'supertest';

var newCreateoutstandingclaimsummarydealerdocument;

describe('Createoutstandingclaimsummarydealerdocument API:', function() {

  describe('GET /uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments', function() {
    var createoutstandingclaimsummarydealerdocuments;

    beforeEach(function(done) {
      request(app)
        .get('/uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          createoutstandingclaimsummarydealerdocuments = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      createoutstandingclaimsummarydealerdocuments.should.be.instanceOf(Array);
    });

  });

  describe('POST /uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments', function() {
    beforeEach(function(done) {
      request(app)
        .post('/uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments')
        .send({
          name: 'New Createoutstandingclaimsummarydealerdocument',
          info: 'This is the brand new createoutstandingclaimsummarydealerdocument!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newCreateoutstandingclaimsummarydealerdocument = res.body;
          done();
        });
    });

    it('should respond with the newly created createoutstandingclaimsummarydealerdocument', function() {
      newCreateoutstandingclaimsummarydealerdocument.name.should.equal('New Createoutstandingclaimsummarydealerdocument');
      newCreateoutstandingclaimsummarydealerdocument.info.should.equal('This is the brand new createoutstandingclaimsummarydealerdocument!!!');
    });

  });
  /*
  describe('GET /uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/:id', function() {
    var createoutstandingclaimsummarydealerdocument;

    beforeEach(function(done) {

      request(app)
        .get('/uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/' + newCreateoutstandingclaimsummarydealerdocument._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          createoutstandingclaimsummarydealerdocument = res.body;
          done();
        });
    });

    afterEach(function() {
      createoutstandingclaimsummarydealerdocument = {};
    });

    it('should respond with the requested createoutstandingclaimsummarydealerdocument', function() {
      createoutstandingclaimsummarydealerdocument.name.should.equal('New Createoutstandingclaimsummarydealerdocument');
      createoutstandingclaimsummarydealerdocument.info.should.equal('This is the brand new createoutstandingclaimsummarydealerdocument!!!');
    });

  });
  */
  describe('PUT /uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/:id', function() {
    var updatedCreateoutstandingclaimsummarydealerdocument;

    beforeEach(function(done) {
      request(app)
        .put('/uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/' + newCreateoutstandingclaimsummarydealerdocument._id)
        .send({
          name: 'Updated Createoutstandingclaimsummarydealerdocument',
          info: 'This is the updated createoutstandingclaimsummarydealerdocument!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedCreateoutstandingclaimsummarydealerdocument = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedCreateoutstandingclaimsummarydealerdocument = {};
    });

    it('should respond with the updated createoutstandingclaimsummarydealerdocument', function() {
      updatedCreateoutstandingclaimsummarydealerdocument.name.should.equal('Updated Createoutstandingclaimsummarydealerdocument');
      updatedCreateoutstandingclaimsummarydealerdocument.info.should.equal('This is the updated createoutstandingclaimsummarydealerdocument!!!');
    });

  });

  describe('DELETE /uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/' + newCreateoutstandingclaimsummarydealerdocument._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when createoutstandingclaimsummarydealerdocument does not exist', function(done) {
      request(app)
        .delete('/uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments/' + newCreateoutstandingclaimsummarydealerdocument._id)
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
