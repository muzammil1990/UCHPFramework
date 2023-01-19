/**
 * Created by a038509 on 26-08-2016.
 */
/// <reference path='../../_all.ts' />

module uchpClientAngularApp {
  'use strict';

  class SelectCompaniesStore implements ISelectCompaniesStore {
    private selectCompaniesCacheKey: String;
    private selectedCompany: String = '1';
    private companyChangeSubscription: any;

    /**
     *
     *
     * @static
     *
     * @memberOf SelectCompaniesStore
     */
    static $inject = [
      'CacheService',
      '$q',
      'DispatcherService',
      '$log'
    ];

    /**
     * Creates an instance of SelectCompaniesStore.
     *
     * @param {ICacheService} CacheService
     * @param {ng.IQService} $q
     * @param {IDispatcherService} DispatcherService
     * @param {ng.ILogService} $log
     *
     * @memberOf SelectCompaniesStore
     */
    constructor( private CacheService: ICacheService,
                 private $q: ng.IQService,
                 private DispatcherService: IDispatcherService,
                 private $log: ng.ILogService
    ) {
      var that = this;
      this.selectCompaniesCacheKey = 'SelectCompaniesCacheStoreService.selectCompanies';
      this.companyChangeSubscription = that.DispatcherService.subscribe(
        'change:Company',
        that.companyChangeHandler.bind(this));

      $log.debug('Initializing SelectCompaniesCacheStoreService...');
    }

    /**
     *
     *
     * @param {any} company
     * @returns {*}
     *
     * @memberOf SelectCompaniesStore
     */
    companyChangeHandler(company): any {
      this.selectedCompany = company;
    }

    /**
     *
     *
     * @returns {String}
     *
     * @memberOf SelectCompaniesStore
     */
    getSelectedCompany(): String {
      return this.selectedCompany;
    }

    /**
     *
     *
     * @returns {ng.IPromise<{}>}
     *
     * @memberOf SelectCompaniesStore
     */
    getSelectCompanies(): ng.IPromise<{}> {
      var deferred = this.$q.defer();
      //tries to get cached data
      // TODO: get this array from backend, get logo images or remove it, best .png
      //var cachedSelectCompanies = this.CacheService.get(this.selectCompaniesCacheKey);
      var cachedSelectCompanies = [
        {
          icon: '<img src="https://upload.wikimedia.org/wikipedia/en/b/b9/Volvo_Trucks_%26_Bus_logo.jpg" width="32">',
          name: 'Volvo Truck Corporation',
          maker: 'VTC',
          id: '1',
          index: '0'
        }, {
          icon: '<img src="https://upload.wikimedia.org/wikipedia/en/b/b9/Volvo_Trucks_%26_Bus_logo.jpg" width="32">',
          name: 'Volvo Bus Corporation',
          maker: 'VBC',
          id: '2',
          index: '1'
         }, { icon:
          `<img 
            src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTi8Ndl00y8TUaxDoRBeWA9oyuv3GRNmTnJZn3AS12cERqJV5Xk5ZI" 
            width="32">`,
          name: 'Volvo Construction Equipment',
          maker: 'VCE',
          id: '3',
          index: '2'
        }, {
          icon: '<img src="https://upload.wikimedia.org/wikipedia/en/b/b9/Volvo_Trucks_%26_Bus_logo.jpg" width="32">',
          name: 'TSM Americas',
          maker: 'NAT',
          id: '4',
          index: '3'
         }, {
          icon: '<img src="https://upload.wikimedia.org/wikipedia/en/1/1b/UD_Nissan_Diesel_trucks_logo.png" width="32">',
          name: 'UD Trucks Corporation',
          maker: 'UDD',
          id: '5',
          index: '4'
        }, {
          icon:
          `<img
            src="http://www.wigmorewrightmarine.co.uk/wp-content/uploads/2015/03/VP_logo_Stacked_VolvoBlue_Large.jpg"
            width="32">`,
          name: 'Volvo Penta Corporation',
          maker: 'VPC',
          id: '6',
          index: '5'
        }
      ];
      //returns data from cache or server
      //TODO Cache Service
      if (!cachedSelectCompanies) {
        deferred.resolve(cachedSelectCompanies);
      } else {
        // wrap local cached data into promise object
        deferred.resolve(cachedSelectCompanies);
      }
      return deferred.promise;
    }
  }
  angular
    .module('uchpClientAngularApp')
    .service('SelectCompaniesStore', SelectCompaniesStore);
}
