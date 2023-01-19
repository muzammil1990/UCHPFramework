'use strict';

// Production specific configuration
// =================================
module.exports = {
  // Server IP
  ip:     process.env.OPENSHIFT_NODEJS_IP ||
          process.env.IP ||
          undefined,

  // Server port
  port:   process.env.OPENSHIFT_NODEJS_PORT ||
          process.env.PORT ||
          8080,

  // MongoDB connection options
  mongo: {
    uri:  process.env.MONGOLAB_URI ||
          process.env.MONGOHQ_URL ||
          process.env.OPENSHIFT_MONGODB_DB_URL +
          process.env.OPENSHIFT_APP_NAME ||
          'mongodb://localhost/uchpclientangular'
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
