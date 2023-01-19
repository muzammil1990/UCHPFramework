/// <reference path="../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  export interface IVehicleInformationStore {
    getVehicleInformation(vehicleSearchData, isService): ng.IPromise<{}>;
    getviewCoverageDocument(coverageId, coverageAttachmentId): any;
    getMultipleVehicleInformations(chassisNo, chassisSeries, isService): any;
  }
}
