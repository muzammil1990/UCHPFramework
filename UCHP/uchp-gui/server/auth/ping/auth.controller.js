'use strict';

var _ = require('lodash');
var contra = require('contra');
var User = require('../../api/user/user.model');
var InternalError = require('../../errors/internal.error');
var Cookies = require('cookies');
var jwt = require('jsonwebtoken');
var config = require('../../config/environment');



 /**
  * #8
  * Attach cookie with token to the request
  * */
 var setTokenCookie = function(req, res, next){
   console.log('set token cookie');
   var cookies = new Cookies(req, res, null);
   var signedToken = signToken(req.user.accessToken);  //OAuth provider can send token expiration time, we can use it to set cookie expiration
   cookies.set('token', JSON.stringify(signedToken), {httpOnly: false});
   res.json(req.user);
 }


 function signToken(id) {
   console.log('signToken ', id);
   return jwt.sign({_id: id}, config.secrets.session, {expiresInMinutes: 60});
 }

 function handleError(res, err) {
   return res.send(500, err);
 }


/**
* path: /auth/ping/callback
* operations:
*   -  httpMethod: GET
*      summary: PingFederate callback endpoint
*      nickname:
*      produces:
*       - application/json
*/
exports.handleCallback = function (req, res, next) {
  console.log('#5 in the /auth/ping/callback');
  setTokenCookie(req,res, next);
 };

/**
 * Find user in database and synchronize database records with Oauth profile provider.
 */
exports.syncUser = function(userObj, refreshToken, callback){
  contra.waterfall([
      //finds given user
      function (next) {
        User.findById(userObj._id, function (err, user) {
          next(err, user);
        });
      },
      function (user, next) { // if user exists update him/her
        if (user) {
          user.accessToken = userObj.accessToken;
          user.refreshToken = refreshToken;
          user.firstName = userObj.firstName;
          user.lastName = userObj.lastName;
          user.street = userObj.street;
          user.postalAddress = userObj.postalAddress;
          user.postalCode = userObj.postalCode;
          user.emailAddress = userObj.emailAddress;
          user.officePhoneNumber = userObj.officePhoneNumber;
          user.mobilePhoneNumber = userObj.mobilePhoneNumber;
          user.roles = user.roles;

          user.save(function (err) {
            next(err, userObj);
          });
        } else {

          User.create(userObj, function (err) {
            next(err, userObj);
          });
        }
      }],
      function (err, user) {
        callback(user, err);
      }
    );

}
