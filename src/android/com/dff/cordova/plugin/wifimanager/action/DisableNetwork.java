package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.net.wifi.WifiManager;

public class DisableNetwork extends WifiManagerAction {
	public static final String ACTION_NAME = "disableNetwork";

	public DisableNetwork(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			int netId;
			
			JSONObject jsonArgs = this.args.getJSONObject(0);
			if (jsonArgs == null) {
				throw new Exception("args missing");
			}
	    	
			if (!jsonArgs.has("netId")) {
				throw new Exception("netId arg missing");
			}
			
			netId = jsonArgs.getInt("netId");
			
			boolean disabled = this.wifiManager.disableNetwork(netId);
			
			if (disabled) {
				this.callbackContext.success();
			}
			else {
				this.callbackContext.error("could not disable network: " + netId);
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
