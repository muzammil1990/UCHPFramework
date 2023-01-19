/**
 * Created by a148094 on 27-04-2016.
 */
module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @interface IContentTypeStore
   */
  export interface IContentTypeStore {
    /**
     * 
     * 
     * @returns {ng.IPromise<{}>}
     * 
     * @memberOf IContentTypeStore
     */
    getContentTypes(): ng.IPromise<{}>;
  }

}
