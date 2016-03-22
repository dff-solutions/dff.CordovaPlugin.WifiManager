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
 * Calculates the level of the signal. This should be used any time a signal is being shown.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @param {Object}   args            - An object containing the parameters.
 * @param {Number}   args.rssi       -  The power of the signal measured in RSSI.
 * @param {Number}   args.numLevels  -  The number of levels to consider in the calculated level.
 */
self.calculateSignalLevel = function (success, error, args) {
    cordova.exec(success, error, feature, "calculateSignalLevel", [args]);
}

/*
 * Compares two signal strengths
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @param {Object}   args            - An object containing the parameters.
 * @param {Number}   args.rssiA      - The power of the first signal measured in RSSI.
 * @param {Number}   args.rssiB      - The power of the second signal measured in RSSI.
 */
self.compareSignalLevel = function (success, error, args) {
    cordova.exec(success, error, feature, "compareSignalLevel", [args]);
}

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

/*
 * Return a list of all the networks configured in the supplicant. Not all fields of WifiConfiguration are returned.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.getConfiguredNetworks = function (success, error, args) {
    cordova.exec(success, error, feature, "getConfiguredNetworks", [args]);
};



module.exports = self;
