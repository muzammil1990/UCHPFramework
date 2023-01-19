/// <reference path="../../_all.ts" />


module uchpClientAngularApp {
  'use strict';

  class MiDocumentStore implements IMiDocumentStore {
    private miDocumentsUrl: string;

    /**
     * 
     * 
     * @static
     * 
     * @memberOf MiDocumentStore
     */
    static $inject = [
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService'
    ];

    /**
     * Creates an instance of MiDocumentStore.
     * 
     * @param {IRESTAdapterService} RESTAdapterService
     * @param {ICacheService} CacheService
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * @param {IDispatcherService} dispatcherService
     * 
     * @memberOf MiDocumentStore
     */
    constructor(private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService,
                private dispatcherService: IDispatcherService

    ) {
      this.miDocumentsUrl = '/claim/claims/midocuments';
    }


    /**
     * 
     * 
     * @param {string} header
     * @returns {Object}
     * 
     * @memberOf MiDocumentStore
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
     * @memberOf MiDocumentStore
     */
    getPrintMIDocuments(listofjobsDocsDownload): any {
      var that = this;
      console.log(listofjobsDocsDownload);
      var cachedData: any = null;
      if (!cachedData) {
        that.RESTAdapterService
          .get(that.miDocumentsUrl, {
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
           /* if (result.data.fileName !== null) {
              anchor.attr({
                href: url,
                target: '_blank',
                download: result.data.fileName.name + '_' + miDocumentId + '.' + result.data.fileName.extension
              });
            } else {
              anchor.attr({
                href: url,
                target: '_blank',
                download: 'PirntMIDocuments.pdf'
              });
            }*/

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
    .service('MiDocumentStore', MiDocumentStore);
}
