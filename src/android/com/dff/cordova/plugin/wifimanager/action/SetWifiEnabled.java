package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.net.wifi.WifiManager;

public class SetWifiEnabled extends WifiManagerAction {
	public static final String ACTION_NAME = "setWifiEnabled";

	public SetWifiEnabled(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			boolean enabled;
			
			JSONObject jsonArgs = this.args.getJSONObject(0);
			if (jsonArgs == null) {
				throw new Exception("args missing");
			}
	    	
			if (!jsonArgs.has("enabled")) {
				throw new Exception("enabled arg missing");
			}
			
			enabled = jsonArgs.getBoolean("enabled");
			
			if (this.wifiManager.setWifiEnabled(enabled)) {
				this.callbackContext.success();
			}
			else {
				this.callbackContext.error("could not set wifi enabled: " + enabled);
			}
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
