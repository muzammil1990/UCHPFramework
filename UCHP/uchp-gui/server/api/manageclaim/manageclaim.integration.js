'use strict';

var app = require('../..');
import request from 'supertest';

var newManageclaim;

describe('Manageclaim API:', function() {

  describe('GET /uchp/api/api/claim/manageclaim/existingclaim/1/1/1', function() {
    var manageclaims;

    beforeEach(function(done) {
      request(app)
        .get('/uchp/api/api/claim/manageclaim/existingclaim/1/1/1')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          manageclaims = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      manageclaims.should.be.instanceOf(Object);
    });

  });
/*
  describe('POST /uchp/api/api/claim/manageclaim/manageclaims', function() {
    beforeEach(function(done) {
      request(app)
        .post('/uchp/api/api/claim/manageclaim/manageclaims')
        .send({
          name: 'New Manageclaim',
          info: 'This is the brand new manageclaim!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newManageclaim = res.body;
          done();
        });
    });

    it('should respond with the newly created manageclaim', function() {
      newManageclaim.name.should.equal('New Manageclaim');
      newManageclaim.info.should.equal('This is the brand new manageclaim!!!');
    });

  });

  describe('GET /uchp/api/api/claim/manageclaim/manageclaims/:id', function() {
    var manageclaim;

    beforeEach(function(done) {

      request(app)
        .get('/uchp/api/api/claim/manageclaim/manageclaims/' + newManageclaim._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          manageclaim = res.body;
          done();
        });
    });

    afterEach(function() {
      manageclaim = {};
    });

    it('should respond with the requested manageclaim', function() {
      manageclaim.name.should.equal('New Manageclaim');
      manageclaim.info.should.equal('This is the brand new manageclaim!!!');
    });

  });

  describe('PUT /uchp/api/api/claim/manageclaim/manageclaims/:id', function() {
    var updatedManageclaim;

    beforeEach(function(done) {
      request(app)
        .put('/uchp/api/api/claim/manageclaim/manageclaims/' + newManageclaim._id)
        .send({
          name: 'Updated Manageclaim',
          info: 'This is the updated manageclaim!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedManageclaim = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedManageclaim = {};
    });

    it('should respond with the updated manageclaim', function() {
      updatedManageclaim.name.should.equal('Updated Manageclaim');
      updatedManageclaim.info.should.equal('This is the updated manageclaim!!!');
    });

  });

  describe('DELETE /uchp/api/api/claim/manageclaim/manageclaims/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/uchp/api/api/claim/manageclaim/manageclaims/' + newManageclaim._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when manageclaim does not exist', function(done) {
      request(app)
        .delete('/uchp/api/api/claim/manageclaim/manageclaims/' + newManageclaim._id)
        .expect(404)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });
  });
  */
});
