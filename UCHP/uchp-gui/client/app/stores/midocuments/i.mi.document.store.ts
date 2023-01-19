/// <reference path="../../_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IMiDocumentStore
   */
  export interface IMiDocumentStore {
    /**
     * 
     * 
     * @param {any} listofjobsDocsDownload
     * @returns {*}
     * 
     * @memberOf IMiDocumentStore
     */
    getPrintMIDocuments(listofjobsDocsDownload): any;
  }

}
