/// <reference path="../../../../app/_all.ts" />


module uchpClientAngularApp {
  'use strict';

  class VehicleInformationStore implements IVehicleInformationStore {
    private vehicleInformationUrl: string;
    private vehicleInformationViewCoverageUrl: string;
    private vehicleInformationViewRepairHistoryUrl: string;
    private vehicleViewCoverageDocumentUrl: string;
    private vehicleInformationCacheKey: string;
    private brandModel: string;
    private chassisNumber: string;

    static $inject = [
      'SelectCompaniesStore',
      'RESTAdapterService',
      'CacheService',
      '$q',
      '$log',
      'DispatcherService'
    ];

    constructor(private SelectCompaniesStore: ISelectCompaniesStore,
                private RESTAdapterService: IRESTAdapterService,
                private CacheService: ICacheService,
                private $q: ng.IQService,
                private $log: ng.ILogService,
                private dispatcherService: IDispatcherService

    ) {
      var that = this;
      that.vehicleInformationCacheKey = 'VehicleInformationStore.vehicleInformations';
      that.vehicleInformationViewCoverageUrl = '/claim/vehicle/coverages';
      that.vehicleInformationViewRepairHistoryUrl = '/claim/vehicle/repairhistory';
      that.vehicleViewCoverageDocumentUrl = '/claim/vehicle/coverages/attachment';
      that.$log.debug('VehicleInformationStore Initialized');
    }

    getFileNameFromHeader(header: string): Object {
      if (!header) {
        return null;
      }

      var result: string = header.split(';')[1].trim().split('=')[1];
      var fullName: string = result.replace(/"/g, '');
      return {
        name: fullName.split('.')[0],
        extension: fullName.split('.')[1]
      };
    }
    getviewCoverageDocument(coverageId, coverageAttachmentId): any {
      var that = this;
    //  var contentType;

      //tries to get cached data
      //var cachedData = this.CacheService.get(this.creditDocsCacheKey);
      //TODO Cache Service
      var cachedData: any = null;

      if (!cachedData) {
        that.RESTAdapterService
          .get(that.vehicleViewCoverageDocumentUrl + '/' + coverageId + '/' + coverageAttachmentId, {
            download: true,
            params: {},
            responseType: 'arraybuffer',
            transformResponse: (data: any, headers: any) => {
              var file = null;
              if (data) {
                file = new Blob([data], {
                  type: 'application/pdf'
                });
              }
/*              //server should sent content-disposition header*/
              var fileName: Object = that.getFileNameFromHeader(headers('content-disposition'));
              return {
                blob: file,
                fileName: fileName
             };
            }
          })
          .then((result: any) => {
            that.$log.debug('Download success ', result);
            var url = window.URL.createObjectURL(result.data.blob);
            var anchor = angular.element('<a></a>');
            let activexObjectAccessor = 'ActiveXObject';

            if (!(window[activexObjectAccessor]) && 'ActiveXObject' in window) {   // IE 11 case
              window.navigator.msSaveBlob(result.data.blob, result.data.fileName.name + '.' + result.data.fileName.extension);
            } else if ('netscape' in window) { //Firefox Case
              var hyperlink = document.createElement('a');
              let hyperlinkDownload = 'download';
              hyperlink.href = url;
              hyperlink.target = '_blank';
              hyperlink[hyperlinkDownload] = result.data.fileName.name + '.' + result.data.fileName.extension || url;
              (document.body || document.documentElement).appendChild(hyperlink);
              hyperlink.onclick = function() {
                (document.body || document.documentElement).removeChild(hyperlink);
              };
              var mouseEvent = new MouseEvent('click', {
                view: window,
                bubbles: true,
                cancelable: true
              });
              hyperlink.dispatchEvent(mouseEvent);
            } else { //Chrome
              anchor.attr({
                href: url,
                target: '_blank',
                download: result.data.fileName.name + '.' + result.data.fileName.extension
              });
            }

            anchor[0].click();
          }, (error: any) => {
            if (error != null) {
              that.$log.debug('Downloading error ' + JSON.stringify(error));
            }
          });
      }
    }

    getVehicleInformation(searchFormData, isService): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var userCompanyNo = that.SelectCompaniesStore.getSelectedCompany();
      var cachedData: any = null;
      if (isService === 'viewCoverage') {
        that.vehicleInformationUrl = that.vehicleInformationViewCoverageUrl;
      } else if (isService === 'viewRepairHistory') {
        that.vehicleInformationUrl = that.vehicleInformationViewRepairHistoryUrl;
      }
      if (searchFormData.marketingmake.length !== 0) {
        that.brandModel = searchFormData.marketingmake;
        that.chassisNumber = searchFormData.marketingchassisNo;
      } else if (searchFormData.brandmake.length !== 0) {
        that.brandModel = searchFormData.brandmake;
      } else {
        that.brandModel = null;
        that.chassisNumber = searchFormData.chassisNo;
      }
      if (!cachedData) {
          that.RESTAdapterService
            .get(that.vehicleInformationUrl, {
              params: {
                companyNo: userCompanyNo,
                regNo: searchFormData.regNo,
                chassisNo: that.chassisNumber,
                chassisSeries: searchFormData.chassisSeries,
                vin: searchFormData.vin,
                make: that.brandModel,
                marketingType: searchFormData.marketingType,
                unitNo: searchFormData.unitNo
            }
            }).then((result: any) => {
            var documents: Array< VehicleInformationModel > = result.vehicleInformations;
            that.CacheService.put(that.vehicleInformationCacheKey, documents);
            deferred.resolve(documents);
            //Publish the results with 'printcreditdocument:SearchCreditDocuments' to listDocumentController
            that.dispatcherService.publish('vehicleInformation:getVehicleInformation', {vehicleInformationSearchResults: documents});
          }, (err) => {
            deferred.reject('Error getting the Credit Documents: ' + err);
          });

      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedData);
        //Publish the results with cached Data 'printcreditdocument:SearchCreditDocuments' to listDocumentController
        that.dispatcherService.publish('vehicleInformation:getVehicleInformation', {vehicleInformationSearchResults: cachedData});
      }
      //Publish the results with deferred promise 'printcreditdocument:SearchCreditDocuments' to listDocumentController
      that.dispatcherService.publish('vehicleInformation:getVehicleInformation', {vehicleInformationSearchResults: deferred.promise});
      return deferred.promise;
    }


    getMultipleVehicleInformations(chassisNo, chassisSeries, isService): ng.IPromise <{ }> {
      var deferred = this.$q.defer();
      var that = this;
      var userCompanyNo = that.SelectCompaniesStore.getSelectedCompany();
      var cachedData: any = null;
      if (isService === 'viewCoverage') {
        that.vehicleInformationUrl = that.vehicleInformationViewCoverageUrl;
      } else if (isService === 'viewRepairHistory') {
        that.vehicleInformationUrl = that.vehicleInformationViewRepairHistoryUrl;
      }
       if (!cachedData) {
        that.RESTAdapterService
          .get(that.vehicleInformationUrl, {
            params: {
              companyNo: userCompanyNo,
              chassisNo: chassisNo,
              chassisSeries: chassisSeries
            }
          }).then((result: any) => {
          var documents: Array< VehicleInformationModel > = result.vehicleInformations;
          that.CacheService.put(that.vehicleInformationCacheKey, documents);
          deferred.resolve(documents);
          //Publish the results with 'printcreditdocument:SearchCreditDocuments' to listDocumentController
          that.dispatcherService.publish('vehicleInformation:getMultipleVehicleInfomations',
            {vehicleInformationSearchResults: documents});
        }, (err) => {
          deferred.reject('Error getting the Credit Documents: ' + err);
        });

      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedData);
        //Publish the results with cached Data 'printcreditdocument:SearchCreditDocuments' to listDocumentController
        that.dispatcherService.publish('vehicleInformation:getMultipleVehicleInfomations',
          {vehicleInformationSearchResults: cachedData});
      }
      //Publish the results with deferred promise 'printcreditdocument:SearchCreditDocuments' to listDocumentController
      that.dispatcherService.publish('vehicleInformation:getMultipleVehicleInfomations',
        {vehicleInformationSearchResults: deferred.promise});
      return deferred.promise;
    }

    }

  angular
    .module('uchpClientAngularApp')
    .service('VehicleInformationStore', VehicleInformationStore);
}
