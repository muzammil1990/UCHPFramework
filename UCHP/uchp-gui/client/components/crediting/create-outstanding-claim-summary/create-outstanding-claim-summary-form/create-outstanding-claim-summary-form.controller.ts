/// <reference path="../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';
  var targetService = 'printCreditDocument';
  /**
   * - retrieves and persists the model via the storage service
   * - exposes the model to the template and provides event handlers
   */
  interface ModalContent {
    title: string;
    message: string;
    subMessage: string;
  }

  class CreateOutstandingClaimSummaryFormController {
    private brandlist: Array < BrandModel >;
    private modalActionSubscription: any;
    private submitActionSubscription: any;
    private selectedCompany: String;
    private selectCompanies: Array < any >;
    private DealerPattern: any;

    static $inject = [
      'BrandStore',
      'OutstandingClaimSummaryDocumentStore',
      'SelectCompaniesStore',
      '$log',
      'DispatcherService',
      '$scope',
      'ModalService',
      'NotificationService',
      '$translate',
      '$translatePartialLoader'
    ];

    constructor(
      private BrandStore: IBrandStore,
      private OutstandingClaimSummaryDocumentStore: IOutstandingClaimSummaryDocumentStore,
      private SelectCompaniesStore: ISelectCompaniesStore,
      private $log: ng.ILogService,
      private DispatcherService: IDispatcherService,
      private $scope: ng.IScope,
      private modal: IModalService,
      private NotificationService: INotificationService,
      private $translate: ng.translate.ITranslateService,
      private $translatePartialLoaderProvider: ng.translate.ITranslatePartialLoaderProvider
    ) {

      var that = this;

      that.modalActionSubscription = that.DispatcherService.subscribe('modalclose:CreateOutstandingClaimSummary',
        that.modalCallbackHandler.bind(this)
      );

      that.submitActionSubscription = that.DispatcherService.subscribe('createoutstandingclaim:ClaimCreated',
        that.claimCreatedCallbackHandler.bind(this)
      );

      that.$scope.$on('$stateChangeStart', function () {
        that.modalActionSubscription.unsubscribe();
      });


      that
        .BrandStore
        .getBrandList(targetService)
        .then(function (BrandList: Array < BrandModel >) {
          that.brandlist = BrandList;
        });
      that
        .SelectCompaniesStore
        .getSelectCompanies()
        .then(function (selectCompanies: Array < {} >) {
          if (selectCompanies !== null && selectCompanies !== undefined) {
            that.selectCompanies = selectCompanies;
          }
        });
      /**
       * Get initial company (used in translation keys)
       * */
      that.selectedCompany = that.SelectCompaniesStore.getSelectedCompany();
      // that.DealerPattern = that.selectedCompany !== '4' ? /^\d+$/ : /^[a-zA-Z0-9]+$/;
      that.DealerPattern = /^[a-zA-Z0-9]+$/;
    }

    // a model to keep the form values
    // implement cache strategy later?

    outStandingClaimDocumentModel: OutstandingClaimDocumentModel = {
      'repairingImporter': null,
      'repairingDealer': null,
      'make': '-1'
    };

    OnlyNumbers = /^\d+$/;

    CreateClaim() {
      this.OutstandingClaimSummaryDocumentStore.createOutstandingClaimSummaryDocument(this.outStandingClaimDocumentModel);
    }

    OpenModal() {
      var that = this;
      var content: ModalContent = {
        'title': 'CREATE_OUTSTANDING_CLAIM_SUMMARY.MODAL.TITLE',
        'message': 'CREATE_OUTSTANDING_CLAIM_SUMMARY.MODAL.MESSAGE',
        'subMessage': 'CREATE_OUTSTANDING_CLAIM_SUMMARY.MODAL.SUBMESSAGE'
      };
      that.modal.open(content, 'CreateOutstandingClaimSummary', 'md', true, null, 'createdocument.html');
      // Please uncomment to check labour section
      /*let labourGrid = [
        {
          operationCode: 1,
          description: 'Foo'
        }, {
          operationCode: 2,
          description: 'Bar'
        }, {
          operationCode: 3,
          description: 'Buzz'
        }, {
          operationCode: 4,
          description: 'FooBar'
        }, {
          operationCode: 5,
          description: 'FooBarBuzz'
        }
      ];
      this.modal.open(null, 'ViewCoverage', '', true, null, 'labour.html', labourGrid);
      */
    }

    modalCallbackHandler(submit: boolean) {
      if (submit) {
        this.$log.debug('submit');
        this.CreateClaim();
      } else {
        this.$log.debug('reject');
      }
    }

    claimCreatedCallbackHandler(response: any) {
      if (response.success) {
        this.successHandler(response);
      }
      if (response.error) {
        this.errorHandler(response);
      }
    }

    successHandler(response) {
      this.$log.debug(response);
      this.NotificationService.sendSuccess(this.$translate.instant('CREATE_OUTSTANDING_CLAIM_SUMMARY.MODAL.SUBMESSAGE'));
      this.resetForm();
    }

    errorHandler(response) {
      // TODO  error handling should be handled in rest adapter
      this.$log.debug('Errorhandler: ' + response);
    }

    resetForm() {
      this.outStandingClaimDocumentModel = {
        'repairingImporter': null,
        'repairingDealer': '',
        'make': '-1'
      };
      let outStandingClaimDocumentFormAccessor = 'outStandingClaimDocumentForm';
      this[outStandingClaimDocumentFormAccessor].$setPristine();
      this[outStandingClaimDocumentFormAccessor].$setUntouched();
    }
  }

  angular
    .module('uchpClientAngularApp')
    .component('createOutstandingClaimSummaryForm', {
      templateUrl : 'components/crediting/create-outstanding-claim-summary/' +
      'create-outstanding-claim-summary-form/create-outstanding-claim-summary-form.html',
      controller  : [
        'BrandStore',
        'OutstandingClaimSummaryDocumentStore',
        'SelectCompaniesStore',
        '$log',
        'DispatcherService',
        '$scope',
        'ModalService',
        'NotificationService',
        '$translate',
        '$translatePartialLoader',
        CreateOutstandingClaimSummaryFormController
      ],
      controllerAs: 'ctrl'
    });
}
