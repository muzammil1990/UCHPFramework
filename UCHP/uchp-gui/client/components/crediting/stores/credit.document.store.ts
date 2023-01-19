/// <reference path="../../../app/_all.ts" />


module uchpClientAngularApp {
  'use strict';

  class CreditDocumentStore implements ICreditDocumentStore {
    private creditDocumentsUrl: string;
    private creditingDocumentsCacheKey: string;

    /**
     * 
     * 
     * @static
     * 
     * @memberOf CreditDocumentStore
     */
    static $inject = [
      'SelectCompaniesStore',
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService',
      'NotificationService'
    ];

    /**
     * Creates an instance of CreditDocumentStore.
     * 
     * @param {ISelectCompaniesStore} SelectCompaniesStore
     * @param {IRESTAdapterService} RESTAdapterService
     * @param {ICacheService} CacheService
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} DispatcherService
     * @param {INotificationService} NotificationService
     * 
     * @memberOf CreditDocumentStore
     */
    constructor(private SelectCompaniesStore: ISelectCompaniesStore,
                private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService,
                private DispatcherService: IDispatcherService,
                private NotificationService: INotificationService

    ) {
      this.creditingDocumentsCacheKey = 'CreditDocumentStore.creditingDocuments';
      this.creditDocumentsUrl = '/crediting/creditdocuments/creditingdocuments';
    }


    /**
     * 
     * 
     * @param {string} header
     * @returns {Object}
     * 
     * @memberOf CreditDocumentStore
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
     * @memberOf CreditDocumentStore
     */
    getCreditDocument(creditingDocumentId, mimeType): any {
      var that = this;
      var contentType;

      // TODO: this should be corrected from serverside!
      if (mimeType === 'PDF') {
        contentType = 'application/pdf';
        that.$log.debug('PDF Type ::: ' + contentType);
        window.open('https://localhost/static/documents/listCreditingDocuments.pdf', '_blank');
      } else if (mimeType === 'EXCEL') {
        contentType = 'application/vnd.ms-excel';
        that.$log.debug('XLS Type ::: ' + contentType);
        window.open('https://localhost/static/documents/listCreditingDocuments.xls', '_blank');
      }
      that.RESTAdapterService
        .get(that.creditDocumentsUrl + '/' + creditingDocumentId, {
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
            this.NotificationService.trigger(error);
            that.$log.debug('Downloading error ' + JSON.stringify(error));
          }
        });
    }

    /**
     * 
     * 
     * @param {*} searchFormData
     * @param {boolean} noCache
     * @returns {ng.IPromise <{ }>}
     * 
     * @memberOf CreditDocumentStore
     */
    getCreditDocuments(searchFormData: any, noCache: boolean): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var userCompanyNo = this.SelectCompaniesStore.getSelectedCompany();
      var that = this;
      if (searchFormData.make === '') {
        searchFormData.make = null;
      }
      //tries to get cached data
      var cachedData = this.CacheService.get(this.creditingDocumentsCacheKey);
      //returns data from cache or server
      if (!cachedData || noCache) {
        // clear old results
        that.DispatcherService.publish('printcreditdocument:SearchCreditDocumentsClear', {});
        that.RESTAdapterService
          .get(that.creditDocumentsUrl, {
            params: {
              companyNo: userCompanyNo,
              importerNo: searchFormData.importerNo,
              dealerNo: searchFormData.dealerNo,
              make: searchFormData.make,
              creditDocNo: searchFormData.creditDocNo,
              creditingDateFrom: searchFormData.creditingDateFrom,
              creditingDateTo: searchFormData.creditingDateTo,
              mimeType: searchFormData.mimeType,
              descendingSortOrder: searchFormData.descendingSortOrder,
              numberOfRows: searchFormData.numberOfRows,
              startIndex: searchFormData.startIndex,
              orderBy: searchFormData.orderBy
            }
          })
          .then((result: any) => {
            that.CacheService.put(that.creditingDocumentsCacheKey, result);
            deferred.resolve(result);
            that.DispatcherService.publish('printcreditdocument:SearchCreditDocuments', result);
          }, (err) => {
            deferred.reject('Error getting the Credit Documents: ' + err);
          });
      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedData);
        that.DispatcherService.publish('printcreditdocument:SearchCreditDocumentsCached', cachedData);
      }
      return deferred.promise;
    }
  }


  angular
    .module('uchpClientAngularApp')
    .service('CreditDocumentStore', CreditDocumentStore);
}
