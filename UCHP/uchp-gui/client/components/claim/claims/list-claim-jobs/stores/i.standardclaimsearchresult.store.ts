/**
 * Created by a148094 on 11-08-2016.
 */
/// <reference path="../../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  export interface IStandardClaimSearchResultStore {
    getClaimSearchResult(searchFormData: any, claimSearchTypeIndex: any, pagination: any): ng.IPromise<{}>;
  }
}
