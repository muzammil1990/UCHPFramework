/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newMiprintstatuses;

describe('Miprintstatuses API:', function() {

  describe('GET /api/miprintstatuses', function() {
    var miprintstatusess;

    beforeEach(function(done) {
      request(app)
        .get('/api/miprintstatuses')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          miprintstatusess = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      miprintstatusess.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/miprintstatuses', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/miprintstatuses')
        .send({
          name: 'New Miprintstatuses',
          info: 'This is the brand new miprintstatuses!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newMiprintstatuses = res.body;
          done();
        });
    });

    it('should respond with the newly created miprintstatuses', function() {
      newMiprintstatuses.name.should.equal('New Miprintstatuses');
      newMiprintstatuses.info.should.equal('This is the brand new miprintstatuses!!!');
    });

  });

  describe('GET /api/miprintstatuses/:id', function() {
    var miprintstatuses;

    beforeEach(function(done) {
      request(app)
        .get('/api/miprintstatuses/' + newMiprintstatuses._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          miprintstatuses = res.body;
          done();
        });
    });

    afterEach(function() {
      miprintstatuses = {};
    });

    it('should respond with the requested miprintstatuses', function() {
      miprintstatuses.name.should.equal('New Miprintstatuses');
      miprintstatuses.info.should.equal('This is the brand new miprintstatuses!!!');
    });

  });

  describe('PUT /api/miprintstatuses/:id', function() {
    var updatedMiprintstatuses;

    beforeEach(function(done) {
      request(app)
        .put('/api/miprintstatuses/' + newMiprintstatuses._id)
        .send({
          name: 'Updated Miprintstatuses',
          info: 'This is the updated miprintstatuses!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedMiprintstatuses = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedMiprintstatuses = {};
    });

    it('should respond with the updated miprintstatuses', function() {
      updatedMiprintstatuses.name.should.equal('Updated Miprintstatuses');
      updatedMiprintstatuses.info.should.equal('This is the updated miprintstatuses!!!');
    });

  });

  describe('DELETE /api/miprintstatuses/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/miprintstatuses/' + newMiprintstatuses._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when miprintstatuses does not exist', function(done) {
      request(app)
        .delete('/api/miprintstatuses/' + newMiprintstatuses._id)
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
