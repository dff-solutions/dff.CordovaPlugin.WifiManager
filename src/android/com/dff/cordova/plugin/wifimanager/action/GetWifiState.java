package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.model.WifiState;

import android.net.wifi.WifiManager;

public class GetWifiState extends WifiManagerAction {
	public static final String ACTION_NAME = "getWifiState";

	public GetWifiState(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			JSONObject jsonWifiState = WifiState
					.getInstance(this.wifiManager.getWifiState())
					.toJson();
			this.callbackContext.success(jsonWifiState);
		}
		catch(JSONException e) {
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
	}
}
