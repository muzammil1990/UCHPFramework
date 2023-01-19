'use strict';

// Development specific configuration
// ==================================
module.exports = {

  // MongoDB connection options
  mongo: {
    uri: 'mongodb://localhost/uchpclientangular-dev'
  },

  ping: {
    host: 'https://federate-qa.volvo.com',
    clientId: 'UCHPQA',
    clientSecret: 'tSb938HJwg9YdIDO9S4eB8ZirOyVs8DjbhyoNDklIxDF7GAzucLCgeDFfIbfivtt',
    authorizationURL: '/as/authorization.oauth2',
    tokenURL:'/as/token.oauth2',
    profileURL: '/as/people/profile'
  },

  // Seed database on startup
  seedDB: true

};
