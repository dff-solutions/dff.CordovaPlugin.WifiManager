package com.dff.cordova.plugin.wifimanager;

import org.apache.cordova.CallbackContext;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.model.WifiState;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetworkConnectivityReceiver extends BroadcastReceiver {
	private static final String LOG_TAG = "com.dff.cordova.plugin.wifimanager.NetworkConnectivityReceiver";
	
	protected WifiState previousWifiState;
	protected WifiState wifiState;
	
	protected CallbackContext onNetworkStateChangedCallbackContext;
	protected CallbackContext onWifiStateChangedCallbackContext;

	public void setNetworkStateChangedCallbackContext(CallbackContext callbackContext) {
		this.onNetworkStateChangedCallbackContext = callbackContext;
	}	
	
	public void setWifiStateChangedCallbackContext(CallbackContext callbackContext) {
		this.onWifiStateChangedCallbackContext = callbackContext;
	}	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		CordovaPluginLog.d(LOG_TAG, "Action Received: " + action + " From intent: " + intent);
		
		if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
			Intent networkStateIntent = new Intent(WifiManagerPlugin.NETWORK_STATE_CHANGED_ACTION);
			
			if (intent.hasExtra(WifiManager.EXTRA_NETWORK_INFO)) {
				NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
				
				if (networkInfo != null) {
					CordovaPluginLog.d(LOG_TAG, WifiManager.EXTRA_NETWORK_INFO + ": " + networkInfo.toString());
					networkStateIntent.putExtra("networkInfo", networkInfo);
				}				
			}
			
			if (intent.hasExtra(WifiManager.EXTRA_BSSID)) {
				String bssid = intent.getStringExtra(WifiManager.EXTRA_BSSID);
				CordovaPluginLog.d(LOG_TAG, WifiManager.EXTRA_BSSID + ": " + bssid);
				networkStateIntent.putExtra(WifiManager.EXTRA_BSSID, bssid);
			}
			
			if (intent.hasExtra(WifiManager.EXTRA_WIFI_INFO)) {
				WifiInfo wifiInfo = intent.getParcelableExtra(WifiManager.EXTRA_WIFI_INFO);
				CordovaPluginLog.d(LOG_TAG, WifiManager.EXTRA_WIFI_INFO + ": " + wifiInfo.toString());
				networkStateIntent.putExtra(WifiManager.EXTRA_WIFI_INFO, wifiInfo);
			}
			
			context.sendBroadcast(networkStateIntent);
		}
		else if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
			if (intent.hasExtra(WifiManager.EXTRA_WIFI_STATE)) {
				wifiState = WifiState.getInstance(intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN));
				
				CordovaPluginLog.d(LOG_TAG, "wifi state: " +  wifiState + " " + wifiState);
			}
			
			if (intent.hasExtra(WifiManager.EXTRA_PREVIOUS_WIFI_STATE)) {
				previousWifiState = WifiState.getInstance(intent.getIntExtra(WifiManager.EXTRA_PREVIOUS_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN));
				
				CordovaPluginLog.d(LOG_TAG, "previousWifiState: " +  previousWifiState + " " + previousWifiState);
			}
		}
	}
}
