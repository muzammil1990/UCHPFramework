module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @export
   * @interface IModalService
   */
  export interface IModalService {
    /**
     *
     *
     * @param {ModalContent} content
     * @param {string} topic
     * @param {string} size
     * @param {boolean} keyboard
     * @param {Object} listGridResult
     * @param {string} [customTemplate]
     * @param {*} [labourGrid]
     * @param {*} [simpleList]
     * @returns {*}
     *
     * @memberOf IModalService
     */
    open(content: ModalContent, topic: string, size: string, keyboard: boolean, listGridResult: Object,
         customTemplate?: string, labourGrid?: any, simpleList?: any): any;

    close(): any;

    modalInstance: any;
  }
}
