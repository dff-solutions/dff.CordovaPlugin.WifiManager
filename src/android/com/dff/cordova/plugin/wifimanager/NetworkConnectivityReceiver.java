package com.dff.cordova.plugin.wifimanager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.model.JsonNetworkInfo;
import com.dff.cordova.plugin.wifimanager.model.JsonWifiInfo;
import com.dff.cordova.plugin.wifimanager.model.WifiState;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetworkConnectivityReceiver extends BroadcastReceiver {	
	private static final String LOG_TAG = "com.dff.cordova.plugin.wifimanager.NetworkConnectivityReceiver";
	
	protected CallbackContext networStateCallbackcontext;
	public CallbackContext getNetworStateCallbackcontext() {
		return networStateCallbackcontext;
	}

	public void setNetworStateCallbackcontext(CallbackContext networStateCallbackcontext) {
		this.networStateCallbackcontext = networStateCallbackcontext;
	}

	public CallbackContext getWifistateCallbackcontext() {
		return wifistateCallbackcontext;
	}

	public void setWifistateCallbackcontext(CallbackContext wifistateCallbackcontext) {
		this.wifistateCallbackcontext = wifistateCallbackcontext;
	}

	protected CallbackContext wifistateCallbackcontext;
	
	public NetworkConnectivityReceiver(Activity activity) {
		IntentFilter networkStateFilter = new IntentFilter();
		networkStateFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		networkStateFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		
		activity.registerReceiver(this, networkStateFilter);
	}
	
	public void onDestroy() {
		if (this.networStateCallbackcontext != null) {
			this.networStateCallbackcontext.success();
		}
		
		if (this.wifistateCallbackcontext != null) {
			this.wifistateCallbackcontext.success();
		}
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		CordovaPluginLog.d(LOG_TAG, "Action Received: " + action + " From intent: " + intent);
		
		if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
			JSONObject jsonNetworkState = new JSONObject();
			try {
			
				if (intent.hasExtra(WifiManager.EXTRA_NETWORK_INFO)) {
					NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
					
					if (networkInfo != null) {
						CordovaPluginLog.d(LOG_TAG, WifiManager.EXTRA_NETWORK_INFO + ": " + networkInfo.toString());
						jsonNetworkState.put(WifiManager.EXTRA_NETWORK_INFO, JsonNetworkInfo.getInstance(networkInfo).toJson());
					}				
				}
				
				if (intent.hasExtra(WifiManager.EXTRA_BSSID)) {
					String bssid = intent.getStringExtra(WifiManager.EXTRA_BSSID);
					CordovaPluginLog.d(LOG_TAG, WifiManager.EXTRA_BSSID + ": " + bssid);
					jsonNetworkState.put(WifiManager.EXTRA_BSSID, bssid);
				}
				
				if (intent.hasExtra(WifiManager.EXTRA_WIFI_INFO)) {
					WifiInfo wifiInfo = intent.getParcelableExtra(WifiManager.EXTRA_WIFI_INFO);
					CordovaPluginLog.d(LOG_TAG, WifiManager.EXTRA_WIFI_INFO + ": " + wifiInfo.toString());
					jsonNetworkState.put(WifiManager.EXTRA_WIFI_INFO, JsonWifiInfo.getInstance(wifiInfo).toJson());
				}
				
				
				if (this.networStateCallbackcontext != null) {
					PluginResult networkStateChangedResult = new PluginResult(Status.OK, jsonNetworkState);
					networkStateChangedResult.setKeepCallback(true);
					this.networStateCallbackcontext.sendPluginResult(networkStateChangedResult);
				}
			}
			catch (JSONException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
				
				if (this.networStateCallbackcontext != null) {
					PluginResult networkStateChangedResult = new PluginResult(Status.JSON_EXCEPTION, e.getMessage());
					networkStateChangedResult.setKeepCallback(true);
					this.networStateCallbackcontext.sendPluginResult(networkStateChangedResult);
				}
			}
		}
		else if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
			JSONObject jsonWifiState = new JSONObject();
			try {
			
				if (intent.hasExtra(WifiManager.EXTRA_WIFI_STATE)) {
					int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
					WifiState wifiStateObj = WifiState.getInstance(wifiState);
					
					CordovaPluginLog.d(LOG_TAG, "wifi state: " +  wifiState + " " + wifiStateObj);
					jsonWifiState.put(WifiManager.EXTRA_WIFI_STATE, wifiState);
					jsonWifiState.put(WifiManager.EXTRA_WIFI_STATE + "Name", wifiStateObj.toString());
				}
				
				if (intent.hasExtra(WifiManager.EXTRA_PREVIOUS_WIFI_STATE)) {
					int previousWifiState = intent.getIntExtra(WifiManager.EXTRA_PREVIOUS_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
					WifiState previousWifiStateObj =  WifiState.getInstance(previousWifiState);
					
					CordovaPluginLog.d(LOG_TAG, "previousWifiState: " +  previousWifiState + " " + previousWifiStateObj);
					jsonWifiState.put(WifiManager.EXTRA_PREVIOUS_WIFI_STATE, previousWifiState);
					jsonWifiState.put(WifiManager.EXTRA_PREVIOUS_WIFI_STATE + "Name", previousWifiStateObj.toString());
				}
				
				if (this.wifistateCallbackcontext != null) {
					PluginResult wifiStateChangedResult = new PluginResult(Status.OK, jsonWifiState);
					wifiStateChangedResult.setKeepCallback(true);
					this.wifistateCallbackcontext.sendPluginResult(wifiStateChangedResult);
				}
			}
			catch (JSONException e) {
				if (this.wifistateCallbackcontext != null) {
					PluginResult wifiStateChangedResult = new PluginResult(Status.JSON_EXCEPTION, e.getMessage());
					wifiStateChangedResult.setKeepCallback(true);
					this.wifistateCallbackcontext.sendPluginResult(wifiStateChangedResult);
				}
			}
		}
	}
}
