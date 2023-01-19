'use strict';

var app = require('../..');
import request from 'supertest';

var newClaimjob;

describe('Claimjob API:', function() {

  describe('GET /uchp/api/api/claim/manageclaim/existingclaim/1/1/1/1/tabs/1', function() {
    var claimjobs;

    beforeEach(function(done) {
      request(app)
        .get('/uchp/api/api/claim/manageclaim/existingclaim/1/1/1/1/tabs/1')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          claimjobs = res.body;
          done();
        });
    });

    it('should respond with JSON object', function() {
      claimjobs.should.be.instanceOf(Object);
    });

  });
/*
  describe('POST /uchp/api/api/claim/manageclaim/claimjobs', function() {
    beforeEach(function(done) {
      request(app)
        .post('/uchp/api/api/claim/manageclaim/claimjobs')
        .send({
          name: 'New Claimjob',
          info: 'This is the brand new claimjob!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newClaimjob = res.body;
          done();
        });
    });

    it('should respond with the newly created claimjob', function() {
      newClaimjob.name.should.equal('New Claimjob');
      newClaimjob.info.should.equal('This is the brand new claimjob!!!');
    });

  });

  describe('GET /uchp/api/api/claim/manageclaim/claimjobs/:id', function() {
    var claimjob;

    beforeEach(function(done) {

      request(app)
        .get('/uchp/api/api/claim/manageclaim/claimjobs/' + newClaimjob._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          claimjob = res.body;
          done();
        });
    });

    afterEach(function() {
      claimjob = {};
    });

    it('should respond with the requested claimjob', function() {
      claimjob.name.should.equal('New Claimjob');
      claimjob.info.should.equal('This is the brand new claimjob!!!');
    });

  });

  describe('PUT /uchp/api/api/claim/manageclaim/claimjobs/:id', function() {
    var updatedClaimjob;

    beforeEach(function(done) {
      request(app)
        .put('/uchp/api/api/claim/manageclaim/claimjobs/' + newClaimjob._id)
        .send({
          name: 'Updated Claimjob',
          info: 'This is the updated claimjob!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedClaimjob = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedClaimjob = {};
    });

    it('should respond with the updated claimjob', function() {
      updatedClaimjob.name.should.equal('Updated Claimjob');
      updatedClaimjob.info.should.equal('This is the updated claimjob!!!');
    });

  });

  describe('DELETE /uchp/api/api/claim/manageclaim/claimjobs/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/uchp/api/api/claim/manageclaim/claimjobs/' + newClaimjob._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when claimjob does not exist', function(done) {
      request(app)
        .delete('/uchp/api/api/claim/manageclaim/claimjobs/' + newClaimjob._id)
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
