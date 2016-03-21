package com.dff.cordova.plugin.wifimanager.action;

import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.model.JsonScanResult;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

public class GetScanResults extends WifiManagerAction {
	public static final String ACTION_NAME = "getScanResults";

	public GetScanResults(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			JSONObject jsonScanResult;
			JSONArray jsonScanResults = new JSONArray();			
			List<ScanResult> scanResult = this.wifiManager.getScanResults();
			
			for (ScanResult sr : scanResult) {
				jsonScanResult = JsonScanResult
					.getInstance(sr)
					.toJson();
				
				jsonScanResults.put(jsonScanResult);
			}
			
			this.callbackContext.success(jsonScanResults);
		}
		catch(JSONException e) {
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
	}
}
