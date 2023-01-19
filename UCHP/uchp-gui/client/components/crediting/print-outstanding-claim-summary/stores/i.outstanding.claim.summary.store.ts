/// <reference path="../../../../../client/app/_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IOutstandingClaimSummaryDocumentStore
   */
  export interface IOutstandingClaimSummaryDocumentStore {
    /**
     * 
     * 
     * @param {any} outstandingClaimSummaryDocumentsObjData
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf IOutstandingClaimSummaryDocumentStore
     */
    getOutstandingClaimSummaryDocuments(outstandingClaimSummaryDocumentsObjData): ng.IPromise<{}>;
    /**
     * 
     * 
     * @param {any} creditDocumentId
     * @param {any} mimeType
     * @returns {*}
     * 
     * @memberOf IOutstandingClaimSummaryDocumentStore
     */
    getOutstandingClaimSummaryDocument(creditDocumentId, mimeType): any;
    /**
     * 
     * 
     * @param {any} formData
     * @returns {*}
     * 
     * @memberOf IOutstandingClaimSummaryDocumentStore
     */
    createOutstandingClaimSummaryDocument(formData): any;
    /**
     * 
     * 
     * @param {string} [header]
     * @returns {Object}
     * 
     * @memberOf IOutstandingClaimSummaryDocumentStore
     */
    getFileNameFromHeader(header?: string): Object;
  }
}
