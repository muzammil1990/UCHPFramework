/// <reference path="../../../../../app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  export interface IClaimJobItemStore {
    getClaimJob(searchData, tab): ng.IPromise<{}>;
  }
}
