class PingFederateConfig implements ng.IServiceProvider {
  private clientId: string = 'UCHPQA';
  private pingImplicitLoginUrl: string = 'https://federate-qa.volvo.com/as/authorization.oauth2?pfidpadapterid=FromWAMVangQA';
  private pingImplicitLogoutUrl: string = 'https://federate-qa.volvo.com/idp/startSLO.ping';
  private redirectUrl: string = './authenticated/finish.html';

  private helloRef: HelloJSStatic = undefined;
  private serviceDefinition: any = {
    pingFederate: {
      name: 'ping-federate',
      oauth: {
        version: 2,
        auth: this.pingImplicitLoginUrl
      },
      refresh: true,
      scope: {
        basic: 'profile'
      },
      scope_delim: ' ',
      logout: this.pingImplicitLogoutUrl
    }
  };

  private loginOptions: HelloJSLoginOptions = {
    redirect_uri: this.redirectUrl,
    display: 'popup'
  };

  static $inject = ['$injector'];

  constructor(private $injector: angular.auto.IInjectorService) {
    this.$get.$inject = [
      '$log'
    ];
  }

  $get($log: ng.ILogService): HelloJSStatic {
    this.helloRef = hello;
    this.helloRef.init(this.serviceDefinition);
    this.helloRef.init({pingFederate: this.clientId}, this.loginOptions);
    $log.debug('HelloJS initialized');
    return this.helloRef;
  }
}

angular
  .module('uchpClientAngularApp')
  .provider('PingFederateConfig', PingFederateConfig);
