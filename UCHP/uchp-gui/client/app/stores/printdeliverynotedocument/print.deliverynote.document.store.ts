/// <reference path="../../_all.ts" />


module uchpClientAngularApp {
  'use strict';

  class PrintDeliveryNoteDocumentStore implements IPrintDeliveryNoteDocumentStore {
    private PrintDeliveryNoteDocumentsUrl: string;

    /**
     * 
     * 
     * @static
     * 
     * @memberOf PrintDeliveryNoteDocumentStore
     */
    static $inject = [
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService'
    ];

    /**
     * Creates an instance of PrintDeliveryNoteDocumentStore.
     * 
     * @param {IRESTAdapterService} RESTAdapterService
     * @param {ICacheService} CacheService
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} dispatcherService
     * 
     * @memberOf PrintDeliveryNoteDocumentStore
     */
    constructor(private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService,
                private dispatcherService: IDispatcherService

    ) {
      this.PrintDeliveryNoteDocumentsUrl = '/claim/claims/deliverynotesummary';
    }


    /**
     * 
     * 
     * @param {string} header
     * @returns {Object}
     * 
     * @memberOf PrintDeliveryNoteDocumentStore
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
     * @param {any} listofjobsDocsDownload
     * @returns {*}
     * 
     * @memberOf PrintDeliveryNoteDocumentStore
     */
    getPrintDeliveryNoteDocuments(listofjobsDocsDownload): any {
      var that = this;
      var cachedData: any = null;
      if (!cachedData) {
        that.RESTAdapterService
          .get(that.PrintDeliveryNoteDocumentsUrl, {
            download: true,
            params: {
              claimJobCompanyNos: listofjobsDocsDownload.claimJobCompanyNos,
              claimHeaderIds: listofjobsDocsDownload.claimHeaderIds,
              claimJobIds: listofjobsDocsDownload.claimJobIds
            },
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
                  type: 'application/pdf'
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
 }


  angular
    .module('uchpClientAngularApp')
    .service('PrintDeliveryNoteDocumentStore', PrintDeliveryNoteDocumentStore);
}
