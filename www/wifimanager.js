/**
 * JavaScript interface to abstract
 * the usage of the cordova Sygic Navigation plugin.
 *
 * @module com/dff/cordova/plugins/sygic
 */

'use strict';

var cordova = require('cordova');
function WifiManager(feature) {
    this.feature = feature;
};

/*
 * Register callbacks for wifimanager log messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.onLog = function (success, error) {
    cordova.exec(success, error, this.feature, "onLog", []);
};

/*
 * Register callbacks for wifimanager onNetworkStateChanged messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.onNetworkStateChanged = function (success, error) {
    cordova.exec(success, error, this.feature, "onNetworkStateChanged", []);
};

/*
 * Register callbacks for wifimanager onWifiStateChanged messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.onWifiStateChanged = function (success, error) {
    cordova.exec(success, error, this.feature, "onWifiStateChanged", []);
};

/*
 * Register callbacks for wifimanager onScanResultsAvaiblable messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.onScanResultsAvailable = function (success, error) {
    cordova.exec(success, error, this.feature, "onScanResultsAvaiblable", []);
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
WifiManager.prototype.addNetwork = function (success, error, args) {
    cordova.exec(success, error, this.feature, "addNetwork", [args]);
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
WifiManager.prototype.calculateSignalLevel = function (success, error, args) {
    cordova.exec(success, error, this.feature, "calculateSignalLevel", [args]);
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
WifiManager.prototype.compareSignalLevel = function (success, error, args) {
    cordova.exec(success, error, this.feature, "compareSignalLevel", [args]);
}

/*
 * Disable a configured network.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @param {Object}   args            - An object containing the parameters.
 * @param {Number}   args.netId      - Id of network to disable.
 */
WifiManager.prototype.disableNetwork = function (success, error, args) {
    cordova.exec(success, error, this.feature, "disableNetwork", [args]);
};

/*
 * Disassociate from the currently active access point.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.disconnect = function (success, error) {
    cordova.exec(success, error, this.feature, "disconnect", []);
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
WifiManager.prototype.enableNetwork = function (success, error, args) {
    cordova.exec(success, error, this.feature, "enableNetwork", [args]);
};

/*
 * Return a list of all the networks configured in the supplicant. Not all fields of WifiConfiguration are returned.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.getConfiguredNetworks = function (success, error, args) {
    cordova.exec(success, error, this.feature, "getConfiguredNetworks", [args]);
};

/*
 * Register callbacks for wifimanager getConnectionInfo messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.getConnectionInfo = function (success, error) {
    cordova.exec(success, error, this.feature, "getConnectionInfo", []);
};

/*
 * Return the DHCP-assigned addresses from the last successful DHCP request, if any.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.getDhcpInfo = function (success, error) {
    cordova.exec(success, error, this.feature, "getDhcpInfo", []);
};

/*
 * Register callbacks for wifimanager getScanResults messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.getScanResults = function (success, error) {
    cordova.exec(success, error, this.feature, "getScanResults", []);
};

/*
 * Register callbacks for wifimanager getWifiState messages.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.getWifiState = function (success, error) {
    cordova.exec(success, error, this.feature, "getWifiState", []);
};

/**
 * Check if scanning is always available.
 * success callback result contains "isScanAlwaysAvailable" that indicates
 * if apps can issue startScan event if Wi-Fi is turned off.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.isScanAlwaysAvailable = function (success, error) {
    cordova.exec(success, error, this.feature, "isScanAlwaysAvailable", []);
};

/**
 * Callback result contains "enabled" that indicates whether Wi-Fi is
 * enabled or disabled.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.isWifiEnabled = function (success, error) {
    cordova.exec(success, error, this.feature, "isWifiEnabled", []);
};

/**
 * Check that the supplicant daemon is responding to requests.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.pingSupplicant = function (success, error) {
    cordova.exec(success, error, this.feature, "pingSupplicant", []);
};

/**
 * Reconnect to the currently active access point,
 * even if we are already connected.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.reassociate = function (success, error) {
    cordova.exec(success, error, this.feature, "reassociate", []);
};

/**
 * Reconnect to the currently active access point,
 * if we are currently disconnected.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.reconnect = function (success, error) {
    cordova.exec(success, error, this.feature, "reconnect", []);
};

/**
 * Remove the specified network from the list of configured networks.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @param {Object}   args            - An object containing the parameters.
 * @param {Number}   args.netId      - Id of network to disable.
 */
WifiManager.prototype.removeNetwork = function (success, error) {
    cordova.exec(success, error, this.feature, "removeNetwork", []);
};

/**
 *  Tell the supplicant to persist the current list of configured networks.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 */
WifiManager.prototype.saveConfiguration = function (success, error) {
    cordova.exec(success, error, this.feature, "saveConfiguration", []);
};

/*
 * Allow a previously configured network to be associated with.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @param {Object}   args            - An object containing the parameters.
 * @param {Number}   args.enabled    - true to enable, false to disable.
 */
WifiManager.prototype.setWifiEnabled = function (success, error, args) {
    cordova.exec(success, error, this.feature, "setWifiEnabled", [args]);
};

/**
 * Request a scan for access points.
 * Result will be available via event later.
 *
 * @param {Function} success         - Callback if action is successful.
 * @param {Function} error           - Callback if action is not successful.
 * @return {[type]}         [description]
 */
WifiManager.prototype.startScan = function (success, error) {
    cordova.exec(success, error, this.feature, "startScan", [args]);
};

module.exports = new WifiManager("WifiManager");
