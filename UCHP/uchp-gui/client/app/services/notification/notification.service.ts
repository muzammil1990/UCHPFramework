/// <reference path="../../_all.ts" />

module uchpClientAngularApp {
  'use strict';

  /**
   * 
   * 
   * @export
   * @class NotificationService
   * @implements {INotificationService}
   */
  export class NotificationService implements INotificationService {
    private maxLength: number;
    /**
     * 
     * 
     * @static
     * 
     * @memberOf NotificationService
     */
    static $inject = [
      '$translate',
      'toastr',
      'toastrConfig'
     ];

    /**
     * Creates an instance of NotificationService.
     * 
     * @param {ng.translate.ITranslateService} $translate
     * @param {Toastr} toastr
     * @param {ToastrOptions} toastrConfig
     * 
     * @memberOf NotificationService
     */
    constructor(
      private $translate: ng.translate.ITranslateService,
      private toastr: Toastr,
      private toastrConfig: ToastrOptions
    ) {
      this.maxLength = 128;
      // API: https://github.com/Foxandxss/angular-toastr
      angular.extend(this.toastrConfig, {
        allowHtml: true,
        positionClass: 'toast-top-right',
        iconClasses: {
          error: 'murali-toast-error',
          info: 'murali-toast-info',
          success: 'murali-toast-success',
          warning: 'murali-toast-warning'
        },
        toastClass: 'toast murali-toast',
        timeOut: 10000,
        preventDuplicates: false,
        closeButton: true,
        progressBar: true,
        closeHtml: '<button>&times;</button>'
      });
    }

    private limitToMaxLength(message: string): string {
      if (!message) {
        return '';
      }
      if (message.length > this.maxLength) {
        message.substr(0, this.maxLength || 100).concat('...');
      } else {
        return message;
      }
    }

    /**
     * 
     * 
     * @param {string} msg
     * @param {string} [title]
     * 
     * @memberOf NotificationService
     */
    sendSuccess(msg: string, title?: string): void {
      this.toastr.clear();
      this.toastr.success(msg, title);
    }

    /**
     * 
     * 
     * @param {string} msg
     * @param {string} [title]
     * 
     * @memberOf NotificationService
     */
    sendError(msg: string, title?: string): void {
      this.toastr.error(msg, title);
    }

    /**
     * 
     * 
     * @param {string} msg
     * @param {string} [title]
     * 
     * @memberOf NotificationService
     */
    sendInfo(msg: string, title?: string): void {
      this.toastr.info(msg, title);
    }

    /**
     * 
     * 
     * @param {string} msg
     * @param {string} [title]
     * 
     * @memberOf NotificationService
     */
    sendWarning(msg: string, title?: string): void {
      this.toastr.warning(msg, title);
    }

    /**
     * 
     * 
     * @param {*} message
     * @returns
     * 
     * @memberOf NotificationService
     */
    trigger(message: any) {
      if (!message) {
        this.toastr.clear();
        return;
      }
      let title;
      let msg;
      let error: boolean;
      let icon: string;
      icon = '<i class="fa fa-info-circle" aria-hidden="true"></i>';
      if (message.data.primaryCode != null) {
        title = message.data.primaryCode || '998';
        if (message.data.secondaryCode !== 0) {
          msg =  this.$translate.instant('ERROR.DESCRIPTION.' + message.data.primaryCode) + '. ' +
            this.$translate.instant('ERROR.DESCRIPTION.' + message.data.secondaryCode);
          title = icon + ' ' + message.data.primaryCode + ', ' + message.data.secondaryCode;
        } else {
          msg = this.$translate.instant('ERROR.DESCRIPTION.' + message.data.primaryCode);
          title = icon + ' ' + message.data.primaryCode;
        }
        if (message.status === 404) {
          error = true;
        }
      } else {
        if (message.status === 500 || message.status === 400 ||
          message.status === 401 || message.status === 402 ||
          message.status === 403 || message.status === 404) {
          icon = '<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>';
          title = icon + ' ' + message.status;
          if (message.status === 403) {
            msg = 'User not authorized';
          } else {
            msg = message.statusText;
          }
          error = true;
        }
      }
      msg = this.limitToMaxLength(msg);

      if (error) {
        this.sendError(msg, title);
      } else {
        this.sendInfo(msg, title);
      }
    }
  }
  angular.module('uchpClientAngularApp')
    .service('NotificationService', NotificationService);
}
