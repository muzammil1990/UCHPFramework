// Karma configuration
// http://karma-runner.github.io/0.10/config/configuration-file.html

module.exports = function(config) {
  config.set({
    // base path, that will be used to resolve files and exclude
    basePath: '',

    // testing framework to use (jasmine/mocha/qunit/...)
    frameworks: ['jasmine', 'sinon'],

    // list of files / patterns to load in the browser
    files: [
      // bower:js
      'client/bower_components/jquery/dist/jquery.js',
      'client/bower_components/angular/angular.js',
      'client/bower_components/angular-animate/angular-animate.js',
      'client/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
      'client/bower_components/angular-ui-router/release/angular-ui-router.js',
      'client/bower_components/angular-breadcrumb/release/angular-breadcrumb.js',
      'client/bower_components/angular-cookies/angular-cookies.js',
      'client/bower_components/moment/moment.js',
      'client/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js',
      'client/bower_components/angular-eonasdan-datetimepicker/dist/angular-eonasdan-datetimepicker.js',
      'client/bower_components/angular-loading-bar/build/loading-bar.js',
      'client/bower_components/angular-messages/angular-messages.js',
      'client/bower_components/angular-resource/angular-resource.js',
      'client/bower_components/angular-sanitize/angular-sanitize.js',
      'client/bower_components/angular-translate/angular-translate.js',
      'client/bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js',
      'client/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js',
      'client/bower_components/angular-translate-storage-local/angular-translate-storage-local.js',
      'client/bower_components/angular-ui-grid/ui-grid.js',
      'client/bower_components/angular-ui-grid/ui-grid.min.js',
      'client/bower_components/angular-ui-router.stateHelper/statehelper.js',
      'client/bower_components/file-saver/FileSaver.js',
      'client/bower_components/isteven-angular-multiselect/isteven-multi-select.js',
      'client/bower_components/lodash/lodash.js',
      'client/bower_components/postal/lib/postal.js',
      'client/bower_components/postal.request-response/lib/postal.request-response.min.js',
      'client/bower_components/postal.request-response/lib/postal.request-response.js',
      'client/bower_components/moment-timezone/builds/moment-timezone-with-data-2010-2020.js',
      'client/bower_components/angular-moment/angular-moment.js',
      'client/bower_components/contra/dist/contra.js',
      'client/bower_components/angular-jwt/dist/angular-jwt.js',
      'client/bower_components/hello/dist/hello.all.js',
      'client/bower_components/angular-translate-loader-partial/angular-translate-loader-partial.js',
      'client/bower_components/angular-toastr/dist/angular-toastr.tpls.js',
      'client/bower_components/angular-local-storage/dist/angular-local-storage.js',
      'client/bower_components/ng-focus-if/focusIf.js',
      'client/bower_components/nya-bootstrap-select/dist/js/nya-bs-select.js',
      'client/bower_components/angular-bootstrap-confirm/dist/angular-bootstrap-confirm.js',
      'client/bower_components/angular-base64-upload/src/angular-base64-upload.js',
      'client/bower_components/angular-mocks/angular-mocks.js',
      // endbower
      'client/app/app.js',
      'client/{app,components}/**/!(*.spec).js',
      'client/{app,components}/**/*.html',
      '.tmp/test/**/*.js'
    ],

    preprocessors: {
      '**/*.html': 'ng-html2js',
    },

    ngHtml2JsPreprocessor: {
      stripPrefix: 'client/'
    },

    // list of files / patterns to exclude
    exclude: [],

    // web server port
    port: 8090,

    client: {
      captureConsole: true
    },

    // level of logging
    // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
    logLevel: config.LOG_DEBUG,

    // reporter types:
    // - dots
    // - progress (default)
    // - spec (karma-spec-reporter)
    // - junit
    // - growl
    // - coverage
    reporters: ['spec'],

    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: false,

    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: ['Chrome'],

    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: true
  });
};
