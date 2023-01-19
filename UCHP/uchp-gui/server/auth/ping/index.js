'use strict';

import express from 'express';
import passport from 'passport';
import config from '../../config/environment';
import User from '../../api/user/user.model';
import controller from './auth.controller';
var Oauth2Strategy = require('passport-ping-oauth2').Strategy;


// Passport Configuration
require('../local/passport').setup(User, config);
var router = express.Router();


/** #1
 * Initial entry point for Ping Federate Oauth authentication.
 **/
router
  .get('/', function (req, res, next) {
    console.log('-------- ping\index.js req --------');
    console.log(req);
    // #2
    // we are attaching the parameters to the query due to PingFederate in Volvo setup
      passport.authenticate('outh2', {
      failureRedirect: '/',
      clientId: 'UCHPQA',
      clientSecret: 'tSb938HJwg9YdIDO9S4eB8ZirOyVs8DjbhyoNDklIxDF7GAzucLCgeDFfIbfivtt',
      scope: [
        'profile'
      ],
      grant_type: 'code',
      session: false
    }, function (err) {
      if (err) {
        var data = JSON.parse(err.oauthError.data);
        var error = {
          code: err.oauthError.statusCode,
          name: data.name,
          message: data.message
        };
        handleError(res, error);
      } else {
        controller.handleCallback(req, res, next);
      }
    })(req, res, next)
  });


module.exports=router;
