/// <reference path="../../_all.ts" />

module uchpClientAngularApp {

  'use strict';

  /**
   * 
   * 
   * @export
   * @class DispatcherService
   * @implements {IDispatcherService}
   */
  export class DispatcherService implements IDispatcherService {
   /* static $inject = [
      '$q',
      '$log',
      'dispatcher'
    ];*/


    /**
     * Creates an instance of DispatcherService.
     * 
     * @param {ng.IQService} $q
     * @param {ng.ILogService} $log
     * @param {*} dispatcher
     * 
     * @memberOf DispatcherService
     */
    constructor(private $q: ng.IQService,
                private $log: ng.ILogService,
                private dispatcher: any) {
      // TODO
      // there are typings definitions for postal but not for extension postal:request-reply
      // for now we will set this global dependency via provider
      this.$log.debug('Initializing Dispatcher service...');

      /**
       * 
       * 
       * @returns
       */
      this.dispatcher.configuration.promise.createDeferred = () => {
        return $q.defer();
      };

      /**
       * 
       * 
       * @param {*} deferred
       * @returns
       */
      this.dispatcher.configuration.promise.getPromise = (deferred: any) => {
        return deferred.promise;
      };

      this.dispatcher.configuration.promise.fail = 'reject';

    }

    /**
     * 
     * 
     * @param {string} topic
     * @param {*} message
     * 
     * @memberOf DispatcherService
     */
    publish(topic: string, message: any) {
      var envelope = {
        topic: topic,
        data: message
      };
      this.dispatcher.publish(envelope);
    }

    /**
     * 
     * 
     * @param {string} topic
     * @param {ng.IPromise<{}>} callback
     * @returns {*}
     * 
     * @memberOf DispatcherService
     */
    subscribe(topic: string, callback: ng.IPromise<{}>): any {
      var options = {
        topic: topic,
        callback: callback
      };
      return this.dispatcher.subscribe(options, callback);
    }

    /**
     * 
     * 
     * @param {string} topic
     * @param {*} message
     * @returns
     * 
     * @memberOf DispatcherService
     */
    publishAndWaitForReply(topic: string, message: any) {
      var channel = this.dispatcher.channel();
      return channel.request({
        topic: topic,
        data: message
      });
    }

  }

}
