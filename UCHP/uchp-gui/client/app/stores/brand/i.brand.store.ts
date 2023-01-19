/**
 * Created by a148094 on 27-04-2016.
 */
/// <reference path="../../_all.ts" />
module uchpClientAngularApp {
  'use strict';

  export interface IBrandStore {
    getBrandList(targetService): ng.IPromise<{}>;
  }
}
