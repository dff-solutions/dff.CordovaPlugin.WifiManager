package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.action.CordovaAction;

import android.net.wifi.WifiManager;

public abstract class WifiManagerAction extends CordovaAction {
	protected WifiManager wifiManager;

	public WifiManagerAction(String action, JSONArray args
			, CallbackContext callbackContext
			, CordovaInterface cordova
			, WifiManager wifiManager
		) {
		super(action, args, callbackContext, cordova);
		this.wifiManager = wifiManager;
	}

}
