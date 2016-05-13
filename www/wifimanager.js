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
 * Add a new network description to the set of configured networks.
 * The networkId field of the supplied configuration object is ignored.
 * The new network will be marked DISABLED by default.
 * To enable it, called enableNetwork(int, boolean).
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @param {Object}   args            - An object containing the parameters.
 */
self.addNetwork = function (success, error, args) {
    cordova.exec(success, error, feature, "addNetwork", [args]);
}


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
 * Disable a configured network.
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
 * Disassociate from the currently active access point.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.disconnect = function (success, error) {
    cordova.exec(success, error, feature, "disconnect", []);
};

/*
 * Allow a previously configured network to be associated with.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @param {Object}   args            - An object containing the parameters.
 * @param {Number}   args.netId      - Id of network to enable.
 * @param {Number}   args.disableOthers -  if true, disable all other networks.
 * The way to select a particular network to connect to is specify true for this parameter.
 */
self.enableNetwork = function (success, error, args) {
    cordova.exec(success, error, feature, "enableNetwork", [args]);
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
 * Return the DHCP-assigned addresses from the last successful DHCP request, if any.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.getDhcpInfo = function (success, error) {
    cordova.exec(success, error, feature, "getDhcpInfo", []);
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

/**
 * Check if scanning is always available.
 * success callback result contains "isScanAlwaysAvailable" that indicates
 * if apps can issue startScan event if Wi-Fi is turned off.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.isScanAlwaysAvailable = function (success, error) {
    cordova.exec(success, error, feature, "isScanAlwaysAvailable", []);
};

/**
 * Callback result contains "enabled" that indicates whether Wi-Fi is
 * enabled or disabled.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.isWifiEnabled = function (success, error) {
    cordova.exec(success, error, feature, "isWifiEnabled", []);
};

/**
 * Check that the supplicant daemon is responding to requests.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.pingSupplicant = function (success, error) {
    cordova.exec(success, error, feature, "pingSupplicant", []);
};

/**
 * Reconnect to the currently active access point,
 * even if we are already connected.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.reassociate = function (success, error) {
    cordova.exec(success, error, feature, "reassociate", []);
};

/**
 * Reconnect to the currently active access point,
 * if we are currently disconnected.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.reconnect = function (success, error) {
    cordova.exec(success, error, feature, "reconnect", []);
};

/**
 * Remove the specified network from the list of configured networks.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @param {Object}   args            - An object containing the parameters.
 * @param {Number}   args.netId      - Id of network to disable.
 */
self.removeNetwork = function (success, error) {
    cordova.exec(success, error, feature, "removeNetwork", []);
};

/**
 *  Tell the supplicant to persist the current list of configured networks.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
self.saveConfiguration = function (success, error) {
    cordova.exec(success, error, feature, "saveConfiguration", []);
};

/*
 * Allow a previously configured network to be associated with.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @param {Object}   args            - An object containing the parameters.
 * @param {Number}   args.enabled    - true to enable, false to disable.
 */
self.setWifiEnabled = function (success, error, args) {
    cordova.exec(success, error, feature, "setWifiEnabled", [args]);
};

/**
 * Request a scan for access points.
 * Result will be available via event later.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @return {[type]}         [description]
 */
self.startScan = function (success, error) {
    cordova.exec(success, error, feature, "startScan", [args]);
};

module.exports = self;
