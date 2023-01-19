/// <reference path="../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  class ViewCampaignController {
    private user: User = null;
    private showView: Boolean = false;
    constructor(
      private $log: ng.ILogService,
      private UserStore: IUserStore,
      private $state: any
    ) {
      var that = this;
      that.$log.debug('ViewRepairHistoryController Initialized');
      that
        .UserStore
        .getUser()
        .then(function (userFromStore) {
          if (userFromStore !== null && userFromStore !== undefined) {
            that.user = userFromStore;
            that.checkIfUserIsPrivileged();
          } else {
            that.checkIfUserIsPrivileged();
          }
        });
    }

    checkIfUserIsPrivileged(): any {
      var that = this;
      if (that.isPrivileged()) {
        that.showView = true;
      } else {
        that.$state.go('app');
      }
    }

    /**
     * Check if user is logged in and a feature should be shown, based on privileges
     * */
    isPrivileged(): boolean {
      // var that = this;
      return true; // that.user && that.user.privilegeList.indexOf(that.privilege) !== -1;
    }
  }

  angular.module('uchpClientAngularApp')
    .component('viewCampaign', {
      templateUrl: 'components/claim/vehicle/view-campaign/view-campaign.html',
      controller: [ '$log', 'UserStore', ViewCampaignController ],
      controllerAs: 'ctrl'
    });
}
