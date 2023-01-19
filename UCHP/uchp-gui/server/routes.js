/**
 * Main application routes
 */

'use strict';

var errors = require('./components/errors');
var path = require('path');
var proxy = require('http-proxy-middleware');

module.exports = function(app) {

  //uchp-test1.got.volvo.net
  app.use('/uchp/api', proxy({target: 'http://131.97.103.85:9087/', changeOrigin: true}));

  //uchp-dt8.got.volvo.net
  //app.use('/uchp/api', proxy({target: 'http://131.97.103.85:9140/', changeOrigin: true}));

  //jBoss bridge
  // app.use('/uchp/api', proxy({target: 'http://localhost:8080/', changeOrigin: true}));


  // Old MongoDB
  /*app.use('/uchp/api/api/typeOfClaims', require('./api/typeofclaims'));
  app.use('/uchp/api/api/claim/advancedsearchclaims/advancedSearch', require('./api/advancedsearch'));
  app.use('/uchp/api/api/claim/advancedsearchclaims/advancedSearchCriteria', require('./api/advancedsearchcriteria'));
  app.use('/uchp/api/api/tmastatuses', require('./api/tmaStatuses'));
  app.use('/uchp/api/api/claimjobstatuses', require('./api/claimjobstatuses'));
  app.use('/uchp/api/api/claim/vehicle/searchVehicleCampaignList', require('./api/searchvehiclecampaignlist'));
  app.use('/uchp/api/api/apppealstatuses', require('./api/appealstatuses'));
  app.use('/uchp/api/api/miprintstatuses', require('./api/miprintstatuses'));
  app.use('/uchp/api/api/mitypes', require('./api/mitypes'));
  app.use('/uchp/api/api/claimjobtypes', require('./api/claimjobtypes'));
  app.use('/uchp/api/api/claimsearchtypes', require('./api/claimsearchtypes'));
  app.use('/uchp/api/api/claim/claims/claimSearchCriteria', require('./api/claimlistsearchcriteria'));
  app.use('/uchp/api/api/claim/claims/standardClaimSearchResult', require('./api/standardclaimsearchresult'));
  app.use('/uchp/api/api/claim/claims/tabClaimSearchResult', require('./api/standardclaimsearchresult'));
  app.use('/uchp/api/api/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments', require('./api/createoutstandingclaimsummarydealerdocument'));
  app.use('/uchp/api/api/claim/vehicle/coverages', require('./api/vehicleinformation'));
  app.use('/uchp/api/api/claim/vehicle/repairhistory', require('./api/vehicleinformation'));
  app.use('/uchp/api/api/crediting/creditdocuments/brandlist', require('./api/brand'));
  app.use('/uchp/api/api/claim/vehicle/brandlist', require('./api/brand'));
  app.use('/uchp/api/api/crediting/creditdocuments/contenttype', require('./api/contenttype'));
  app.use('/uchp/api/api/crediting/creditdocuments/creditingdocuments', require('./api/creditdocument'));
  app.use('/uchp/api/api/crediting/creditdocuments/outstandingclaimsummarydealerdocuments', require('./api/creditdocument'));



  app.use('/uchp/api/api/common/user/usercontext/', require('./api/user'));
  app.use('/uchp/api/api/usergroup/', require('./api/usergroup'));
  app.use('/uchp/api/api/usersetting/', require('./api/usersetting'));
  app.use('/uchp/api/api/auth', require('./auth'));

  app.use('/uchp/api/api/claim/manageclaim/existingclaim/1/1/', require('./api/manageclaim'));
  app.use('/uchp/api/api/claim/manageclaim/existingclaim/1/1/1/1/tabs/', require('./api/claimjob'));

  app.use('/uchp/api/api/claim/manageclaim/preparenewclaim', require('./api/preparenewclaim'));
  // Mock for geting 200 POST response
  app.use('/uchp/api/api/claim/manageclaim/preparenewclaimjob', require('./api/preparenewclaimjob'));
  app.use('/uchp/api/api/claim/manageclaim/operations', require('./api/operations'));
  app.use('/uchp/api/api/claim/manageclaim/returncodes', require('./api/returncodes'));
  app.use('/uchp/api/api/claim/manageclaim/defectcodes', require('./api/defectcodes'));
*/
  // All undefined asset or api routes should return a 404
  app.route('/:url(api|auth|components|app|bower_components|assets)/*')
    .get(errors[404]);

  // All other routes should redirect to the index.html
  app.route('/*')
    .get(function(req, res) {
      res.sendFile(path.resolve(app.get('appPath') + '/index.html'));
    });
};
