package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.net.wifi.WifiManager;

public class CompareSignalLevel extends WifiManagerAction {
	public static final String ACTION_NAME = "compareSignalLevel";
	
	public CompareSignalLevel(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			int rssiA;
			int rssiB;
			
			JSONObject jsonArgs = this.args.getJSONObject(0);
			if (jsonArgs == null) {
				throw new Exception("args missing");
			}
	    	
			if (!jsonArgs.has("rssiA")) {
				throw new Exception("rssiA arg missing");
			}
			
			if (!jsonArgs.has("rssiB")) {
				throw new Exception("rssiB arg missing");
			}
			
			rssiA = jsonArgs.getInt("rssiA");
			rssiB = jsonArgs.getInt("rssiB");
			
			int compare = WifiManager.compareSignalLevel(rssiA, rssiB);
			
			this.callbackContext.success(compare);
			
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
