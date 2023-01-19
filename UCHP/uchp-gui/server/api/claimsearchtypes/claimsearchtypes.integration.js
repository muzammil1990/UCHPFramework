/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newClaimsearchtypes;

describe('Claimsearchtypes API:', function() {

  describe('GET /api/claimsearchtypes', function() {
    var claimsearchtypess;

    beforeEach(function(done) {
      request(app)
        .get('/api/claimsearchtypes')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          claimsearchtypess = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      claimsearchtypess.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/claimsearchtypes', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/claimsearchtypes')
        .send({
          name: 'New Claimsearchtypes',
          info: 'This is the brand new claimsearchtypes!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newClaimsearchtypes = res.body;
          done();
        });
    });

    it('should respond with the newly created claimsearchtypes', function() {
      newClaimsearchtypes.name.should.equal('New Claimsearchtypes');
      newClaimsearchtypes.info.should.equal('This is the brand new claimsearchtypes!!!');
    });

  });

  describe('GET /api/claimsearchtypes/:id', function() {
    var claimsearchtypes;

    beforeEach(function(done) {
      request(app)
        .get('/api/claimsearchtypes/' + newClaimsearchtypes._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          claimsearchtypes = res.body;
          done();
        });
    });

    afterEach(function() {
      claimsearchtypes = {};
    });

    it('should respond with the requested claimsearchtypes', function() {
      claimsearchtypes.name.should.equal('New Claimsearchtypes');
      claimsearchtypes.info.should.equal('This is the brand new claimsearchtypes!!!');
    });

  });

  describe('PUT /api/claimsearchtypes/:id', function() {
    var updatedClaimsearchtypes;

    beforeEach(function(done) {
      request(app)
        .put('/api/claimsearchtypes/' + newClaimsearchtypes._id)
        .send({
          name: 'Updated Claimsearchtypes',
          info: 'This is the updated claimsearchtypes!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedClaimsearchtypes = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedClaimsearchtypes = {};
    });

    it('should respond with the updated claimsearchtypes', function() {
      updatedClaimsearchtypes.name.should.equal('Updated Claimsearchtypes');
      updatedClaimsearchtypes.info.should.equal('This is the updated claimsearchtypes!!!');
    });

  });

  describe('DELETE /api/claimsearchtypes/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/claimsearchtypes/' + newClaimsearchtypes._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when claimsearchtypes does not exist', function(done) {
      request(app)
        .delete('/api/claimsearchtypes/' + newClaimsearchtypes._id)
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
