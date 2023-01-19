'use strict';

var path = require('path');
var _ = require('lodash');

function requiredProcessEnv(name) {
  if (!process.env[name]) {
    throw new Error('You must set the ' + name + ' environment variable');
  }
  return process.env[name];
}

// All configurations will extend these options
// ============================================
var all = {
  env: process.env.NODE_ENV,

  // Root path of server
  root: path.normalize(__dirname + '/../../..'),

  // Server port
  port: process.env.PORT || 80,

  // Https port
  portHttps: process.env.PORT_HTTPS || 443,

  // Server IP
  ip: process.env.IP || '0.0.0.0',

  // Should we populate the DB with sample data?
  uchpDB: true,

  // Secret for session, you will want to change this and make it an environment variable
  secrets: {
    session: 'uchp-client-angular-secret'
  },

  // MongoDB connection options
  mongo: {
    options: {
      db: {
        safe: true
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

// Export the config object based on the NODE_ENV
// ==============================================
module.exports = _.merge(
  all,
  require('./shared'),
  require('./' + process.env.NODE_ENV + '.js') || {});
