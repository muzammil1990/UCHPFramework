/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';
  declare var moment: any;

  class ListClaimJobsSearchFilterController {
    private listClaimJobsData: Object = null;
    private user: any = null;
    private claimsearchcriteria: Array < ClaimListSearchCriteriaModel >;
    private ClaimSearchCriteriadata: Array <any>;
    private selectedCompany: String;
    private selectCompanies: Array < any >;
    private claimSearchTypeKeyIndex: String;
    private chassiSerieMaxlength: string;
    private disableOpen: boolean = true;
    private disablePrint: boolean = true;
    private enableSubscription: any;
    private tabSearchSubscription: any;
    private companyChangeSubscription: any;
    private typeOfConcernListChangeSubscription: any;
    private isVpcOrVce: boolean = false;
    private isTSMAmericas: boolean = false;
    private DealerPattern: any;
    private typeOfConcernList: Array<any> = [];
    private selectedTypeOfConcern: Number;
    private listName: string;
    /**
     *
     *
     * @static
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    static $inject = [
      'StandardClaimSearchResultStore',
      'ClaimSearchCriteriaStore',
      'UserStore',
      'SelectCompaniesStore',
      'DispatcherService',
      'DateUtilService',
      '$translate',
      '$log',
      '$scope'
    ];

    /**
     * Creates an instance of ListClaimJobsSearchFilterController.
     *
     * @param {IStandardClaimSearchResultStore} StandardClaimSearchResultStore
     * @param {IClaimSearchCriteriaStore} ClaimSearchCriteriaStore
     * @param {IUserStore} UserStore
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {IDispatcherService} DispatcherService
     * @param {IDateUtilService} DateUtilService
     * @param {ng.translate.ITranslateService} $translate
     * @param {ng.ILogService} $log
     * @param {ng.IScope} $scope
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    constructor(
      private StandardClaimSearchResultStore: IStandardClaimSearchResultStore,
      private ClaimSearchCriteriaStore: IClaimSearchCriteriaStore,
      private UserStore: IUserStore,
      private SelectCompaniesStore: ISelectCompaniesStore,
      private DispatcherService: IDispatcherService,
      private DateUtilService: IDateUtilService,
      private $translate: ng.translate.ITranslateService,
      private $log: ng.ILogService,
      private $scope: ng.IScope
    ) {
      var that = this;
      that
        .UserStore
        .getUser()
        .then(function (userFromStore) {
          if (userFromStore !== null && userFromStore !== undefined) {
            that.user = userFromStore;
            _.forEach(that.user.typeOfConcernList, function(value) {
              that.typeOfConcernList.push({
                key: value,
                value: $translate.instant('TYPEOFCONCERN_.' + value)
              });
            });
          } else {
            // TODO: remove when userstore works
            that.user = {
              typeOfConcernList: [1, 2]
            };
            _.forEach(that.user.typeOfConcernList, function(value) {
              that.typeOfConcernList.push({
                key: value,
                value: $translate.instant('TYPEOFCONCERN_.' + value)
              });
            });
          }
          if (that.user.typeOfConcernList.length > 1) {
            that.typeOfConcernList.push({
              key: -1,
              value: $translate.instant('TYPEOFCONCERN_.ALL')
            });
            that.listClaimJobsForm.typeOfConcern = 1;
          } else {
            that.listClaimJobsForm.typeOfConcern = that.user.typeOfConcernList[0];
          }
        });
      that.ClaimSearchCriteriaStore.getClaimSearchCriteria()
        .then(function (ClaimSearchCriteriadata: Array < ClaimListSearchCriteriaModel >) {
          let typeOfClaimsAllAcc = 'typeOfClaimsAll';
          let typeOfClaimsWarrantyAcc = 'typeOfClaimsWarranty';
          let typeOfClaimsContractAcc = 'typeOfClaimsContract';
          let appealStatusesAcc = 'appealStatuses';
          let claimSearchTypesAcc = 'claimSearchTypes';
          let miPrintStatusesAcc = 'miPrintStatuses';
          let miTypesAcc = 'miTypes';


          let typeOfClaims: Array<Object> = ClaimSearchCriteriadata[typeOfClaimsAllAcc];
          let buffertypeOfClaimsTextHolder = { key: null, displayText: '\<Select type of claim\>' };

          ClaimSearchCriteriadata[typeOfClaimsAllAcc].unshift(buffertypeOfClaimsTextHolder);
          ClaimSearchCriteriadata[typeOfClaimsWarrantyAcc].unshift(buffertypeOfClaimsTextHolder);
          ClaimSearchCriteriadata[typeOfClaimsContractAcc].unshift(buffertypeOfClaimsTextHolder);
          that.ClaimSearchCriteriadata = ClaimSearchCriteriadata;
          // Default type of Claims - all
          that.ClaimSearchCriteriadata = ClaimSearchCriteriadata;
          that.claimsearchcriteria = typeOfClaims;
          // New BackEnd convention
          that.claimsearchcriteria[appealStatusesAcc] = ClaimSearchCriteriadata[appealStatusesAcc];
          that.claimsearchcriteria[claimSearchTypesAcc] = ClaimSearchCriteriadata[claimSearchTypesAcc];
          that.claimsearchcriteria[miPrintStatusesAcc] = ClaimSearchCriteriadata[miPrintStatusesAcc];
          that.claimsearchcriteria[miTypesAcc] = ClaimSearchCriteriadata[miTypesAcc];
          // Default for 'All'
          that.DispatcherService.publish('listclaimjobs:typeOfConcernListChange', {typeOfConcern: -1});
        });
      /**
       * Get initial company (used in translation keys)
       * */
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      // that.DealerPattern = that.selectedCompany !== '4' ? /^\d+$/ : /^[a-zA-Z0-9]+$/;
      that.DealerPattern = /^[a-zA-Z0-9]+$/;
      that.tabSearchSubscription = that.DispatcherService.subscribe('listclaimjobs:TabSearch',
        that.tabSearchHandler.bind(this));
      that.enableSubscription = that.DispatcherService.subscribe('listclaimjobs:EnableButtons',
        that.enableHandler.bind(this));

      that.SelectCompaniesStore.getSelectCompanies().then(function (selectCompanies: Array < {} >) {
        if (selectCompanies !== null && selectCompanies !== undefined) {
          that.selectCompanies = selectCompanies;
        }
      });

      that.isVpcOrVce = that.selectedCompany === '3' || that.selectedCompany === '6' || false;
      that.isTSMAmericas = that.selectedCompany === '4';
      that.chassiSerieMaxlength = that.selectedCompany === '3' ? '17' : '5';
      that.companyChangeSubscription = that.DispatcherService.subscribe('change:CompanyChanged',
        that.CompanyChangeHandler.bind(this));
      that.typeOfConcernListChangeSubscription = that.DispatcherService.subscribe('listclaimjobs:typeOfConcernListChange',
        that.changeTypeOfConcern.bind(this));

      that.$scope.$on('$stateChangeStart', function () {
        that.companyChangeSubscription.unsubscribe();
        that.enableSubscription.unsubscribe();
        that.typeOfConcernListChangeSubscription.unsubscribe();
      });
    }

    /**
     *
     *
     * @param {any} company
     * @returns {*}
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    CompanyChangeHandler(company): any {
      this.selectedCompany = company;
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    OpenSelectedClaimJobs() {
      var that = this;
      that.DispatcherService.publish('listclaimjobs:OpenSelectedClaimJobs' + that.listName, {});
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    ListPrintMiDocuments() {
      this.DispatcherService.publish('listclaimjobs:PrintMiDocuments', {});
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    typeOfConcernListChange(typeOfConcern) {
      this.DispatcherService.publish('listclaimjobs:typeOfConcernListChange', {typeOfConcern: typeOfConcern});
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    ListPrintDeliverNotes() {
      this.DispatcherService.publish('listclaimjobs:PrintDeliverNotes', {});
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    RemoveFromShipment() {
      this.DispatcherService.publish('listclaimjobs:RemoveFromShipment', {});
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    PrintDealerShippingDocument() {
      this.DispatcherService.publish('listclaimjobs:PrintDealerShippingDocument', {});
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    PrintHoldScrapList() {
      this.DispatcherService.publish('listclaimjobs:PrintHoldScrapList', {});
    }

    /**
     *
     *
     * @param {any} data
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    enableHandler(data) {
      this.disableOpen = !data.list;
      this.disablePrint = !data.mi || this.disableOpen;
      this.listName = data.name;
    }

    /**
     *
     *
     * @param {Object} DispatcherObject
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    changeTypeOfConcern(typeOfConcern) {
      this.selectedTypeOfConcern = typeOfConcern.typeOfConcern;
      let typeOfClaimsAcc = 'typeOfClaimsAll';
      switch (this.selectedTypeOfConcern) {
        case -1:
          typeOfClaimsAcc = 'typeOfClaimsAll';
          break;
        case 1:
          typeOfClaimsAcc = 'typeOfClaimsWarranty';
          break;
        case 2:
          typeOfClaimsAcc = 'typeOfClaimsContract';
          break;
        default:
          typeOfClaimsAcc = 'typeOfClaimsAll';
      }
      let typeOfClaims: Array<Object> = this.ClaimSearchCriteriadata[typeOfClaimsAcc];
      this.claimsearchcriteria.typeOfClaims = typeOfClaims;
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    datePickerOptions = this.DateUtilService.getDatePickerOptions();

    OnlyNumbers = /^\d+$/;

    AlphaNumeric = /^[a-zA-Z0-9]+$/;

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    isCollapsed = true;

    listClaimJobsForm = {
      'companyNo': null,
      'repairingimporter': null,
      'dealerno': null,
      'refno': null,
      'repairdatefrom': null,
      'repairdateto': null,
      'registrationdatefrom': null,
      'registrationdateto': null,
      'registrationnumber': null,
      'vin': null,
      'machidch': null,
      'machidchseries': null,
      'repairorderno': null,
      'typeofclaim': null,
      'followupcode': null,
      'causalpartprefix': null,
      'causalpartno': null,
      'functiongroup': null,
      'casesensitive': true,
      'claimsearchtypes': '4',
      'scc': null,
      'appealstatuses': '-1',
      'mitypes': '-1',
      'miprintstatuses': '-1',
      'printmilistdatefrom': null,
      'printmilistdateto': null,
      'midatefrom': null,
      'midateto': null,
      'typeOfConcern': 1
    };

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    SearchListClaimJobs() {
      var that = this;
      that.$log.debug('From SearchListClaimJobs');
      that.listClaimJobsData = {
        'companyNo': that.listClaimJobsForm.companyNo,
        'repairingImporter': that.listClaimJobsForm.repairingimporter,
        'repairingDealer': that.listClaimJobsForm.dealerno,
        'refNo': that.listClaimJobsForm.refno,
        'repairDateFrom': that.DateUtilService.DateParse(that.listClaimJobsForm.repairdatefrom),
        'repairDateTo': that.DateUtilService.DateParse(that.listClaimJobsForm.repairdateto),
        'registrationDateFrom': that.DateUtilService.DateParse(that.listClaimJobsForm.registrationdatefrom),
        'registrationDateTo': that.DateUtilService.DateParse(that.listClaimJobsForm.registrationdateto),
        'registrationNumber': that.listClaimJobsForm.registrationnumber,
        'vin': that.listClaimJobsForm.vin,
        'chassisNo': that.listClaimJobsForm.machidch,
        'chassisSeries': that.listClaimJobsForm.machidchseries,
        'repairOrderNo': that.listClaimJobsForm.repairorderno,
        'typeOfClaim': that.listClaimJobsForm.typeofclaim,
        'followUpCode': that.listClaimJobsForm.followupcode,
        'causalPartPrefix': that.listClaimJobsForm.causalpartprefix,
        'causalPartNo': that.listClaimJobsForm.causalpartno,
        'functionGroup': that.listClaimJobsForm.functiongroup,
        'caseSensitive': that.listClaimJobsForm.casesensitive,
        'claimSearchType': that.listClaimJobsForm.claimsearchtypes,
        'scc': that.listClaimJobsForm.scc,
        'appealStatuses': that.listClaimJobsForm.appealstatuses,
        'typeOfConcern': that.listClaimJobsForm.typeOfConcern
      };

      let miTypesAccessor = 'miTypes';
      let miPrintStatusesAccessor = 'miPrintStatuses';
      let miPrintStatusDateFromAccessor = 'miPrintStatusDateFrom';
      let miPrintStatusDateToAccessor = 'miPrintStatusDateTo';
      let materialInstrDateFromAccessor = 'materialInstrDateFrom';
      let materialInstrDateToAccessor = 'materialInstrDateTo';

      if (that.listClaimJobsForm.claimsearchtypes === '2') {
        that.listClaimJobsData[miTypesAccessor] = that.listClaimJobsForm.mitypes;
        that.listClaimJobsData[miPrintStatusesAccessor] = that.listClaimJobsForm.miprintstatuses;
        that.listClaimJobsData[miPrintStatusDateFromAccessor] = that.DateUtilService.DateParse(that.listClaimJobsForm.printmilistdatefrom);
        that.listClaimJobsData[miPrintStatusDateToAccessor] = that.DateUtilService.DateParse(that.listClaimJobsForm.printmilistdateto);
        that.listClaimJobsData[materialInstrDateFromAccessor] = that.DateUtilService.DateParse(that.listClaimJobsForm.midatefrom);
        that.listClaimJobsData[materialInstrDateToAccessor] = that.DateUtilService.DateParse(that.listClaimJobsForm.midateto);
      }
      that.claimSearchTypeKeyIndex = that.listClaimJobsForm.claimsearchtypes;
      console.log('that.claimSearchTypeKeyIndex ::: ' + that.claimSearchTypeKeyIndex);
      // searchFormData: any, claimSearchTypeIndex: any, tabjobtype: any, pagination: any
      that.StandardClaimSearchResultStore.getClaimSearchResult(that.listClaimJobsData, that.claimSearchTypeKeyIndex, {});
    }

    /**
     *
     *
     *
     * @memberOf ListClaimJobsSearchFilterController
     */
    tabSearchHandler() {
      this.SearchListClaimJobs();
    }
  }
  angular.module('uchpClientAngularApp')
    .component('listClaimJobsSearchFilter', {
      templateUrl: 'components/claim/claims/list-claim-jobs/list-claim-jobs-search-filter/list-claim-jobs-search-filter.html',
      controller: [
        'StandardClaimSearchResultStore',
        'ClaimSearchCriteriaStore',
        'UserStore',
        'SelectCompaniesStore',
        'DispatcherService',
        'DateUtilService',
        '$translate',
        '$log',
        '$scope',
        ListClaimJobsSearchFilterController
      ],
      controllerAs: 'ctrl'
    });
}
