/*
'use strict';

var app = require('../..');
import request from 'supertest';

var newBrand;

describe('Brand API:', function() {

  describe('GET /api/crediting/creditdocuments/brandlist', function() {
    var brands;

    beforeEach(function(done) {
      request(app)
        .get('/api/crediting/creditdocuments/brandlist')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          brands = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      brands.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/crediting/creditdocuments/brandlist', function() {
    beforeEach(function(done) {
      request(app)
        .post('/api/crediting/creditdocuments/brandlist')
        .send({
          name: 'New Brand',
          info: 'This is the brand new brand!!!'
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newBrand = res.body;
          done();
        });
    });

    it('should respond with the newly created brand', function() {
      newBrand.name.should.equal('New Brand');
      newBrand.info.should.equal('This is the brand new brand!!!');
    });

  });

  describe('GET /api/crediting/creditdocuments/brandlist/:id', function() {
    var brand;

    beforeEach(function(done) {
      request(app)
        .get('/api/crediting/creditdocuments/brandlist/' + newBrand._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          brand = res.body;
          done();
        });
    });

    afterEach(function() {
      brand = {};
    });

    it('should respond with the requested brand', function() {
      brand.name.should.equal('New Brand');
      brand.info.should.equal('This is the brand new brand!!!');
    });

  });

  describe('PUT /api/crediting/creditdocuments/brandlist/:id', function() {
    var updatedBrand;

    beforeEach(function(done) {
      request(app)
        .put('/api/crediting/creditdocuments/brandlist/' + newBrand._id)
        .send({
          name: 'Updated Brand',
          info: 'This is the updated brand!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedBrand = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedBrand = {};
    });

    it('should respond with the updated brand', function() {
      updatedBrand.name.should.equal('Updated Brand');
      updatedBrand.info.should.equal('This is the updated brand!!!');
    });

  });

  describe('DELETE /api/crediting/creditdocuments/brandlist/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/crediting/creditdocuments/brandlist/' + newBrand._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when brand does not exist', function(done) {
      request(app)
        .delete('/api/crediting/creditdocuments/brandlist/' + newBrand._id)
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
