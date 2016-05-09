package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.net.wifi.WifiManager;

public class Reconnect extends WifiManagerAction {
	public static final String ACTION_NAME = "reconnect";

	public Reconnect(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			if (this.wifiManager.reconnect()) {
				this.callbackContext.success();
			}
			else {
				this.callbackContext.error("could not reconnect");
			}
		}
		catch(Exception e){
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
	}
}
