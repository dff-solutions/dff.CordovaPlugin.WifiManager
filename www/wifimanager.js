/**
 * JavaScript interface to abstract
 * the usage of the cordova Sygic Navigation plugin.
 *
 * @module com/dff/cordova/plugins/sygic
 */

'use strict';

var cordova = require('cordova');
var feature = "WifiManager";
var self = {};

/*
 * Register callbacks for wifimanager log messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.onLog = function (success, error) {
    cordova.exec(success, error, feature, "onLog", []);
};

/*
 * Register callbacks for wifimanager onNetworkStateChanged messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.onNetworkStateChanged = function (success, error) {
    cordova.exec(success, error, feature, "onNetworkStateChanged", []);
};

/*
 * Register callbacks for wifimanager onWifiStateChanged messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.onWifiStateChanged = function (success, error) {
    cordova.exec(success, error, feature, "onWifiStateChanged", []);
};

/*
 * Register callbacks for wifimanager onScanResultsAvaiblable messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.onScanResultsAvailable = function (success, error) {
    cordova.exec(success, error, feature, "onScanResultsAvaiblable", []);
};

/*
 * Register callbacks for wifimanager getConnectionInfo messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.getConnectionInfo = function (success, error) {
    cordova.exec(success, error, feature, "getConnectionInfo", []);
};

/*
 * Register callbacks for wifimanager getScanResults messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.getScanResults = function (success, error) {
    cordova.exec(success, error, feature, "getScanResults", []);
};

/*
 * Register callbacks for wifimanager getWifiState messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.getWifiState = function (success, error) {
    cordova.exec(success, error, feature, "getWifiState", []);
};

/*
 * Register callbacks for wifimanager disableNetwork messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @param {Object}   args            - An object containing the parameters.
 * @param {Number}   args.netId      - Id of network to disable.
 */
self.disableNetwork = function (success, error, args) {
    cordova.exec(success, error, feature, "disableNetwork", [args]);
};

module.exports = self;
