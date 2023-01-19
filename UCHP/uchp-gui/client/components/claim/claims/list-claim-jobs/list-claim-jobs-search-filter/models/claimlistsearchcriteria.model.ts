/**
 * Created by a148094 on 17-01-2017.
 */

/// <reference path="../../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  export class ClaimListSearchCriteriaModel {
    claimSearchTypes: Array<[ClaimSearchTypesModel]>;
    typeOfClaims: Array<[TypeOfClaimsModel]>;
    miTypes: Array<[MiTypesModel]>;
    miPrintStatuses: Array<[MiPrintStatusesModel]>;
    appealStatuses: Array<[AppealStatusesModel]>;
  }
}
