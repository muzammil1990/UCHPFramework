/// <reference path="../../../../../client/app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  export interface IViewCampaignStore {
    getViewCampaign(viewCampaignFormData): ng.IPromise<{}>;
  }

}
