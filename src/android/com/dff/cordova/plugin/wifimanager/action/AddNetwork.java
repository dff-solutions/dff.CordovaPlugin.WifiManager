package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.model.JsonWifiConfiguration;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

public class AddNetwork extends WifiManagerAction {
	public static final String ACTION_NAME = "addNetwork";
	
	public AddNetwork(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			JSONObject config;
			
			JSONObject jsonArgs = this.args.getJSONObject(0);
			if (jsonArgs == null) {
				throw new Exception("args missing");
			}
	    	
			if (!jsonArgs.has("config")) {
				throw new Exception("config arg missing");
			}
			
			config = jsonArgs.getJSONObject("config");
			
			WifiConfiguration wifiConfiguration = JsonWifiConfiguration.getInstance(config).fromJson();
			
			int netId = this.wifiManager.addNetwork(wifiConfiguration);
			this.callbackContext.success(netId);
			
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
