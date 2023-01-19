'use strict';

// Test specific configuration
// ===========================
module.exports = {
  // MongoDB connection options
  mongo: {
    uri: 'mongodb://localhost/uchpclientangular-test?socketTimeoutMS=90000'
  },
  sequelize: {
    uri: 'sqlite://',
    options: {
      logging: false,
      storage: 'test.sqlite',
      define: {
        timestamps: false
      }
    }
  },
  ping: {
    host: 'https://federate-qa.volvo.com',
    clientId: 'UCHPQA',
    clientSecret: 'tSb938HJwg9YdIDO9S4eB8ZirOyVs8DjbhyoNDklIxDF7GAzucLCgeDFfIbfivtt',
    authorizationURL: '/as/authorization.oauth2',
    tokenURL:'/as/token.oauth2',
    profileURL: '/as/people/profile'
  }
};
