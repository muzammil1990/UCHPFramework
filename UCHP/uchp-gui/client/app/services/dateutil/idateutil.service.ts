/**
 * Created by a232625 on 2016-07-18.
 */
/// <reference path="../../_all.ts" />
module uchpClientAngularApp {
  'use strict';

  /**
   *
   *
   * @export
   * @interface IDateUtilService
   */
  export interface IDateUtilService {
    /**
     *
     *
     * @memberOf IDateUtilService
     */
    DateParse(date: any): any;
    /**
     *
     * @memberOf IDateUtilService
     */
    getDatePickerOptions(): any;

  }

}
