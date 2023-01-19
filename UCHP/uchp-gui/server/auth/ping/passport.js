(function () {

  'use strict';

  var passport = require('passport');
  var Oauth2Strategy = require('passport-ping-oauth2').Strategy;
  var auth = require('./auth.controller');
  var config = require('../../config/environment');
  var User = require('../../api/user/user.model');

  exports.setup = function (config) {
    /**
     * HTTP  Request
     * POST https://localhost:9031/as/token.oauth2 HTTP/1.1
     * Headers
     * Content-Type:
     * Authorization:
     * application/x-www-form-urlencoded
     * Basic cm9fY2xpZW50OjJGZWRlcmF0ZQ==
     * Body
     * grant_type=password&username=joe&password=2Federate&scope=edit
     * */
    var strategy = new OAuth2Strategy({
        host: config.ping.host, // ping federate mock, read it from config
        clientID: config.ping.clientId,
        clientSecret: config.ping.clientSecret,
        callbackURL: '/auth/ping/callback',
        grant_type: 'code'
      },

      function (req, accessToken, refreshToken, params, profile, done) {  // #4
        console.log('#4 in ping strategy ');

       var user = new User();
        user._id = profile.id;
        user.firstName = profile.firstName;
        user.lastName = profile.lastName;
        user.emailAddress = profile.emailAddress;
        user.mobilePhoneNumber = profile.mobilePhoneNumber;
        user.officePhoneNumber = profile.officePhoneNumber;
        user.postalAddress = profile.postalAddress;
        user.postalCode = profile.postalCode;
        user.roles = profile.roles;
        user.street = profile.street;
        user.accessToken = accessToken;
        user.refreshToken = refreshToken;

        auth.syncUser(user, refreshToken, function (synced, err) {
          console.log('passport sync token');
          if (!err && synced !== null && synced) {
            var copy = synced;
            delete copy.refreshToken;
            req.user = copy.toJSON();
          }
          return done(err, synced.toJSON());
        });

      });

    passport.use(strategy);
  }

})
();
