package com.dff.cordova.plugin.wifimanager.action;

import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.model.JsonWifiConfiguration;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

public class GetConfiguredNetworks extends WifiManagerAction {
	public static final String ACTION_NAME = "getConfiguredNetworks";

	public GetConfiguredNetworks(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
			WifiManager wifiManager) {
		super(action, args, callbackContext, cordova, wifiManager);
	}

	@Override
	public void run() {
		super.run();
		
		try {
			List<WifiConfiguration> wifiConfigurations = this.wifiManager.getConfiguredNetworks();
			JSONArray jsonWifiConfigurations = new JSONArray();
			JSONObject jsonWifiConfiguration;
			
			for (WifiConfiguration wifiConfiguration : wifiConfigurations) {
				jsonWifiConfiguration = JsonWifiConfiguration
						.getInstance(wifiConfiguration)
						.toJson();
				
				jsonWifiConfigurations.put(jsonWifiConfiguration);
			}
			
			
			this.callbackContext.success(jsonWifiConfigurations);
		}
		catch(JSONException e) {
			CordovaPluginLog.e(this.getClass().getName(), e.getMessage(), e);
			this.callbackContext.error(e.getMessage());
		}
	}
}
