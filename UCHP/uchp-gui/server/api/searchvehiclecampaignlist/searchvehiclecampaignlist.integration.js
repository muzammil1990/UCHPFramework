/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newSearchvehiclecampaignlist;

describe('Searchvehiclecampaignlist API:', function() {

  describe('GET /api/claim/vehicle/searchVehicleCampaignList', function() {
    var searchvehiclecampaignlists;

    beforeEach(function(done) {
      request(app)
        .get('/api/claim/vehicle/searchVehicleCampaignList')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          searchvehiclecampaignlists = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      searchvehiclecampaignlists.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/claim/vehicle/searchVehicleCampaignList', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/claim/vehicle/searchVehicleCampaignList')
        .send({
          name: 'New Searchvehiclecampaignlist',
          info: 'This is the brand new searchvehiclecampaignlist!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newSearchvehiclecampaignlist = res.body;
          done();
        });
    });

    it('should respond with the newly created searchvehiclecampaignlist', function() {
      newSearchvehiclecampaignlist.name.should.equal('New Searchvehiclecampaignlist');
      newSearchvehiclecampaignlist.info.should.equal('This is the brand new searchvehiclecampaignlist!!!');
    });

  });

  describe('GET /api/claim/vehicle/searchVehicleCampaignList/:id', function() {
    var searchvehiclecampaignlist;

    beforeEach(function(done) {
      request(app)
        .get('/api/claim/vehicle/searchVehicleCampaignList/' + newSearchvehiclecampaignlist._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          searchvehiclecampaignlist = res.body;
          done();
        });
    });

    afterEach(function() {
      searchvehiclecampaignlist = {};
    });

    it('should respond with the requested searchvehiclecampaignlist', function() {
      searchvehiclecampaignlist.name.should.equal('New Searchvehiclecampaignlist');
      searchvehiclecampaignlist.info.should.equal('This is the brand new searchvehiclecampaignlist!!!');
    });

  });

  describe('PUT /api/claim/vehicle/searchVehicleCampaignList/:id', function() {
    var updatedSearchvehiclecampaignlist;

    beforeEach(function(done) {
      request(app)
        .put('/api/claim/vehicle/searchVehicleCampaignList/' + newSearchvehiclecampaignlist._id)
        .send({
          name: 'Updated Searchvehiclecampaignlist',
          info: 'This is the updated searchvehiclecampaignlist!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedSearchvehiclecampaignlist = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedSearchvehiclecampaignlist = {};
    });

    it('should respond with the updated searchvehiclecampaignlist', function() {
      updatedSearchvehiclecampaignlist.name.should.equal('Updated Searchvehiclecampaignlist');
      updatedSearchvehiclecampaignlist.info.should.equal('This is the updated searchvehiclecampaignlist!!!');
    });

  });

  describe('DELETE /api/claim/vehicle/searchVehicleCampaignList/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/claim/vehicle/searchVehicleCampaignList/' + newSearchvehiclecampaignlist._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when searchvehiclecampaignlist does not exist', function(done) {
      request(app)
        .delete('/api/claim/vehicle/searchVehicleCampaignList/' + newSearchvehiclecampaignlist._id)
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
