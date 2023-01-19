/**
 * Main application file
 */

'use strict';

import express from 'express';
// import mongoose from 'mongoose';
// mongoose.Promise = require('bluebird');
import config from './config/environment';
import http from 'http';
import configExpress from './config/express';
import appRoutes from './routes';
import fs from 'fs';
import https from 'https';


var httpsOptions = {
  key: fs.readFileSync('./server/auth/certificate/pos.key'),
  cert: fs.readFileSync('./server/auth/certificate/pos.crt')
};

// Connect to MongoDB
/*
mongoose.connect(config.mongo.uri, config.mongo.options);
mongoose.connection.on('error', function(err) {
  console.error('MongoDB connection error: ' + err);
  process.exit(-1);
});
*/

// Populate databases with sample data
if (config.uchpDB) {
  require('./config/uchpDB');
}

// Setup server
var app = express();
var server = http.createServer(app);
var httpsServer = https.createServer(httpsOptions, app);
configExpress(app);
appRoutes(app);

// Start server
function startServer() {
  app.angularFullstackHttp = server.listen(config.port, config.ip, function() {
    console.log('Express server listening on %d, in %s mode', config.port, app.get('env'));
  });

  app.angularFullstack = httpsServer.listen(config.portHttps, config.ip, function() {
    console.log('Express HTTPS server listening on %d, in %s mode', config.portHttps, app.get('env'));
  });
}

setImmediate(startServer);

// Expose app
exports = module.exports = app;
