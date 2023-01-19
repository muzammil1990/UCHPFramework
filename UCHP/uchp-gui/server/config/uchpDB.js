'use strict';
/* // Uncomment to get MongoDB
import CreditDocument from '../api/creditdocument/creditdocument.model';
import Contenttype from '../api/contenttype/contenttype.model';
import Brand from '../api/brand/brand.model';
import Coverage from '../api/viewcoverage/viewcoverage.model';
import Repairhistory from '../api/viewrepairhistory/viewrepairhistory.model';
import VechicleInformation from '../api/vehicleinformation/vehicleinformation.model';
import User from '../api/user/user.model';
import Usergroup from '../api/usergroup/usergroup.model';
import Usersetting from '../api/usersetting/usersetting.model';
import Claimsearchtypes from '../api/claimsearchtypes/claimsearchtypes.model';
import Claimjobtypes from '../api/claimjobtypes/claimjobtypes.model';
import Typeofclaims from '../api/typeofclaims/typeofclaims.model';
import Mitypes from '../api/mitypes/mitypes.model';
import Miprintstatuses from '../api/miprintstatuses/miprintstatuses.model';
import Appealstatuses from '../api/appealstatuses/appealstatuses.model';
import Claimlistsearchcriteria from '../api/claimlistsearchcriteria/claimlistsearchcriteria.model';
import Claimjob from '../api/claimjob/claimjob.model';
import Preparenewclaim from '../api/preparenewclaim/preparenewclaim.model';
import Manageclaim from '../api/manageclaim/manageclaim.model';
import Standardclaimsearchresult from '../api/standardclaimsearchresult/standardclaimsearchresult.model';
import Claimsearchresult from '../api/claimsearchresult/claimsearchresult.model';
import Searchvehiclecampaignlist from '../api/searchvehiclecampaignlist/searchvehiclecampaignlist.model';
import TmaStatuses from '../api/tmaStatuses/tmaStatuses.model';
import Claimjobstatuses from '../api/claimjobstatuses/claimjobstatuses.model';
import Advancedsearchcriteria from '../api/advancedsearchcriteria/advancedsearchcriteria.model';
import Advancedsearch from '../api/advancedsearch/advancedsearch.model';
import Operations from '../api/operations/operations.model';
import Returncodes from '../api/returncodes/returncodes.model';
import Defectcodes from '../api/defectcodes/defectcodes.model';


import jsonCreditDocumentData from '../data/creditDocuments.json';
import jsonContenttypeData from '../data/contentType.json';
import jsonBrandData from '../data/brand.json';
import jsonViewCoverageData from '../data/vehicleinformationviewcoverage.json';
import jsonViewRepairHistoryData from '../data/vehicleinformationviewrepairhistory.json';
import jsonVechicleInformationData from '../data/vehicleinformation.json';
import jsonUserData from '../data/users.json';
import jsonUsergroupData from '../data/usergroups.json';
import jsonUsersettingData from '../data/usersettings.json';
import jsonClaimsearchtypesData from '../data/claimsearchtypes.json';
import jsonClaimjobtypesData from '../data/claimjobtypes.json';
import jsonTypeofclaimsData from '../data/typeofclaims.json';
import jsonMitypesData from '../data/mitypes.json';
import jsonMiprintstatusesData from '../data/miprintstatuses.json';
import jsonAppealstatusesData from '../data/appealstatuses.json';
import jsonClaimlistsearchcriteriaData from '../data/claimsearchcriteria.json';
import jsonClaimjobData from '../data/claimjob.json';
import jsonPreparenewclaimData from '../data/preparenewclaim.json';
import jsonManageclaimData from '../data/manageclaim.json';
import jsonStandardclaimsearchresult from '../data/standardclaimsearchresult.json';
import jsonClaimsearchresult from '../data/claimsearchresult.json';
import jsonSearchvehiclecampaignlistData from '../data/searchvehiclecampaignlist.json';
import jsonTmaStatusesData from '../data/tmastatuses.json';
import jsonClaimjobstatusesData from '../data/claimjobstatuses.json';
import jsonAdvancedsearchcriteriaData from '../data/advancedsearchcriteria.json';
import jsonAdvancedsearchData from '../data/advancedsearch.json';
import jsonOperations from '../data/operations.json';
import jsonReturncodes from '../data/returncodes.json';
import jsonDefectcodes from '../data/defectcodes.json';


CreditDocument.find({}).remove()
 .then(() => {
  CreditDocument.create(jsonCreditDocumentData);
 });

Contenttype.find({}).remove()
 .then(() => {
   Contenttype.create(jsonContenttypeData);
 });

Brand.find({}).remove()
 .then(() => {
   Brand.create(jsonBrandData);
 });

Coverage.find({}).remove()
  .then(() => {
    Coverage.create(jsonViewCoverageData);
  });
Repairhistory.find({}).remove()
  .then(() => {
    Repairhistory.create(jsonViewRepairHistoryData);
  });

VechicleInformation.find({}).remove()
  .then(() => {
  VechicleInformation.create(jsonVechicleInformationData);
});

User.find({}).remove()
  .then(() => {
    User.create(jsonUserData);
});

Usergroup.find({}).remove()
  .then(() => {
    Usergroup.create(jsonUsergroupData);
  });

Usersetting.find({}).remove()
  .then(() => {
    Usersetting.create(jsonUsersettingData);
  });

Claimsearchtypes.find({}).remove()
  .then(() => {
    Claimsearchtypes.create(jsonClaimsearchtypesData);
  });

Claimjobtypes.find({}).remove()
  .then(() => {
    Claimjobtypes.create(jsonClaimjobtypesData);
  });

Typeofclaims.find({}).remove()
  .then(() => {
    Typeofclaims.create(jsonTypeofclaimsData);
  });

Mitypes.find({}).remove()
  .then(() => {
    Mitypes.create(jsonMitypesData);
  });

Miprintstatuses.find({}).remove()
  .then(() => {
    Miprintstatuses.create(jsonMiprintstatusesData);
  });

Appealstatuses.find({}).remove()
  .then(() => {
    Appealstatuses.create(jsonAppealstatusesData);
  });

Claimlistsearchcriteria.find({}).remove()
  .then(() => {
    Claimlistsearchcriteria.create(jsonClaimlistsearchcriteriaData);
  });

Claimjob.find({}).remove()
  .then(() => {
    Claimjob.create(jsonClaimjobData);
  });

Preparenewclaim.find({}).remove()
  .then(() => {
  Preparenewclaim.create(jsonPreparenewclaimData);
});

Manageclaim.find({}).remove()
  .then(() => {
    Manageclaim.create(jsonManageclaimData);
  });

Standardclaimsearchresult.find({}).remove()
  .then(() => {
    Standardclaimsearchresult.create(jsonStandardclaimsearchresult);
  });

Claimsearchresult.find({}).remove()
  .then(() => {
    Claimsearchresult.create(jsonClaimsearchresult);
  });

Searchvehiclecampaignlist.find({}).remove()
  .then(() => {
    Searchvehiclecampaignlist.create(jsonSearchvehiclecampaignlistData);
  });

TmaStatuses.find({}).remove()
  .then(() => {
  TmaStatuses.create(jsonTmaStatusesData);
});

Claimjobstatuses.find({}).remove()
  .then(() => {
  Claimjobstatuses.create(jsonClaimjobstatusesData);
});

Advancedsearchcriteria.find({}).remove()
  .then(() => {
  Advancedsearchcriteria.create(jsonAdvancedsearchcriteriaData);
});

Advancedsearch.find({}).remove()
  .then(() => {
    Advancedsearch.create(jsonAdvancedsearchData);
});

Operations.find({}).remove()
  .then(() => {
    Operations.create(jsonOperations);
});

Returncodes.find({}).remove()
  .then(() => {
    Returncodes.create(jsonReturncodes);
});

Defectcodes.find({}).remove()
  .then(() => {
    Defectcodes.create(jsonDefectcodes);
  });
*/
