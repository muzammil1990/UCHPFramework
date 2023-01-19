import IRootScopeService = angular.IRootScopeService;
import IHttpBackendService = angular.IHttpBackendService;
import ICompileService = angular.ICompileService;
/**
 * Created by a232625 on 2016-07-12.
 */

/// <reference path="../../../app/_all.ts" />

describe('Component: PrintCreditDocument', () => {
  var $componentController, compile, rootScope, httpBackend;
  beforeEach(angular.mock.module('uchpClientAngularApp'));

  beforeEach(inject(($injector: IInjectorService, _$componentController_: any, $compile: ICompileService, $rootScope: IRootScopeService, $httpBackend: IHttpBackendService) => {
      $componentController = _$componentController_;
      compile = $compile;
      rootScope = $rootScope;
      httpBackend = $httpBackend;
    })
  );

  it('should exist', () => {
    var bindings = {};
    var ctrl = $componentController('printCreditDocument', null, bindings);
    expect(ctrl).toBeDefined();
  });

  it('should exist and render', () => {
    var httpMock;
    httpMock = httpBackend;
    var documentResponse = [{
    }];
    var componentHtmlTemplate = '<p class="page-title">Print credit document</p>' +
      '<print-credit-document-search-filter></print-credit-document-search-filter>' +
      '<print-credit-document-list></print-credit-document-list>';

    httpMock.when('GET', /i18n/).respond('');

    httpBackend.whenGET(/uchp-uiservice-rest\/api\/crediting\/creditdocuments\/creditingdocuments/).respond(function () {
      return documentResponse;
    }());

    httpBackend.whenGET(/components\/crediting\/print-credit-document\/print-credit-document-search-filter/).respond(function () {
      return componentHtmlTemplate;
    }());

    var scope = rootScope.$new();
    var element = angular.element('<print-credit-document-search-filter></print-credit-document-search-filter>');

    compile(element)(scope);

    scope.$digest();

    expect(element.hasClass('ng-scope')).toBeDefined(true);
  });

});
