/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newAppealstatuses;

describe('Appealstatuses API:', function() {

  describe('GET /api/apppealstatuses', function() {
    var appealstatusess;

    beforeEach(function(done) {
      request(app)
        .get('/api/apppealstatuses')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          appealstatusess = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      appealstatusess.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/apppealstatuses', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/apppealstatuses')
        .send({
          name: 'New Appealstatuses',
          info: 'This is the brand new appealstatuses!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newAppealstatuses = res.body;
          done();
        });
    });

    it('should respond with the newly created appealstatuses', function() {
      newAppealstatuses.name.should.equal('New Appealstatuses');
      newAppealstatuses.info.should.equal('This is the brand new appealstatuses!!!');
    });

  });

  describe('GET /api/apppealstatuses/:id', function() {
    var appealstatuses;

    beforeEach(function(done) {
      request(app)
        .get('/api/apppealstatuses/' + newAppealstatuses._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          appealstatuses = res.body;
          done();
        });
    });

    afterEach(function() {
      appealstatuses = {};
    });

    it('should respond with the requested appealstatuses', function() {
      appealstatuses.name.should.equal('New Appealstatuses');
      appealstatuses.info.should.equal('This is the brand new appealstatuses!!!');
    });

  });

  describe('PUT /api/apppealstatuses/:id', function() {
    var updatedAppealstatuses;

    beforeEach(function(done) {
      request(app)
        .put('/api/apppealstatuses/' + newAppealstatuses._id)
        .send({
          name: 'Updated Appealstatuses',
          info: 'This is the updated appealstatuses!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedAppealstatuses = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedAppealstatuses = {};
    });

    it('should respond with the updated appealstatuses', function() {
      updatedAppealstatuses.name.should.equal('Updated Appealstatuses');
      updatedAppealstatuses.info.should.equal('This is the updated appealstatuses!!!');
    });

  });

  describe('DELETE /api/apppealstatuses/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/apppealstatuses/' + newAppealstatuses._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when appealstatuses does not exist', function(done) {
      request(app)
        .delete('/api/apppealstatuses/' + newAppealstatuses._id)
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
