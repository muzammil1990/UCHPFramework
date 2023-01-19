/**
 * Created by a038509 on 21-09-2016.
 */
module uchpClientAngularApp {
  'use strict';

  export interface INewClaimJobStore {
    getNewClaimJobData(company): ng.IPromise<{}>;
    postNewClaimJobData(selectedClaimJobType, searchFormData): ng.IPromise<{}>;
    getCurrentClaimJob();
  }
}
