/// <reference path="../../_all.ts" />


  'use strict';

describe('Filter: filename', function () {

  // load the filter's module
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  // initialize a new instance of the filter before each test
  var filename;
  beforeEach(inject(function ($filter) {
    filename = $filter('filename');
  }));

  it('should return the input translated to correct filetype name', function () {
    expect(filename('application/pdf')).toBe('PDF');
    expect(filename('application/vnd.ms-excel')).toBe('EXCEL');
    expect(filename(null)).toBe('');
  });

});
