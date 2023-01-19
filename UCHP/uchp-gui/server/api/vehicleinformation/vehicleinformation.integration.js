/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newVehicleinformation;

describe('Vehicleinformation API:', function() {

  describe('GET /api/claim/vehicle/coverages', function() {
    var vehicleinformations;

    beforeEach(function(done) {
      request(app)
        .get('/api/claim/vehicle/coverages')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          vehicleinformations = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      vehicleinformations.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/claim/vehicle/coverages', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/claim/vehicle/coverages')
        .send({
          name: 'New Vehicleinformation',
          info: 'This is the brand new vehicleinformation!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newVehicleinformation = res.body;
          done();
        });
    });

    it('should respond with the newly created vehicleinformation', function() {
      newVehicleinformation.name.should.equal('New Vehicleinformation');
      newVehicleinformation.info.should.equal('This is the brand new vehicleinformation!!!');
    });

  });

  describe('GET /api/claim/vehicle/coverages/:id', function() {
    var vehicleinformation;

    beforeEach(function(done) {
      request(app)
        .get('/api/claim/vehicle/coverages/' + newVehicleinformation._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          vehicleinformation = res.body;
          done();
        });
    });

    afterEach(function() {
      vehicleinformation = {};
    });

    it('should respond with the requested vehicleinformation', function() {
      vehicleinformation.name.should.equal('New Vehicleinformation');
      vehicleinformation.info.should.equal('This is the brand new vehicleinformation!!!');
    });

  });

  describe('PUT /api/claim/vehicle/coverages/:id', function() {
    var updatedVehicleinformation;

    beforeEach(function(done) {
      request(app)
        .put('/api/claim/vehicle/coverages/' + newVehicleinformation._id)
        .send({
          name: 'Updated Vehicleinformation',
          info: 'This is the updated vehicleinformation!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedVehicleinformation = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedVehicleinformation = {};
    });

    it('should respond with the updated vehicleinformation', function() {
      updatedVehicleinformation.name.should.equal('Updated Vehicleinformation');
      updatedVehicleinformation.info.should.equal('This is the updated vehicleinformation!!!');
    });

  });

  describe('DELETE /api/claim/vehicle/coverages/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/claim/vehicle/coverages/' + newVehicleinformation._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when vehicleinformation does not exist', function(done) {
      request(app)
        .delete('/api/claim/vehicle/coverages/' + newVehicleinformation._id)
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
