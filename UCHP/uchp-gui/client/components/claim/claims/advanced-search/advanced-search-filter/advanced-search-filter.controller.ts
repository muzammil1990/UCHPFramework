/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  class AdvancedSearchFilterController {
  //  private user: User = null;
  //  private showView: Boolean = false;
    private selectedCompany: String;
    private advancedsearchData: Object = null;
    private advancedSearchForm: any;
    private chassiSerieMaxlength: string;
    private isVpcOrVce: boolean = false;
    private advancedsearchcriteria: Array < AdvancedSearchCriteriaModel >;
    private selectCompanies: Array < any >;

    static $inject = [
      'AdvancedSearchStore',
      'AdvancedSearchCriteriaStore',
      'SelectCompaniesStore',
      'DispatcherService',
      'DateUtilService',
      '$log',
      '$scope',
      'UserStore',
      '$state',
      '$translate'

    ];
    constructor(
                private AdvancedSearchStore: IAdvancedSearchStore,
                private AdvancedSearchCriteriaStore: IAdvancedSearchCriteriaStore,
                private SelectCompaniesStore: ISelectCompaniesStore,
                private DispatcherService: IDispatcherService,
                private DateUtilService: IDateUtilService,
                private $log: ng.ILogService,
                private $scope: ng.IScope,
                private UserStore: IUserStore,
                private $state: any,
                private $translate: ng.translate.ITranslateService) {
      var that = this;
      console.log('Advanced Search Filter Controller ...');

      that.chassiSerieMaxlength = that.selectedCompany === '3' ? '17' : '5';
      that.isVpcOrVce = that.selectedCompany === '3' || that.selectedCompany === '6' || false;
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      that
        .SelectCompaniesStore
        .getSelectCompanies()
        .then(function (selectCompanies: Array < {} >) {
          if (selectCompanies !== null && selectCompanies !== undefined) {
            that.selectCompanies = selectCompanies;
          }
        });

      that.AdvancedSearchCriteriaStore.getAdvancedSearchCriteria()
        .then(function (AdvancedSearchCriteriadata: Array < AdvancedSearchCriteriaModel >) {
              that.advancedsearchcriteria = AdvancedSearchCriteriadata;
        });

    }

    OnlyNumbers = /^\d+$/;

    datePickerOptions = this.DateUtilService.getDatePickerOptions();

    advancedSearchForm = {
      'causalPartName': null,
      'causalPartNameCaseSensitive': false,
      'causalPartNameInc': false,
      'causalPartNo': null,
      'causalPartNoCaseSensitive': false,
      'causalPartNoInc': false,
      'causalPartPrefix': null,
      'causalPartPrefixCaseSensitive': false,
      'causalPartPrefixInc': false,
      'causalPartSerial': null,
      'cause': null,
      'causeInc': false,
      'chassisNoFrom': null,
      'chassisNoInc': false,
      'chassisNoTo': null,
      'chassisSeries': null,
      'chassisSeriesCaseSensitive': false,
      'chassisSeriesInc': false,
      'claimJobStatuses': '-1',
      'claimJobStatusInc': false,
      'companyNo': null,
      'companyNoInc': false,
      'complaint': null,
      'complaintInc': false,
      'correction': null,
      'correctionInc': false,
      'creditedDateFrom': null,
      'creditedDateInc': false,
      'creditedDateTo': null,
      'currency': null,
      'currencyInc': false,
      'defectCode': null,
      'defectCodeInc': false,
      'deliveryDateFrom': null,
      'deliveryDateInc': false,
      'deliveryDateTo': null,
      'followUpCodeFrom': null,
      'followUpCodeTo': null,
      'followUpCodeInc': false,
      'forced': false,
      'forcedInc': false,
      'functionGroupFrom': null,
      'functionGroupInc': false,
      'functionGroupTo': null,
      'jobNo': null,
      'jobNoInc': false,
      'labourHoursFrom': null,
      'labourHoursTo': null,
      'labourHoursInc': false,
      'labourNetTotalFrom': null,
      'labourNetTotalInc': false,
      'labourNetTotalTo': null,
      'marketType': null,
      'marketTypeCaseSensitive': false,
      'marketTypeInc': false,
      'miDateFrom': null,
      'miDateInc': false,
      'miDateTo': null,
      'materialNetTotalFrom': null,
      'materialNetTotalInc': false,
      'materialNetTotalTo': null,
      'miName': null,
      'miNameInc': false,
      'miType': null,
      'miTypeInc': false,
      'noteToDealer': null,
      'noteToDealerInc': false,
      'operatingHours': null,
      'operatingHoursInc': false,
      'otherCostNetTotalFrom': null,
      'otherCostNetTotalInc': false,
      'otherCostNetTotalTo': null,
      'partsFittedDateInc': false,
      'partsMilageInc': false,
      'refNoFrom': null,
      'refNoInc': false,
      'refNoTo': null,
      'registrationDateFrom': null,
      'registrationDateInc': false,
      'registrationDateTo': null,
      'repairDateFrom': null,
      'repairDateInc': false,
      'repairDateTo': null,
      'repairOrderNoFrom': null,
      'repairOrderNoInc': false,
      'repairOrderNoTo': null,
      'repairingDealer': null,
      'repairingDealerInc': false,
      'repairingDealerName': null,
      'repairingDealerNameInc': false,
      'repairingImporter': null,
      'repairingImporterInc': false,
      'repairingImporterName': null,
      'repairingImporterNameInc': false,
      'returnCode': null,
      'returnCodeInc': false,
      'scc': null,
      'sccCaseSensitive': false,
      'sccInc': false,
      'scenarioName': null,
      'scenarioNameInc': false,
      'scenarioDescription': null,
      'scenarioDescriptionInc': false,
      'claimSearchTypes': '-1',
      'searchTypeInc': false,
      'tmaStatuses': '-1',
      'tmaStatusInc': false,
      'typeofclaim': '-1',
      'typeOfClaimInc': false,
      'vehicleAgeFrom': null,
      'vehicleAgeInc': false,
      'vehicleAgeTo': null,
      'vehicleMilageInc': false,
      'vin': null,
      'vinInc': false,
      'debitCodeActiveFrom': null,
      'debitCodeActiveTo': null,
      'debitCodeActiveInc': false,
      'debitCodeHistoryFrom': null,
      'debitCodeHistoryTo': null,
      'debitCodeHistoryInc': false,
      'rejectCodeFrom': null,
      'rejectCodeTo': null,
      'rejectCodeInc': false,
      'rejectCodeCaseSensitive': false,
      'travelNetTotalFrom': null,
      'travelNetTotalTo': null,
      'travelNetTotalInc': false,
      'numberOfRows': null,
      'orderBy': null,
      'startIndex': null,
      'descendingSortOrder': false,
      'fetchSize': 0
    };

    postAdvancedSearchClaimsForm () {
      var that = this;
      that.$log.debug(that.advancedSearchForm);
      that.advancedsearchData = {
        'causalPartName': that.advancedSearchForm.causalpartname,
        'causalPartNameCaseSensitive': that.advancedSearchForm.causalpartnamecasesensitive,
        'causalPartNameInc': that.advancedSearchForm.causalpartnameinc,
        'causalPartNo': that.advancedSearchForm.causalpartno,
        'causalPartNoCaseSensitive': that.advancedSearchForm.causalpartnocasesensitive,
        'causalPartNoInc': that.advancedSearchForm.causalpartnoinc,
        'causalPartPrefix': that.advancedSearchForm.causalpartprefix,
        'causalPartPrefixCaseSensitive': that.advancedSearchForm.causalpartprefixcasesensitive,
        'causalPartPrefixInc': that.advancedSearchForm.causalpartprefixinc,
        'causalPartSerial': that.advancedSearchForm.causalPartSerial,
        'cause': null,
        'causeInc': that.advancedSearchForm.causeinc,
        'chassisNoFrom': that.advancedSearchForm.chassisnofrom,
        'chassisNoInc': that.advancedSearchForm.chassisnoinc,
        'chassisNoTo': that.advancedSearchForm.chassisnoto,
        'chassisSeries': that.advancedSearchForm.chassisseries,
        'chassisSeriesCaseSensitive': that.advancedSearchForm.chassisseriescasesensitive,
        'chassisSeriesInc': that.advancedSearchForm.chassisseriesinc,
        'claimJobStatuses': that.advancedSearchForm.claimJobStatuses,
        'claimJobStatusInc': that.advancedSearchForm.claimjobstatusinc,
        'companyNo': this.selectedCompany,
        'companyNoInc': that.advancedSearchForm.companynoinc,
        'complaint': null,
        'complaintInc': that.advancedSearchForm.complaintinc,
        'correction': null,
        'correctionInc': that.advancedSearchForm.correctioninc,
        'creditedDateFrom': that.advancedSearchForm.crediteddatefrom,
        'creditedDateInc': that.advancedSearchForm.crediteddateinc,
        'creditedDateTo': that.advancedSearchForm.crediteddateto,
        'currency': that.advancedSearchForm.currency,
        'currencyInc': that.advancedSearchForm.currencyinc,
        'defectCode': that.advancedSearchForm.defectcode,
        'defectCodeInc': that.advancedSearchForm.defectcodeinc,
        'deliveryDateFrom': that.advancedSearchForm.deliverydatefrom,
        'deliveryDateInc': that.advancedSearchForm.deliverydateinc,
        'deliveryDateTo': that.advancedSearchForm.deliverydateto,
        'followUpCodeFrom': that.advancedSearchForm.followupcodefrom,
        'followUpCodeTo': that.advancedSearchForm.followupcodeto,
        'followUpCodeInc': that.advancedSearchForm.followupcodeinc,
        'forced': that.advancedSearchForm.forced,
        'forcedInc': that.advancedSearchForm.forcedinc,
        'functionGroupFrom': that.advancedSearchForm.functiongroupfrom,
        'functionGroupInc': that.advancedSearchForm.functiongroupinc,
        'functionGroupTo': that.advancedSearchForm.functiongroupto,
        'jobNo': null,
        'jobNoInc': that.advancedSearchForm.jobnoinc,
        'labourHoursFrom': that.advancedSearchForm.labourhoursfrom,
        'labourHoursTo': that.advancedSearchForm.labourhoursto,
        'labourHoursInc': that.advancedSearchForm.labourhoursinc,
        'labourNetTotalFrom': that.advancedSearchForm.labournettotalfrom,
        'labourNetTotalInc': that.advancedSearchForm.labournettotalinc,
        'labourNetTotalTo': that.advancedSearchForm.labournettotalto,
        'marketType': that.advancedSearchForm.marketingtype,
        'marketTypeCaseSensitive': that.advancedSearchForm.markettypecasesensitive,
        'marketTypeInc': that.advancedSearchForm.markettypeinc,
        'miDateFrom': that.advancedSearchForm.midatefrom,
        'miDateInc': that.advancedSearchForm.midateinc,
        'miDateTo': that.advancedSearchForm.midateto,
        'materialNetTotalFrom': that.advancedSearchForm.materialnettotalfrom,
        'materialNetTotalInc': that.advancedSearchForm.materialnettotalinc,
        'materialNetTotalTo': that.advancedSearchForm.materialnettotalto,
        'miName': null,
        'miNameInc': that.advancedSearchForm.minameinc,
        'miType': null,
        'miTypeInc': that.advancedSearchForm.false,
        'noteToDealer': null,
        'noteToDealerInc': that.advancedSearchForm.noteTodealerinc,
        'operatingHours': null,
        'operatingHoursInc': that.advancedSearchForm.operatinghoursinc,
        'otherCostNetTotalFrom': that.advancedSearchForm.othercostnettotalfrom,
        'otherCostNetTotalInc': that.advancedSearchForm.othercostnettotalinc,
        'otherCostNetTotalTo': that.advancedSearchForm.othercostnettotalto,
        'partsFittedDateInc': that.advancedSearchForm.partsfitteddateinc,
        'partsMilageInc': that.advancedSearchForm.partsmilageinc,
        'refNoFrom': that.advancedSearchForm.refnofrom,
        'refNoInc': that.advancedSearchForm.refnoinc,
        'refNoTo': that.advancedSearchForm.refnoto,
        'registrationDateFrom': that.advancedSearchForm.registrationdatefrom,
        'registrationDateInc': that.advancedSearchForm.registrationdateinc,
        'registrationDateTo': that.advancedSearchForm.registrationdateto,
        'repairDateFrom': that.advancedSearchForm.repairdatefrom,
        'repairDateInc': that.advancedSearchForm.repairdateinc,
        'repairDateTo': that.advancedSearchForm.repairdateto,
        'repairOrderNoFrom': that.advancedSearchForm.repairordernofrom,
        'repairOrderNoInc': that.advancedSearchForm.repairordernoinc,
        'repairOrderNoTo': that.advancedSearchForm.repairordernoto,
        'repairingDealer': that.advancedSearchForm.dealerno,
        'repairingDealerInc': that.advancedSearchForm.repairingdealerinc,
        'repairingDealerName': null,
        'repairingDealerNameInc': that.advancedSearchForm.false,
        'repairingImporter': that.advancedSearchForm.repairingimporter,
        'repairingImporterInc': that.advancedSearchForm.repairingimporterinc,
        'repairingImporterName': null,
        'repairingImporterNameInc': that.advancedSearchForm.repairingdealernameinc,
        'returnCode': that.advancedSearchForm.returncode,
        'returnCodeInc': that.advancedSearchForm.returncodeinc,
        'scc': that.advancedSearchForm.scc,
        'sccCaseSensitive': that.advancedSearchForm.scccasesensitive,
        'sccInc': that.advancedSearchForm.sccinc,
        'scenarioName': that.advancedSearchForm.scenarioname,
        'scenarioNameInc': that.advancedSearchForm.scenarionameinc,
        'scenarioDescription': null,
        'scenarioDescriptionInc': that.advancedSearchForm.scenariodescriptioninc,
        'claimSearchTypes': that.advancedSearchForm.claimSearchTypes,
        'searchTypeInc': that.advancedSearchForm.searchtypeinc,
        'tmaStatuses': that.advancedSearchForm.tmaStatuses,
        'tmaStatusInc': that.advancedSearchForm.tmastatusinc,
        'typeOfclaim': that.advancedSearchForm.typeofclaim,
        'typeOfClaimInc': that.advancedSearchForm.typeofclaiminc,
        'vehicleAgeFrom': that.advancedSearchForm.vehicleagefrom,
        'vehicleAgeInc': that.advancedSearchForm.vehicleageinc,
        'vehicleAgeTo': that.advancedSearchForm.vehicleageto,
        'vehicleMilageInc': that.advancedSearchForm.vehiclemilageinc,
        'vin': that.advancedSearchForm.vin,
        'vinInc': that.advancedSearchForm.vininc,
        'debitCodeActiveFrom': that.advancedSearchForm.debitcodeactivefrom,
        'debitCodeActiveTo': that.advancedSearchForm.debitcodeactiveto,
        'debitCodeActiveInc': that.advancedSearchForm.debitcodeactiveinc,
        'debitCodeHistoryFrom': that.advancedSearchForm.debitcodehistoryfrom,
        'debitCodeHistoryTo': that.advancedSearchForm.debitcodehistoryto,
        'debitCodeHistoryInc': that.advancedSearchForm.debitcodehistoryinc,
        'rejectCodeFrom': that.advancedSearchForm.rejectcodefrom,
        'rejectCodeTo': that.advancedSearchForm.rejectcodeto,
        'rejectCodeInc': that.advancedSearchForm.rejectcodeinc,
        'rejectCodeCaseSensitive': that.advancedSearchForm.rejectcodecasesensitive,
        'travelNetTotalFrom': that.advancedSearchForm.travelnettotalfrom,
        'travelNetTotalTo': that.advancedSearchForm.travelnettotalto,
        'travelNetTotalInc': that.advancedSearchForm.travelnettotalinc,
        'numberOfRows': 25,
        'orderBy': null,
        'startIndex': 1,
        'descendingSortOrder': false,
        'fetchSize': 0
      };

      var formData = that.advancedsearchData;
      that.AdvancedSearchStore.postAdvancedSearchClaimsForm(formData)
        .then(function (response) {
          that.DispatcherService.publish('advancedSearch:PostSearchClaims', response);
        });

      that.$log.debug(that.advancedsearchData);
    }
  }


  angular.module('uchpClientAngularApp')
    .component('advancedSearchFilter', {
      templateUrl: 'components/claim/claims/advanced-search/advanced-search-filter/advanced-search-filter.html',
      controller: [
        'AdvancedSearchStore',
        'AdvancedSearchCriteriaStore',
        'SelectCompaniesStore',
        'DispatcherService',
        'DateUtilService',
        '$log',
        '$scope',
        'UserStore',
        '$state',
        '$translate',
        AdvancedSearchFilterController
      ],
      controllerAs: 'ctrl'
    });

}
