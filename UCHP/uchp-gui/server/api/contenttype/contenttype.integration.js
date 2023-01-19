/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newContenttype;

describe('Contenttype API:', function() {

  describe('GET /api/crediting/creditdocuments/contenttype', function() {
    var contenttypes;

    beforeEach(function(done) {
      request(app)
        .get('/api/crediting/creditdocuments/contenttype')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          contenttypes = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      contenttypes.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/crediting/creditdocuments/contenttype', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/crediting/creditdocuments/contenttype')
        .send({
          name: 'New Contenttype',
          info: 'This is the brand new contenttype!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newContenttype = res.body;
          done();
        });
    });

    it('should respond with the newly created contenttype', function() {
      newContenttype.name.should.equal('New Contenttype');
      newContenttype.info.should.equal('This is the brand new contenttype!!!');
    });

  });

  describe('GET /api/crediting/creditdocuments/contenttype/:id', function() {
    var contenttype;

    beforeEach(function(done) {
      request(app)
        .get('/api/crediting/creditdocuments/contenttype/' + newContenttype._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          contenttype = res.body;
          done();
        });
    });

    afterEach(function() {
      contenttype = {};
    });

    it('should respond with the requested contenttype', function() {
      contenttype.name.should.equal('New Contenttype');
      contenttype.info.should.equal('This is the brand new contenttype!!!');
    });

  });

  describe('PUT /api/crediting/creditdocuments/contenttype/:id', function() {
    var updatedContenttype;

    beforeEach(function(done) {
      request(app)
        .put('/api/crediting/creditdocuments/contenttype/' + newContenttype._id)
        .send({
          name: 'Updated Contenttype',
          info: 'This is the updated contenttype!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedContenttype = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedContenttype = {};
    });

    it('should respond with the updated contenttype', function() {
      updatedContenttype.name.should.equal('Updated Contenttype');
      updatedContenttype.info.should.equal('This is the updated contenttype!!!');
    });

  });

  describe('DELETE /api/crediting/creditdocuments/contenttype/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/crediting/creditdocuments/contenttype/' + newContenttype._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when contenttype does not exist', function(done) {
      request(app)
        .delete('/api/crediting/creditdocuments/contenttype/' + newContenttype._id)
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
