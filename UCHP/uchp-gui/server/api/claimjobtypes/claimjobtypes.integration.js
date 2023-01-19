/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newClaimjobtypes;

describe('Claimjobtypes API:', function() {

  describe('GET /api/claimjobtypes', function() {
    var claimjobtypess;

    beforeEach(function(done) {
      request(app)
        .get('/api/claimjobtypes')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          claimjobtypess = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      claimjobtypess.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/claimjobtypes', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/claimjobtypes')
        .send({
          name: 'New Claimjobtypes',
          info: 'This is the brand new claimjobtypes!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newClaimjobtypes = res.body;
          done();
        });
    });

    it('should respond with the newly created claimjobtypes', function() {
      newClaimjobtypes.name.should.equal('New Claimjobtypes');
      newClaimjobtypes.info.should.equal('This is the brand new claimjobtypes!!!');
    });

  });

  describe('GET /api/claimjobtypes/:id', function() {
    var claimjobtypes;

    beforeEach(function(done) {
      request(app)
        .get('/api/claimjobtypes/' + newClaimjobtypes._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          claimjobtypes = res.body;
          done();
        });
    });

    afterEach(function() {
      claimjobtypes = {};
    });

    it('should respond with the requested claimjobtypes', function() {
      claimjobtypes.name.should.equal('New Claimjobtypes');
      claimjobtypes.info.should.equal('This is the brand new claimjobtypes!!!');
    });

  });

  describe('PUT /api/claimjobtypes/:id', function() {
    var updatedClaimjobtypes;

    beforeEach(function(done) {
      request(app)
        .put('/api/claimjobtypes/' + newClaimjobtypes._id)
        .send({
          name: 'Updated Claimjobtypes',
          info: 'This is the updated claimjobtypes!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedClaimjobtypes = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedClaimjobtypes = {};
    });

    it('should respond with the updated claimjobtypes', function() {
      updatedClaimjobtypes.name.should.equal('Updated Claimjobtypes');
      updatedClaimjobtypes.info.should.equal('This is the updated claimjobtypes!!!');
    });

  });

  describe('DELETE /api/claimjobtypes/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/claimjobtypes/' + newClaimjobtypes._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when claimjobtypes does not exist', function(done) {
      request(app)
        .delete('/api/claimjobtypes/' + newClaimjobtypes._id)
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
