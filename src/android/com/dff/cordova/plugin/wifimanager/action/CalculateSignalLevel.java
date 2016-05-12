package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.net.wifi.WifiManager;

public class CalculateSignalLevel extends WifiManagerAction {
	public static final String ACTION_NAME = "calculateSignalLevel";
	
	public CalculateSignalLevel(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			int rssi;
			int numLevels;
			
			JSONObject jsonArgs = this.args.getJSONObject(0);
			if (jsonArgs == null) {
				throw new Exception("args missing");
			}
	    	
			if (!jsonArgs.has("rssi")) {
				throw new Exception("rssi arg missing");
			}
			
			if (!jsonArgs.has("numLevels")) {
				throw new Exception("numLevels arg missing");
			}
			
			rssi = jsonArgs.getInt("rssi");
			numLevels = jsonArgs.getInt("numLevels");
			
			int level = WifiManager.calculateSignalLevel(rssi, numLevels);
			
			this.callbackContext.success(level);
			
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
