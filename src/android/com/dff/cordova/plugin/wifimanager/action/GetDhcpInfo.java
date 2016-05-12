package com.dff.cordova.plugin.wifimanager.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.model.JsonDhcpInfo;

import android.net.wifi.WifiManager;

public class GetDhcpInfo extends WifiManagerAction {
	public static final String ACTION_NAME = " getDhcpInfo";
	
	public GetDhcpInfo(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			JSONObject jsonDhcpInfo = JsonDhcpInfo
					.getInstance(this.wifiManager.getDhcpInfo())
					.toJson();
			this.callbackContext.success(jsonDhcpInfo);
		}
		catch(JSONException e) {
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
	}
}
