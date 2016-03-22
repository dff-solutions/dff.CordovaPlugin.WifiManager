package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.net.wifi.WifiManager;

public class EnableNetwork extends WifiManagerAction {
	public static final String ACTION_NAME = "enableNetwork";

	public EnableNetwork(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			int netId;
			boolean disableOthers;
			
			JSONObject jsonArgs = this.args.getJSONObject(0);
			if (jsonArgs == null) {
				throw new Exception("args missing");
			}
	    	
			if (!jsonArgs.has("netId")) {
				throw new Exception("netId arg missing");
			}
			
			if (!jsonArgs.has("disableOthers")) {
				throw new Exception("disableOthers arg missing");
			}
			
			netId = jsonArgs.getInt("netId");
			disableOthers = jsonArgs.getBoolean("disableOthers");
			
			boolean succeeded = this.wifiManager.enableNetwork(netId, disableOthers);
			
			if (succeeded) {
				this.callbackContext.success();
			}
			else {
				this.callbackContext.error("could not enable network: " + netId + " disableOthers: " + disableOthers);
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
