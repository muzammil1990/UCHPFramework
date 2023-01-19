/**
 * Created by a232625 on 2016-07-22.
 */
'use strict';

var app = require('../..');
import request from 'supertest';

var newUsergroup;

describe('Usergroup API:', function() {

  describe('GET /uchp/api/api/usergroup/', function() {
    var usergroups;

    beforeEach(function(done) {
      request(app)
        .get('/uchp/api/api/usergroup/')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          usergroups = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      usergroups.should.be.instanceOf(Array);
    });

  });

  describe('POST /uchp/api/api/usergroup/', function() {
    beforeEach(function(done) {
      request(app)
        .post('/uchp/api/api/usergroup/')
        .send({
          userid: 'ClaimHandlerDealer',
          usergroup: 'ClaimHandlerDealer',
          objversion: 1,
          companyno: 1
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newUsergroup = res.body;
          done();
        });
    });

    it('should respond with the newly created usergroup', function() {
      newUsergroup.userid.should.equal('ClaimHandlerDealer');
      newUsergroup.usergroup.should.equal('ClaimHandlerDealer');
    });

  });

  describe('PUT /uchp/api/api/usergroup/:id', function() {
    var updatedUsergroup;

    beforeEach(function(done) {
      request(app)
        .put('/uchp/api/api/usergroup/' + newUsergroup._id)
        .send({
          userid: 'ClaimHandlerDealer',
          usergroup: 'ClaimHandlerDealer',
          objversion: 1,
          companyno: 1
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedUsergroup = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedUsergroup = {};
    });

    it('should respond with the updated usergroup', function() {
      updatedUsergroup.userid.should.equal('ClaimHandlerDealer');
      updatedUsergroup.usergroup.should.equal('ClaimHandlerDealer');
    });

  });

  describe('DELETE /uchp/api/api/usergroup/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/uchp/api/api/usergroup/' + newUsergroup._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when usergroup does not exist', function(done) {
      request(app)
        .delete('/uchp/api/api/usergroup/' + newUsergroup._id)
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
