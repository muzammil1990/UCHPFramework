'use strict';

import config from '../config/environment';
import jwt from 'jsonwebtoken';
import expressJwt from 'express-jwt';
import compose from 'composable-middleware';
import User from '../api/user/user.model';
import fs from 'fs';

var pingQACertificate = fs.readFileSync('./server/auth/certificate/posqa.crt');

/**
 * Attaches the user object to the request if authenticated
 * Otherwise returns 403
 */
export function isAuthenticated() {
  return compose()
    .use(function(req, res, next){
      //expressJwt({ secret: secretCallback })(req, res, next);
      //var token = req.token;
      var token = 'eyJhbGciOiJSUzUxMiIsImtpZCI6IjAifQ.eyJtZW1iZXIiOiJub25lIiwidWlkIjoiTTA2NzY3NSIsIm1lbWJlcm9mIjoidXNlbGF0ZXIiLCJlbWFpbCI6IndzYUB2b2x2by5jb20iLCJleHAiOjE0ODUzNDExNjAsInNjb3BlIjpbInByb2ZpbGUiXSwiY2xpZW50X2lkIjoiVUNIUFFBIn0.Ze1W8E7ThFOaGeOLGCKulnMGLBXbidfBMZFxx-WC0PM9CggaQjx9-gwqQGFUKxCi_7GvD7-myAxLawiKK_veHyas0NH_PxivL6v8FDx0X6x-H3g4wR0RPpWRGSDtnmZqCXJLmgPSRZCOCn5Vyy6zWrWr87qoc4q5ftLc-zf6tw_MNacFeO1BD7f9llRi9IC1eEER1w3VJjRxy4NlJ7Lbeotdr0mxhdc0VDP-XmnowksPNmdBJciOb26Wc6bkFoAjOJgA-5K0ZVvxbOcv7LSyUNTXhXPtK8-PWSTvEZKOF4BEYaZnQzPY3Uo7ZXb4pHFiur0P3WdWfcdNg6S_2f2QDw';
      var jwtDecode = require('jwt-decode');
      var decoded = jwtDecode(token);
      req.user = decoded;
      next();
    })
    .use(function(req, res, next) {
      loadAuthenticatedUser(req, req.user).then(user => {
        if (!user) {
          return res.status(401).end();
        }
        req.user = user;
        next();
        return user;
      })
        .catch(err => {
          next(err);
        });
    });
}

function loadAuthenticatedUser(req, userData) {
  var provider = req.cookies['provider'];
  if(provider === 'ping') {
    return User.findOne({userName: userData.uid }, '-salt -password').exec();
  } else{
    return User.findById(userData._id).exec();
  }
}

/**
 * Checks if the user role meets the minimum requirements of the route
 */
export function hasRole(roleRequired) {
  if (!roleRequired) {
    throw new Error('Required role needs to be set');
  }

  return compose()
    .use(isAuthenticated())
    .use(function meetsRequirements(req, res, next) {
      if (config.userRoles.indexOf(req.user.role) >= config.userRoles.indexOf(roleRequired)) {
        next();
      } else {
        res.status(403).send('Forbidden');
      }
    });
}

/**
 * Returns a jwt token signed by the app secret
 */
export function signToken(id, role) {
  return jwt.sign({ _id: id, role: role }, config.secrets.session, {
    expiresIn: 60 * 60 * 5
  });
}

/**
 * Set token cookie directly for oAuth strategies
 */
export function setTokenCookie(req, res) {
  if (!req.user) {
    return res.status(404).send('It looks like you aren\'t logged in, please try again.');
  }
  var token = signToken(req.user._id, req.user.role);
  res.cookie('token', token);
  res.redirect('/');
}

/**
 * Select proper secret based on provider type (certificate or session secret)
 */
function secretCallback(req, payload, done){
  var provider = req.cookies['provider'];

  done(null, (provider && provider === 'ping') ? pingQACertificate : config.secrets.session);
}
