/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newClaimsearchresult;

describe('Claimsearchresult API:', function() {

  describe('GET /api/claimsearchresult', function() {
    var claimsearchresults;

    beforeEach(function(done) {
      request(app)
        .get('/api/claimsearchresult')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          claimsearchresults = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      claimsearchresults.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/claimsearchresult', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/claimsearchresult')
        .send({
          name: 'New Claimsearchresult',
          info: 'This is the brand new claimsearchresult!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newClaimsearchresult = res.body;
          done();
        });
    });

    it('should respond with the newly created claimsearchresult', function() {
      newClaimsearchresult.name.should.equal('New Claimsearchresult');
      newClaimsearchresult.info.should.equal('This is the brand new claimsearchresult!!!');
    });

  });

  describe('GET /api/claimsearchresult/:id', function() {
    var claimsearchresult;

    beforeEach(function(done) {
      request(app)
        .get('/api/claimsearchresult/' + newClaimsearchresult._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          claimsearchresult = res.body;
          done();
        });
    });

    afterEach(function() {
      claimsearchresult = {};
    });

    it('should respond with the requested claimsearchresult', function() {
      claimsearchresult.name.should.equal('New Claimsearchresult');
      claimsearchresult.info.should.equal('This is the brand new claimsearchresult!!!');
    });

  });

  describe('PUT /api/claimsearchresult/:id', function() {
    var updatedClaimsearchresult;

    beforeEach(function(done) {
      request(app)
        .put('/api/claimsearchresult/' + newClaimsearchresult._id)
        .send({
          name: 'Updated Claimsearchresult',
          info: 'This is the updated claimsearchresult!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedClaimsearchresult = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedClaimsearchresult = {};
    });

    it('should respond with the updated claimsearchresult', function() {
      updatedClaimsearchresult.name.should.equal('Updated Claimsearchresult');
      updatedClaimsearchresult.info.should.equal('This is the updated claimsearchresult!!!');
    });

  });

  describe('DELETE /api/claimsearchresult/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/claimsearchresult/' + newClaimsearchresult._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when claimsearchresult does not exist', function(done) {
      request(app)
        .delete('/api/claimsearchresult/' + newClaimsearchresult._id)
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
