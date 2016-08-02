package com.dff.cordova.plugin.wifimanager;

import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.model.JsonScanResult;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

public class NetworkScanReceiver extends BroadcastReceiver {
	private static final String LOG_TAG = "com.dff.cordova.plugin.wifimanager.NetworkScanReceiver";
	
	protected WifiManager wifiManager;
	protected CallbackContext scanResultCallback;
	protected Activity activity;
	
	public NetworkScanReceiver(Activity activity, WifiManager wifiManager) {
		this.activity = activity;
		this.wifiManager = wifiManager;
		
		IntentFilter scanResultsFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		activity.registerReceiver(this, scanResultsFilter);
	}
	
	public void onDestroy() {
		if (this.activity != null) {
			this.activity.unregisterReceiver(this);
		}
		
		if (this.scanResultCallback != null) {
			scanResultCallback.success();
		}
	}
	
	public CallbackContext getScanResultCallback() {
		return scanResultCallback;
	}

	public void setScanResultCallback(CallbackContext scanresultsCallback) {
		this.scanResultCallback = scanresultsCallback;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
//		CordovaPluginLog.d(LOG_TAG, "Action Received: " + intent.getAction() + " From intent: " + intent);
		
		JSONArray jsonScanResults = new JSONArray();
		
		try {
			List<ScanResult> scanresult = this.wifiManager.getScanResults();
			JsonScanResult jsonScanresult;
			
			for (ScanResult sr : scanresult) {
				jsonScanresult = JsonScanResult.getInstance(sr);
				jsonScanResults.put(jsonScanresult.toJson());
			}
			
			if (this.scanResultCallback != null) {
				PluginResult pluginScanResult = new PluginResult(Status.OK, jsonScanResults);
				pluginScanResult.setKeepCallback(true);
				this.scanResultCallback.sendPluginResult(pluginScanResult);
			}
		}
		catch (JSONException e) {
			CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			
			if (this.scanResultCallback != null) {
				PluginResult pluginScanResult = new PluginResult(Status.JSON_EXCEPTION, e.getMessage());
				pluginScanResult.setKeepCallback(true);
				this.scanResultCallback.sendPluginResult(pluginScanResult);
			}
		}
	}

}
