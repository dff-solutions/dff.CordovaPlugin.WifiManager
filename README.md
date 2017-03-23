# dff.CordovaPlugin.WifiManager

cordova wifi manager plugin

Basically abstracts the functionality of
[WifiManager](https://developer.android.com/reference/android/net/wifi/WifiManager.html)

## Supported Platforms
  * Android

## Installation
    cordova plugin add https://github.com/dff-solutions/dff.CordovaPlugin.WifiManager.git

## Usage

### Actions


#### onNetworkStateChanged

```js
/**
 * Register callback for wifimanager onNetworkStateChanged messages.
 *
 * @name onNetworkStateChanged
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .onNetworkStateChanged(function (networkState) {
        console.log(networkState);
    }, function (reason) {
        console.error(reason);
    });
```

#### onWifiStateChanged

```js
/**
 * Register callbacks for wifimanager onWifiStateChanged messages.
 *
 * @name onWifiStateChanged
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .onWifiStateChanged(function (wifiState) {
        consolve.log(wifiState);
    }, function (reason) {
        console.error(reason);
    });
```

#### onScanResultsAvailable

```js
/**
 * Register callbacks for wifimanager onScanResultsAvaiblable messages.
 *
 * @name onScanResultsAvailable
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .onScanResultsAvailable(function (scanResults) {
        console.log(scanResults);
    }, function (reason) {
        console.error(reason);
    });
```

#### addNetwork

<!--

```js
/**
 * Add a new network description to the set of configured networks.
 * The networkId field of the supplied configuration object is ignored.
 * The new network will be marked DISABLED by default.
 * To enable it, called enableNetwork(int, boolean).
 *
 * @name addNetwork
 * @param {function} success Success callback.
 * @param {function} error Error callback
 * @param {Object}   args Named parameters.
 */
WifiManager
    .addNetwork(function () {

    }, function (reason) {
        console.error(reason);
    }, {

    });
```

-->

#### calculateSignalLevel

```js
/**
 * Calculates the level of the signal. This should be used any time a signal is being shown.
 * Resolves with a level of the signal, given in the range of 0 to numLevels-1 (both inclusive).
 *
 * @name calculateSignalLevel
 * @param {function} success Success callback.
 * @param {function} error Error callback
 * @param {Object}   args Named parameters.
 * @param {Number}   args.rssi The power of the signal measured in RSSI.
 * @param {Number}   args.numLevels The number of levels to consider in the calculated level.
 */
WifiManager
    .calculateSignalLevel(function (level) {
        console.log(level);
    }, function (reason) {
        console.error(reason);
    }, {
        rssi: -70,
        numLevels: 4
    });
```

#### compareSignalLevel

```js
/**
 * Compares two signal strengths.
 * Resolves <0 if the first signal is weaker than the second signal,
 * 0 if the two signals have the same strength,
 * and >0 if the first signal is stronger than the second signal.
 *
 * @name compareSignalLevel
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 * @param {Object}   args Named parameters.
 * @param {Number}   args.rssiA The power of the first signal measured in RSSI.
 * @param {Number}   args.rssiB The power of the second signal measured in RSSI.
 */
WifiManager
    .compareSignalLevel(function (compare) {
        console.log(compare);
    }, function (reason) {
        console.error(reason);
    }, {
        rssiA: 3,
        rssiB: 5
    });
```

#### disableNetwork

```js
/**
 * Disable a configured network.
 * Resolves with 1 if successful else 0.
 *
 * @name disableNetwork
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 * @param {Object}   args Named parameters.
 * @param {Number}   args.netId Id of network to disable.
 */
WifiManager
    .disableNetwork(function (success) {
        console.log(success);
    }, function (reason) {
        console.error(reason);
    }, {
        netId: 3
    });
```

#### disconnect

```js
/**
 * Disassociate from the currently active access point.
 * Resolves with 1 if successful else 0.
 *
 * @name disconnect
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .disconnect(function (success) {
        console.log(success);
    }, function (reason) {
        console.error(reason);
    });
```

#### enableNetwork

```js
/**
 * Allow a previously configured network to be associated with.
 * Resolves with 1 if successful else 0.
 *
 * @name enableNetwork
 * @param {function} success Success callback.
 * @param {function} error Error callback
 * @param {Number}   args.netId Id of network to enable.
 * @param {Boolean}   args.disableOthers If true, disable all other networks.
 * The way to select a particular network to connect to is specify true for this parameter.
 */
WifiManager
    .enableNetwork(function (success) {
        console.log(success);
    }, function (reason) {
        console.error(reason);
    }, {
        netId: 9,
        disableOthers: true
    });
```

#### getConfiguredNetworks

```js
/**
 * Return a list of all the networks configured in the supplicant.
 * Not all fields of WifiConfiguration are returned.
 *
 * @name getConfiguredNetworks
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .getConfiguredNetworks(function (configurations) {
        console.log(configurations);
    }, function (reason) {
        console.error(reason);
    });
```

#### getConnectionInfo

```js
/**
 * Return dynamic information about the current Wi-Fi connection, if any is active.
 *
 * @name getConnectionInfo
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .getConnectionInfo(function (connectionInfo) {
        console.log(connectionInfo);
    }, function (reason) {
        console.error(reason);
    });
```

#### getDhcpInfo

```js
/**
 * Return the DHCP-assigned addresses from the last successful DHCP request, if any.
 *
 * @name getDhcpInfo
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .getDhcpInfo(function (dhcpInfo) {
        console.log(dhcpInfo);
    }, function (reason) {
        console.error(reason);
    });
```

#### getScanResults

```js
/**
 * Return the results of the latest access point scan.
 *
 * @name getScanResults
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .getScanResults(function (scanResults) {
        console.log(scanResults);
    }, function (reason) {
        console.error(reason);
    });
```

#### getWifiState

```js
/**
 * Gets the Wi-Fi enabled state.
 *
 * @name getWifiState
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .getWifiState(function (wifiState) {
        console.log(wifiState);
    }, function (reason) {
        console.error(reason);
    });
```

#### isScanAlwaysAvailable

```js
/**
 * Check if scanning is always available.
 * success callback result contains 1 or 0 that indicates
 * if apps can issue startScan event if Wi-Fi is turned off.
 *
 * @name isScanAlwaysAvailable
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .isScanAlwaysAvailable(function (isScanAlwaysAvailable) {
        console.log(!!isScanAlwaysAvailable);
    }, function (reason) {
        console.error(reason);
    });
```

#### isWifiEnabled

```js
/**
 * Callback result contains 1 or 0 that indicates whether Wi-Fi is
 * enabled or disabled.
 *
 * @name isWifiEnabled
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .isWifiEnabled(function (enabled) {
        console.log(!!enabled);
    }, function (reason) {
        console.error(reason);
    });
```

#### pingSupplicant

```js
/**
 * Check that the supplicant daemon is responding to requests.
 * Resolves with 1 if ping was successful. Else resolves with 0.
 *
 * @name pingSupplicant
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .pingSupplicant(function (success) {
        console.log(!!success);
    }, function (reason) {
        console.error(reason);
    });
```

#### reassociate

```js
/**
 * Reconnect to the currently active access point,
 * even if we are already connected.
 * Resolves with 1 if successful else 0.
 *
 * @name reassociate
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .reassociate(function (success) {
        console.log(!!success);
    }, function (reason) {
        console.error(reason);
    });
```

#### reconnect

```js
/**
 * Reconnect to the currently active access point, even if we are already connected.
 * This may result in the asynchronous delivery of state change events.
 * Resolves with 1 if successful else 0.
 *
 * @name reconnect
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .reconnect(function (success) {
        console.log(success);
    }, function (reason) {
        console.error(reason);
    });
```

#### removeNetwork

```js
/**
 * Remove the specified network from the list of configured networks.
 * This may result in the asynchronous delivery of state change events.
 * Resolves with 1 if successful else 0.
 *
 * @name removeNetwork
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .removeNetwork(function (success) {
        console.log(success);
    }, function (reason) {
        console.error(reason);
    });
```

#### saveConfiguration

```js
/**
 * Tell the supplicant to persist the current list of configured networks.
 * Note: It is possible for this method to change the network IDs of existing networks.
 * You should assume the network IDs can be different after calling this method.
 * Resolves with 1 if successful else 0.
 *
 * @name saveConfiguration
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .saveConfiguration(function (success) {
        console.log(success);
    }, function (reason) {
        console.error(reason);
    });
```

#### setWifiEnabled

```js
/**
 * Enable or disable Wi-Fi.
 * Resolves with 1 if successful else 0.
 *
 * @name setWifiEnabled
 * @param {function} success Success callback.
 * @param {function} error Error callback.
 * @param {Object}   args Named parameters.
 * @param {Number}   args.enabled true to enable, false to disable.
 */
WifiManager
    .setWifiEnabled(function (success) {
        console.log(success);
    }, function (reason) {
        console.error(reason);
    }, {
        enabled: true
    });
```

#### startScan

```js
/**
 * Request a scan for access points.
 * The availability of the results is made known later by means of an asynchronous
 * event sent on completion of the scan.
 * Resolves with 1 if successful else 0.
 *
 * @name startScan
 * @param {function} success Success callback.
 * @param {function} error Error callback
 */
WifiManager
    .startScan(function (success) {
        console.log(success);
    }, function (reason) {
        console.error(reason);
    });
```

## Documentation
- <a href="https://dff-solutions.github.io/dff.CordovaPlugin.WifiManager/" target="_blank" >JAVA DOC</a>
