/**
 * Created by a038509 on 2016-08-26.
 */
module uchpClientAngularApp {
  'use strict';

  // moment is a globally available variable
  declare var moment: any;


class NewClaimJobController {
    private newClaimJobData: any;
    private newClaimForm: any;
    // private companyTextValue: String;
    private user: User = null;
    // private privilege: String = 'CanCreateClaimJobs';
    private showView: Boolean = false;
    private brandlist: Array < BrandModel >;
    private selectedClaimJobType: String;
    private selectedCompany: String;
    private selectCompanies: Array < any >;
    private DealerPattern: any;
    private postClaimSubscription: any;
    private fileUploadSubscription: any;
    private activeTab: any = 0;

    static $inject = [
      'NewClaimJobStore',
      'BrandStore',
      'UserStore',
      'SelectCompaniesStore',
      '$log',
      'DispatcherService',
      'NewClaimService',
      '$state',
      '$translate',
      '$scope'
    ];

    constructor(private NewClaimJobStore: INewClaimJobStore,
                private BrandStore: IBrandStore,
                private UserStore: IUserStore,
                private SelectCompaniesStore: ISelectCompaniesStore,
                private $log: ng.ILogService,
                private DispatcherService: IDispatcherService,
                private NewClaimService: INewClaimService,
                private $state: any,
                private $translate: ng.translate.ITranslateService,
                private $scope: ng.IScope) {
      $log.debug('claimJobHeaderController Initialized');

      var that = this;
      that.checkIfUserIsPrivileged();
      that.selectedClaimJobType = '';
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
      that
        .BrandStore
        .getBrandList('newClaimJob')
        .then(function (BrandList: Array < BrandModel >) {
          var text = that.$translate.instant('SELECT.MAKE');
          BrandList.unshift({
            'key': '',
            'displayText': text
          });
          that.brandlist = BrandList;
          // that.companyTextValue = that.newClaimJobData.claimRepairHeaderDTO.companyNo.toString();
        });
      that
        .SelectCompaniesStore
        .getSelectCompanies()
        .then(function (selectCompanies: Array < {} >) {
          if (selectCompanies !== null && selectCompanies !== undefined) {
            that.selectCompanies = selectCompanies;
          }
        });
      that.postClaimSubscription = that.DispatcherService.subscribe('newclaimjob:PostClaim',
        that.postClaimHandler.bind(this));

      that.fileUploadSubscription = that.DispatcherService.subscribe('Attachment:fileupload',
        that.fileUploadHandler.bind(this));
      /**
       * Get initial company (used in translation keys)
       * */
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      that.NewClaimJobStore.getNewClaimJobData(that.selectedCompany)
        .then(function (newClaimJobForm: NewClaimJobModel) {
          // TODO: fix 'bug' with initial date value don't showing up
          that.newClaimJobData = newClaimJobForm;
          if (!_.isUndefined(that.newClaimJobData)) {
            if (!_.isUndefined(that.newClaimJobData.claimRepairHeaderDTO)) {
              if (!_.isUndefined(that.newClaimJobData.claimRepairHeaderDTO.repairDate)) {
                that.newClaimJobData.claimRepairHeaderDTO.repairDate = that.newClaimJobData.claimRepairHeaderDTO.repairDate || null;
              }
            }
            that.newClaimJobData.claimJobTypes.unshift({
              key: '',
              displayText: $translate.instant('SELECT_CLAIM_JOBTYPE')
            });
            that.selectedClaimJobType = '';
          }
          console.log(that.newClaimJobData);
        });
      // that.DealerPattern = that.selectedCompany !== '4' ? /^\d+$/ : /^[a-zA-Z0-9]+$/;
      that.DealerPattern = /^[a-zA-Z0-9]+$/;
    }
    // Set options for the datepickers
    // API here: http://eonasdan.github.io/bootstrap-datetimepicker/Options/
    datePickerOptions: Object = {
      format: 'DD/MM/YY',
      maxDate: moment(),
      useCurrent: true,
      locale: moment.locale(),
      allowInputToggle: true
    };

    checkIfUserIsPrivileged(): any {
      var that = this;
      if (that.isPrivileged()) {
        that.showView = true;
      } else {
        that.$state.go('app');
      }
    }

    fileUploadHandler(data) {
     console.log(data);
    }

    postClaimHandler() {
      this.activeTab = 1;
    }
    /**
     * Check if a feature should be shown, based on privileges
     * */
    isPrivileged(): boolean {
      // var that = this;
      return true; // that.user && that.user.privilegeList.indexOf(that.privilege) !== -1;
    }

    postForm() {
      var that = this;
      that.$log.debug(that.selectedClaimJobType, that.newClaimJobData);
      var formData = that.NewClaimService.get(that.newClaimJobData.claimRepairHeaderDTO);
      that.NewClaimJobStore.postNewClaimJobData(that.selectedClaimJobType, formData)
        .then(function (response) {
          that.resetForm();
          that.DispatcherService.publish('newclaimjob:PostClaim', response);
        });
    }

    resetForm(): any {
      // Reset the form model.
      this.newClaimJobData = {};
      this.newClaimForm.$setPristine();
      this.newClaimForm.$setUntouched();
    }
  }

  angular.module('uchpClientAngularApp')
    .component('newClaimJobForm', {
      templateUrl: 'components/claim/claims/new-claim-job/new-claim-job-form/new-claim-job-form.html',
      controller: [
        'NewClaimJobStore',
        'BrandStore',
        'UserStore',
        'SelectCompaniesStore',
        '$log',
        'DispatcherService',
        'NewClaimService',
        '$state',
        '$translate',
        '$scope',
        NewClaimJobController
      ],
      controllerAs: 'ctrl'
    });
}
