/// <reference path="../../../../../client/app/_all.ts" />


module uchpClientAngularApp {
  'use strict';

  class OutstandingClaimSummaryDocumentStore implements IOutstandingClaimSummaryDocumentStore {
    private outstandingClaimSummaryDocsUrl: string;
    private outstandingClaimSummaryDocsCacheKey: string;
    private postOutstandingClaimSummaryDocsUrl: string;

    /**
     * 
     * 
     * @static
     * 
     * @memberOf OutstandingClaimSummaryDocumentStore
     */
    static $inject = [
      'SelectCompaniesStore',
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService'
    ];

    /**
     * Creates an instance of OutstandingClaimSummaryDocumentStore.
     * 
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {IRESTAdapterService} RESTAdapterService
     * @param {ICacheService} CacheService
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} DispatcherService
     * 
     * @memberOf OutstandingClaimSummaryDocumentStore
     */
    constructor(private SelectCompaniesStore: ISelectCompaniesStore,
                private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService,
                private DispatcherService: IDispatcherService) {
      this.outstandingClaimSummaryDocsCacheKey = 'OutstandingClaimSummaryDocumentStoreService.outstandingclaimsummaryDocuments';
      this.outstandingClaimSummaryDocsUrl = '/crediting/creditdocuments/outstandingclaimsummarydealerdocuments';
      this.postOutstandingClaimSummaryDocsUrl = '/crediting/creditdocuments/createoutstandingclaimsummarydealerdocuments';
    }

    /**
     * 
     * 
     * @param {string} header
     * @returns {Object}
     * 
     * @memberOf OutstandingClaimSummaryDocumentStore
     */
    getFileNameFromHeader(header: string): Object {
      if (!header) {
        return null;
      }

      var result: string = header.split(';')[1].trim().split('=')[1];
      var fullName: string = result.replace(/"/g, '');
      return {
        name: fullName.split('.')[0],
        extension: fullName.split('.')[1]
      };
    }

    /**
     * 
     * 
     * @param {any} creditingDocumentId
     * @param {any} mimeType
     * @returns {*}
     * 
     * @memberOf OutstandingClaimSummaryDocumentStore
     */
    getOutstandingClaimSummaryDocument(creditingDocumentId, mimeType): any {
      var that = this;
      var contentType;
      //tries to get cached data
      //var cachedData = this.CacheService.get(this.creditDocsCacheKey);
      //TODO Cache Service
      var cachedData: any = null;

      if (mimeType === 'PDF') {
        contentType = 'application/pdf';
        that.$log.debug('PDF Type ::: ' + contentType);
        window.open('https://localhost/static/documents/outstandingclaimsummarydocument.pdf', '_blank');
      } else if (mimeType === 'EXCEL') {
        contentType = 'application/vnd.ms-excel';
        that.$log.debug('XLS Type ::: ' + contentType);
        window.open('https://localhost/static/documents/outstandingclaimsummarydocument.xls', '_blank');
      }

      if (!cachedData) {
        that.RESTAdapterService
          .get(that.outstandingClaimSummaryDocsUrl + '/' + creditingDocumentId, {
            download: true,
            params: {},
            responseType: 'arraybuffer',
            /**
             * 
             * 
             * @param {*} data
             * @param {*} headers
             * @returns
             */
            transformResponse: (data: any, headers: any) => {
              var file = null;
              if (data) {
                file = new Blob([data], {
                  type: contentType
                });
              }
              //server should sent content-disposition header
              var fileName: Object = that.getFileNameFromHeader(headers('content-disposition'));
              return {
                blob: file,
                fileName: fileName
              };
            }
          })
          .then((result: any) => {
            that.$log.debug('Download success ', result);
            var url = window.URL.createObjectURL(result.data.blob);
            var anchor = angular.element('<a></a>');
            let activexObjectAccessor = 'ActiveXObject';

            if (!(window[activexObjectAccessor]) && 'ActiveXObject' in window) {   // IE 11 case
              window.navigator.msSaveBlob(result.data.blob, result.data.fileName.name + '.' + result.data.fileName.extension);
            } else if ('netscape' in window) { //Firefox Case
              var hyperlink = document.createElement('a');
              let hyperlinkDownload = 'download';
              hyperlink.href = url;
              hyperlink.target = '_blank';
              hyperlink[hyperlinkDownload] = result.data.fileName.name + '.' + result.data.fileName.extension || url;
              (document.body || document.documentElement).appendChild(hyperlink);
              /**
               * 
               */
              hyperlink.onclick = function() {
                (document.body || document.documentElement).removeChild(hyperlink);
              };
              var mouseEvent = new MouseEvent('click', {
                view: window,
                bubbles: true,
                cancelable: true
              });
              hyperlink.dispatchEvent(mouseEvent);
            } else { //Chrome
              anchor.attr({
                href: url,
                target: '_blank',
                download: result.data.fileName.name + '.' + result.data.fileName.extension
              });
            }

            anchor[0].click();
          }, (error: any) => {
            if (error != null) {
              that.$log.debug('Downloading error ' + JSON.stringify(error));
            }
          });
      }
    }

    /**
     * 
     * 
     * @param {*} searchFormData
     * @returns {ng.IPromise <{ }>}
     * 
     * @memberOf OutstandingClaimSummaryDocumentStore
     */
    getOutstandingClaimSummaryDocuments(searchFormData: any): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      // tries to get cached data
      // var cachedData = this.CacheService.get(this.creditDocsCacheKey);
      // TODO Cache Service
      var cachedData = null;

      // returns data from cache or server
      if (!cachedData) {
        that.RESTAdapterService
          .get(that.outstandingClaimSummaryDocsUrl, {
            params: {
              companyNo: searchFormData.companyNo,
              importerNo: searchFormData.importerNo,
              dealerNo: searchFormData.dealerNo,
              make: searchFormData.make,
              creationDateFrom: searchFormData.creationDateFrom,
              creationDateTo: searchFormData.creationDateTo,
              descendingSortOrder: searchFormData.descendingSortOrder,
              numberOfRows: searchFormData.numberOfRows,
              startIndex: searchFormData.startIndex,
              orderBy: searchFormData.orderBy
            }
          })
          .then((result: any) => {
              that.CacheService.put(that.outstandingClaimSummaryDocsCacheKey, result);
              that.DispatcherService.publish('outstandingclaimsummarydocument:OutstandingClaimSummaryDocuments', result);
              deferred.resolve(result);
            }, (err) => {
              deferred.reject('Error getting the Credit Documents: ' + err);
            });
          } else {
          // wrap local cached data into promise object
          deferred.resolve(cachedData);
          that.DispatcherService.publish('outstandingclaimsummarydocument:OutstandingClaimSummaryDocumentsCached', cachedData);
        }
        return deferred.promise;
    }

    /**
     * 
     * 
     * @param {*} formData
     * 
     * @memberOf OutstandingClaimSummaryDocumentStore
     */
    createOutstandingClaimSummaryDocument(formData: any) {
      var that = this;
      var userCompanyNo = that.SelectCompaniesStore.getSelectedCompany();
      var postData = {
        'companyNo': userCompanyNo,
        'repairingImporter': formData.repairingImporter,
        'repairingDealer': formData.repairingDealer,
        'make': formData.make
      };
      that.RESTAdapterService
        .post(that.postOutstandingClaimSummaryDocsUrl, {
          params: null
        }, postData)
        .then((response: any) => {
          that.DispatcherService.publish('createoutstandingclaim:ClaimCreated', {
            success: true,
            claimCreatedResponse: response
          });
        }, (error) => {
          that.DispatcherService.publish('createoutstandingclaim:ClaimCreated', {
            success: false,
            claimCreatedResponse: error
          });
        });
    }
  }

  angular
    .module('uchpClientAngularApp')
    .service('OutstandingClaimSummaryDocumentStore', OutstandingClaimSummaryDocumentStore);
}
