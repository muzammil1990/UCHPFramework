/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newViewrepairhistory;

describe('Viewrepairhistory API:', function() {

  describe('GET /uchp-uiservice-rest/api/claim/vehicle/repairhistory', function() {
    var viewrepairhistorys;

    beforeEach(function(done) {
      request(app)
        .get('/uchp-uiservice-rest/api/claim/vehicle/repairhistory')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          viewrepairhistorys = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      viewrepairhistorys.should.be.instanceOf(Array);
    });

  });

  describe('POST /uchp-uiservice-rest/api/claim/vehicle/repairhistory', function() {
    beforeEach(function(done) {
      request(app)
        .post('/uchp-uiservice-rest/api/claim/vehicle/repairhistory')
        .send({
          name: 'New Viewrepairhistory',
          info: 'This is the brand new viewrepairhistory!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newViewrepairhistory = res.body;
          done();
        });
    });

    it('should respond with the newly created viewrepairhistory', function() {
      newViewrepairhistory.name.should.equal('New Viewrepairhistory');
      newViewrepairhistory.info.should.equal('This is the brand new viewrepairhistory!!!');
    });

  });

  describe('GET /uchp-uiservice-rest/api/claim/vehicle/repairhistory/:id', function() {
    var viewrepairhistory;

    beforeEach(function(done) {
      request(app)
        .get('/uchp-uiservice-rest/api/claim/vehicle/repairhistory/' + newViewrepairhistory._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          viewrepairhistory = res.body;
          done();
        });
    });

    afterEach(function() {
      viewrepairhistory = {};
    });

    it('should respond with the requested viewrepairhistory', function() {
      viewrepairhistory.name.should.equal('New Viewrepairhistory');
      viewrepairhistory.info.should.equal('This is the brand new viewrepairhistory!!!');
    });

  });

  describe('PUT /uchp-uiservice-rest/api/claim/vehicle/repairhistory/:id', function() {
    var updatedViewrepairhistory;

    beforeEach(function(done) {
      request(app)
        .put('/uchp-uiservice-rest/api/claim/vehicle/repairhistory/' + newViewrepairhistory._id)
        .send({
          name: 'Updated Viewrepairhistory',
          info: 'This is the updated viewrepairhistory!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedViewrepairhistory = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedViewrepairhistory = {};
    });

    it('should respond with the updated viewrepairhistory', function() {
      updatedViewrepairhistory.name.should.equal('Updated Viewrepairhistory');
      updatedViewrepairhistory.info.should.equal('This is the updated viewrepairhistory!!!');
    });

  });

  describe('DELETE /uchp-uiservice-rest/api/claim/vehicle/repairhistory/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/uchp-uiservice-rest/api/claim/vehicle/repairhistory/' + newViewrepairhistory._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when viewrepairhistory does not exist', function(done) {
      request(app)
        .delete('/uchp-uiservice-rest/api/claim/vehicle/repairhistory/' + newViewrepairhistory._id)
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
