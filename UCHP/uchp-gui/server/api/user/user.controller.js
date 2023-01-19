'use strict';

import User from './user.model';
import Usersetting from '../usersetting/usersetting.model';
import Usergroup from '../usergroup/usergroup.model';
import passport from 'passport';
import config from '../../config/environment';
import jwt from 'jsonwebtoken';

function validationError(res, statusCode) {
  statusCode = statusCode || 422;
  return function(err) {
    res.status(statusCode).json(err);
  }
}

function handleError(res, statusCode) {
  statusCode = statusCode || 500;
  return function(err) {
    res.status(statusCode).send(err);
  };
}

/**
 * Get list of users
 * restriction: 'admin'
 */
export function index(req, res) {
  var userName = req.query.username;
  console.log('index');
  if (userName != null){
    Usersetting.find().exec()
      .then(usersetting => {
        Usergroup.find().exec()
          .then(usergroup => {
            return User.findOne({ userName: userName }, '-salt -password').exec()
              .then(user => {
                if (!user) {
                  console.log('------is it here ? -------');
                  return res.status(401).end();
                }
                user['usersetting'] = usersetting;
                user['usergroup'] = usergroup;
                res.json(user);
              })
              .catch(err =>  next(err)); //log error
          });
      });
  }  else {
    return User.find({}, '-salt -password').exec()
      .then(users => {
        res.status(200).json(users);
      })
      .catch(handleError(res));
  }
}

/**
 * Creates a new user
 */
export function create(req, res, next) {
  var newUser = new User(req.body);
  newUser.provider = 'local';
  newUser.role = 'user';
  newUser.save()
    .then(function(user) {
      var token = jwt.sign({ _id: user._id }, config.secrets.session, {
        expiresIn: 60 * 60 * 5
      });
      res.json({ token });
    })
    .catch(validationError(res));
}

/**
 * Get a single user
 */
export function show(req, res, next) {
  var userId = req.params.id;
  console.log('------is it here in show ? -------');
  return User.findById(userId).exec()
    .then(user => {
      if (!user) {
        return res.status(404).end();
      }
      res.json(user.profile);
    })
    .catch(err => next(err));
}

/**
 * Deletes a user
 * restriction: 'admin'
 */
export function destroy(req, res) {
  return User.findByIdAndRemove(req.params.id).exec()
    .then(function() {
      res.status(204).end();
    })
    .catch(handleError(res));
}

/**
 * Change a users password
 */
export function changePassword(req, res, next) {
  var userId = req.user._id;
  var oldPass = String(req.body.oldPassword);
  var newPass = String(req.body.newPassword);

  return User.findById(userId).exec()
    .then(user => {
      if (user.authenticate(oldPass)) {
        user.password = newPass;
        return user.save()
          .then(() => {
            res.status(204).end();
          })
          .catch(validationError(res));
      } else {
        return res.status(403).end();
      }
    });
}

/**
 * Get my info
 * Precondition: we have always authenticated user
 */
export function me(req, res, next) {
  console.log('------is it here me function ? -------');
  return User.findOne({_id: req.user._id }, '-salt -password').exec()
    .then(user => { // don't ever give out the password or salt
      if (!user) {
        return res.status(401).end();
      }
      res.json(user);
    })
    .catch(err => next(err));
}
