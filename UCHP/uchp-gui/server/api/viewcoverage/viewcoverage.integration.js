/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newViewcoverage;

describe('Viewcoverage API:', function() {

  describe('GET /uchp-uiservice-rest/api/claim/vehicle/coverages', function() {
    var viewcoverages;

    beforeEach(function(done) {
      request(app)
        .get('/uchp-uiservice-rest/api/claim/vehicle/coverages')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          viewcoverages = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      viewcoverages.should.be.instanceOf(Array);
    });

  });

  describe('POST /uchp-uiservice-rest/api/claim/vehicle/coverages', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/claim/vehicle/coverages')
        .send({
          name: 'New Viewcoverage',
          info: 'This is the brand new viewcoverage!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newViewcoverage = res.body;
          done();
        });
    });

    it('should respond with the newly created viewcoverage', function() {
      newViewcoverage.name.should.equal('New Viewcoverage');
      newViewcoverage.info.should.equal('This is the brand new viewcoverage!!!');
    });

  });

  describe('GET /uchp-uiservice-rest/api/claim/vehicle/coverages/:id', function() {
    var viewcoverage;

    beforeEach(function(done) {
      request(app)
        .get('/uchp-uiservice-rest/api/claim/vehicle/coverages/' + newViewcoverage._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          viewcoverage = res.body;
          done();
        });
    });

    afterEach(function() {
      viewcoverage = {};
    });

    it('should respond with the requested viewcoverage', function() {
      viewcoverage.name.should.equal('New Viewcoverage');
      viewcoverage.info.should.equal('This is the brand new viewcoverage!!!');
    });

  });

  describe('PUT /uchp-uiservice-rest/api/claim/vehicle/coverages/:id', function() {
    var updatedViewcoverage;

    beforeEach(function(done) {
      request(app)
        .put('/uchp-uiservice-rest/api/claim/vehicle/coverages/' + newViewcoverage._id)
        .send({
          name: 'Updated Viewcoverage',
          info: 'This is the updated viewcoverage!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedViewcoverage = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedViewcoverage = {};
    });

    it('should respond with the updated viewcoverage', function() {
      updatedViewcoverage.name.should.equal('Updated Viewcoverage');
      updatedViewcoverage.info.should.equal('This is the updated viewcoverage!!!');
    });

  });

  describe('DELETE /uchp-uiservice-rest/api/claim/vehicle/coverages/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/uchp-uiservice-rest/api/claim/vehicle/coverages/' + newViewcoverage._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when viewcoverage does not exist', function(done) {
      request(app)
        .delete('/uchp-uiservice-rest/api/claim/vehicle/coverages/' + newViewcoverage._id)
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
