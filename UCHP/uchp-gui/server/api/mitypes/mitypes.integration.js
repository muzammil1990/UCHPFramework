/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newMitypes;

describe('Mitypes API:', function() {

  describe('GET /api/mitypes', function() {
    var mitypess;

    beforeEach(function(done) {
      request(app)
        .get('/api/mitypes')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          mitypess = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      mitypess.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/mitypes', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/mitypes')
        .send({
          name: 'New Mitypes',
          info: 'This is the brand new mitypes!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newMitypes = res.body;
          done();
        });
    });

    it('should respond with the newly created mitypes', function() {
      newMitypes.name.should.equal('New Mitypes');
      newMitypes.info.should.equal('This is the brand new mitypes!!!');
    });

  });

  describe('GET /api/mitypes/:id', function() {
    var mitypes;

    beforeEach(function(done) {
      request(app)
        .get('/api/mitypes/' + newMitypes._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          mitypes = res.body;
          done();
        });
    });

    afterEach(function() {
      mitypes = {};
    });

    it('should respond with the requested mitypes', function() {
      mitypes.name.should.equal('New Mitypes');
      mitypes.info.should.equal('This is the brand new mitypes!!!');
    });

  });

  describe('PUT /api/mitypes/:id', function() {
    var updatedMitypes;

    beforeEach(function(done) {
      request(app)
        .put('/api/mitypes/' + newMitypes._id)
        .send({
          name: 'Updated Mitypes',
          info: 'This is the updated mitypes!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedMitypes = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedMitypes = {};
    });

    it('should respond with the updated mitypes', function() {
      updatedMitypes.name.should.equal('Updated Mitypes');
      updatedMitypes.info.should.equal('This is the updated mitypes!!!');
    });

  });

  describe('DELETE /api/mitypes/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/mitypes/' + newMitypes._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when mitypes does not exist', function(done) {
      request(app)
        .delete('/api/mitypes/' + newMitypes._id)
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
