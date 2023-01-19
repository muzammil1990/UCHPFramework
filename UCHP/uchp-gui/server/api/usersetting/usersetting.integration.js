/**
 * Created by a232625 on 2016-07-22.
 */
'use strict';

var app = require('../..');
import request from 'supertest';

var newUsersetting;

describe('Usersetting API:', function() {

  describe('GET /uchp/api/api/usergroup', function() {
    var Usersettings;

    beforeEach(function(done) {
      request(app)
        .get('/uchp/api/api/usergroup')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          Usersettings = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      Usersettings.should.be.instanceOf(Array);
    });

  });

  describe('POST /uchp/api/api/usersetting', function() {
    beforeEach(function(done) {
      request(app)
        .post('/uchp/api/api/usersetting')
        .send({
          settingtype: 'AUTHORIZATIONLEVEL',
          value: '-1;-1',
          description: 'Warranty',
          userid: 'ClaimHandlerDealer',
          usersettingcompanyno: 1
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newUsersetting = res.body;
          done();
        });
    });

    it('should respond with the newly created Usersetting', function() {
      newUsersetting.settingtype.should.equal('AUTHORIZATIONLEVEL');
      newUsersetting.description.should.equal('Warranty');
    });

  });

  describe('PUT /uchp/api/api/usersetting/:id', function() {
    var updatedUsersetting;

    beforeEach(function(done) {
      request(app)
        .put('/uchp/api/api/usersetting/' + newUsersetting._id)
        .send({
          settingtype: 'AUTHORIZATIONLEVEL',
          description: 'Warranty'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedUsersetting = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedUsersetting = {};
    });

    it('should respond with the updated Usersetting', function() {
      updatedUsersetting.settingtype.should.equal('AUTHORIZATIONLEVEL');
      updatedUsersetting.description.should.equal('Warranty');
    });

  });

  describe('DELETE /uchp/api/api/usersetting/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/uchp/api/api/usersetting/' + newUsersetting._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when Usersetting does not exist', function(done) {
      request(app)
        .delete('/uchp/api/api/usergroup/' + newUsersetting._id)
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
