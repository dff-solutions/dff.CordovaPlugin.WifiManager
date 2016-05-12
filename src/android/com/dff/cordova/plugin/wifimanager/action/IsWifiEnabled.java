package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.net.wifi.WifiManager;

public class IsWifiEnabled extends WifiManagerAction {
	public static final String ACTION_NAME = "isWifiEnabled";
	
	public IsWifiEnabled(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			JSONObject jsonResult = new JSONObject();
			jsonResult.put("enabled", this.wifiManager.isWifiEnabled());
			
			this.callbackContext.success(jsonResult);		
			
		}
		catch(JSONException e) {
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
		catch(Exception e){
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
	}
}
