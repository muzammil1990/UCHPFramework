/**
 * Created by a232625 on 2016-08-02.
 */
module uchpClientAngularApp {
  'use strict';
  class ClaimJobItemAttachmentController {
    private claimJobTabSubscription: any;
    private files: Array<any> = [];
    private attachmentList: Array<any> = [];
 //   private fresh: any;
    private uploadFile: any;
    private tempList: any;

    static $inject = [
      'ClaimJobItemStore',
      'NewClaimService',
      '$log',
      '$scope',
      'DispatcherService'
    ];

    constructor(private ClaimJobItemStore: IClaimJobItemStore,
                private NewClaimService: NewClaimService,
                private $log: ng.ILogService,
                private $scope: ng.IScope,
                private DispatcherService: IDispatcherService
    ) {
      var that = this;
      that.files = [];
      let attachmentList = [];
      that.tempList = attachmentList;

      that.uploadFile = function(e, fileObjects, files) {
        if (fileObjects && fileObjects.length > 0) {
          for (var i = 0; i < fileObjects.length; i++) {
            let file = fileObjects[i];
            // {
            //   $$hashKey: "object:266",
            //   base64: "/9j/4AAQSkZJRgABAQEASABIAAD/4RN....",
            //   filename:"1_4NewClaimJob_Material.jpg",
            //   filesize: 130564,
            //   filetype: "image/jpeg"
            // }
            attachmentList.push({
              attachment: file.base64, // byte Convert file to Base64 https://github.com/adonespitogo/angular-base64-upload
              attachmentName: file.filename, // name
              changed: !1,
              claimDocumentId: '',
              claimJobSuppAttId: '',
              claimJobSuppId: 0,
              clean: !1,
              companyNo: 0,
              deleted: !1,
              deletedInGUI: !1,
              mimeType: file.filetype, // type
              new: !1
            });
          }
        }
      };
      that.claimJobTabSubscription = that.DispatcherService.subscribe('claimJob:getClaimJobItemAttachment',
        that.claimJobResultHandler.bind(this));
      that.$scope.$on('$stateChangeStart', function () {
        that.claimJobTabSubscription.unsubscribe();
      });
    }
    // comma separated wildcard to filter file names and types allowed
    pattern = '.pdf,.xls,.xlsx,.doc,.docx,.jpg,.jpeg,.zip';
    // 500kb, 4 files, bytestream standard HTML accept attr, browser specific select
    // popup window
    accept = '.pdf, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, applica' +
      'tion/vnd.ms-excel, image/jpeg, image/jpg, application/vnd.openxmlformats-officedocument.wor' +
      'dprocessingml.document, application/msword, application/zip,application/x-zip,ap' +
      'plication/x-zip-compressed';

    maxFiles = '4';

    maxSize = '2000';


    claimJobResultHandler(claimJob) {
      var that = this;
      that.attachmentList = claimJob.claimSupplementDTO.attachmentList;
    }

    removeFile($index) {
      var that = this;
      that.tempList.splice($index, 1);
      that.attachmentList.splice($index, 1);
    }

    uploadFiles() {
      var that = this;
      that.attachmentList = that.tempList;
      that.updateReadyObject();
    }

    updateReadyObject() {
      var that = this;
      that.NewClaimService.addAttachments(that.attachmentList);
      that.DispatcherService.publish('claimJob:SaveEnabled', {});
    }
  }

  angular
    .module('uchpClientAngularApp')
    .component('claimJobItemAttachment', {
      bindings: {
        fresh: '<'
      },
      controller: [
        'ClaimJobItemStore',
        'NewClaimService',
        '$log',
        '$scope',
        'DispatcherService',
        ClaimJobItemAttachmentController
      ],
      controllerAs: 'ctrl',
      templateUrl: 'components/claim/job/claim-job-item/claim-job-item-attachment/claim-job-item-attachment.html'
    });
}
