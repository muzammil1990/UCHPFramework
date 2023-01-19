/// <reference path="../../../../client/app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface ICreditDocumentStore
   */
  export interface ICreditDocumentStore {
    /**
     * 
     * 
     * @param {any} creditDocumentsData
     * @param {any} noCache
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf ICreditDocumentStore
     */
    getCreditDocuments(creditDocumentsData, noCache): ng.IPromise<{}>;
    /**
     * 
     * 
     * @param {any} creditingDocumentId
     * @param {any} mimeType
     * @returns {*}
     * 
     * @memberOf ICreditDocumentStore
     */
    getCreditDocument(creditingDocumentId, mimeType): any;
    /**
     * 
     * 
     * @param {String} [header]
     * @returns {Object}
     * 
     * @memberOf ICreditDocumentStore
     */
    getFileNameFromHeader(header?: String): Object;
  }

}
