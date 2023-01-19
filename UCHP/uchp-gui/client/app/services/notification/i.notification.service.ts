module uchpClientAngularApp {
  'use strict';
  /**
   * 
   * 
   * @export
   * @interface INotificationService
   */
  export interface INotificationService {
    /**
     * 
     * 
     * @param {string} msg
     * @param {string} [title]
     * 
     * @memberOf INotificationService
     */
    sendSuccess(msg: string, title?: string);
    /**
     * 
     * 
     * @param {string} msg
     * @param {string} [title]
     * 
     * @memberOf INotificationService
     */
    sendError(msg: string, title?: string);
    /**
     * 
     * 
     * @param {string} msg
     * @param {string} [title]
     * 
     * @memberOf INotificationService
     */
    sendInfo(msg: string, title?: string);
    /**
     * 
     * 
     * @param {string} msg
     * @param {string} [title]
     * 
     * @memberOf INotificationService
     */
    sendWarning(msg: string, title?: string);
    /**
     * 
     * 
     * @param {*} message
     * 
     * @memberOf INotificationService
     */
    trigger(message: any);
  }
}
