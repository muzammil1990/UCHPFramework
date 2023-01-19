/**
 * Created by a038509 on 2017-01-13.
 */
/// <reference path="../../_all.ts" />
module uchpClientAngularApp {
  'use strict';
  // moment is a globally available variable
  declare var moment: any;

  class DateUtilService implements IDateUtilService {
    private datePickerOptions: any;
    private dateFormat: string;

    static $inject = [
      '$log'
    ];

    constructor(private $log: ng.ILogService) {
      $log.debug('Initializing DateUtilService...');

      this.dateFormat = 'DD/MM/YY';
      // Set options for the datepickers
      // API here: http://eonasdan.github.io/bootstrap-datetimepicker/Options/
      this.datePickerOptions = {
        format: this.dateFormat,
        locale: moment.locale(),
        useCurrent: false,
        maxDate: moment(),
        allowInputToggle: true
      };
    }

    DateParse(date: any) {
      return date ? moment(date).format(this.dateFormat) : null;
    }

    getDatePickerOptions() {
      return this.datePickerOptions;
    }
  }


  angular.module('uchpClientAngularApp')
    .service('DateUtilService', DateUtilService);


}
