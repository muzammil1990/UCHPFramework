/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newClaimlistsearchcriteria;

describe('Claimlistsearchcriteria API:', function() {

  describe('GET /api/claimlistsearchcriterias', function() {
    var claimlistsearchcriterias;

    beforeEach(function(done) {
      request(app)
        .get('/api/claimlistsearchcriterias')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          claimlistsearchcriterias = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      claimlistsearchcriterias.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/claimlistsearchcriterias', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/claimlistsearchcriterias')
        .send({
          name: 'New Claimlistsearchcriteria',
          info: 'This is the brand new claimlistsearchcriteria!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newClaimlistsearchcriteria = res.body;
          done();
        });
    });

    it('should respond with the newly created claimlistsearchcriteria', function() {
      newClaimlistsearchcriteria.name.should.equal('New Claimlistsearchcriteria');
      newClaimlistsearchcriteria.info.should.equal('This is the brand new claimlistsearchcriteria!!!');
    });

  });

  describe('GET /api/claimlistsearchcriterias/:id', function() {
    var claimlistsearchcriteria;

    beforeEach(function(done) {
      request(app)
        .get('/api/claimlistsearchcriterias/' + newClaimlistsearchcriteria._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          claimlistsearchcriteria = res.body;
          done();
        });
    });

    afterEach(function() {
      claimlistsearchcriteria = {};
    });

    it('should respond with the requested claimlistsearchcriteria', function() {
      claimlistsearchcriteria.name.should.equal('New Claimlistsearchcriteria');
      claimlistsearchcriteria.info.should.equal('This is the brand new claimlistsearchcriteria!!!');
    });

  });

  describe('PUT /api/claimlistsearchcriterias/:id', function() {
    var updatedClaimlistsearchcriteria;

    beforeEach(function(done) {
      request(app)
        .put('/api/claimlistsearchcriterias/' + newClaimlistsearchcriteria._id)
        .send({
          name: 'Updated Claimlistsearchcriteria',
          info: 'This is the updated claimlistsearchcriteria!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedClaimlistsearchcriteria = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedClaimlistsearchcriteria = {};
    });

    it('should respond with the updated claimlistsearchcriteria', function() {
      updatedClaimlistsearchcriteria.name.should.equal('Updated Claimlistsearchcriteria');
      updatedClaimlistsearchcriteria.info.should.equal('This is the updated claimlistsearchcriteria!!!');
    });

  });

  describe('DELETE /api/claimlistsearchcriterias/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/claimlistsearchcriterias/' + newClaimlistsearchcriteria._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when claimlistsearchcriteria does not exist', function(done) {
      request(app)
        .delete('/api/claimlistsearchcriterias/' + newClaimlistsearchcriteria._id)
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
