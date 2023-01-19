/**
 * Created by a148094 on 21-07-2016.
 */

/// <reference path="../../../../../app/_all.ts" />

module uchpClientAngularApp {
  'use strict';

  export class ViewCoverageModel {
    coverageId: String;
    coverageName: String;
    coverageDescription: String;
    coverageTextName: String;
    coverageTextDescription: String;
    countryCode: String;
    languageCode: String;
    coverageStartDate: Date;
    coverageEndDate: Date;
    coverageAttachmentId: String;
    coverageAttachmentName: String;
    active: Boolean;
  }
}
