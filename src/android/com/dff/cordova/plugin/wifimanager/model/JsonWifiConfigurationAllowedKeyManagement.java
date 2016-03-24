package com.dff.cordova.plugin.wifimanager.model;

import java.util.BitSet;

import org.json.JSONArray;
import org.json.JSONException;

import android.net.wifi.WifiConfiguration;

public class JsonWifiConfigurationAllowedKeyManagement {
	protected BitSet allowedKeyManagement;
	
	private JsonWifiConfigurationAllowedKeyManagement(BitSet allowedKeyManagement) {
		this.allowedKeyManagement = allowedKeyManagement;
	}
	
	public JSONArray toJson() throws JSONException {
		JSONArray jsonallowedKeyManagement = new JSONArray();		
		
		if (this.allowedKeyManagement != null) {			
			if (allowedKeyManagement.get(WifiConfiguration.KeyMgmt.IEEE8021X)) {
				jsonallowedKeyManagement.put("IEEE8021X");
			}
			
			if (allowedKeyManagement.get(WifiConfiguration.KeyMgmt.NONE)) {
				jsonallowedKeyManagement.put("NONE");
			}
			
			if (allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_EAP)) {
				jsonallowedKeyManagement.put("WPA_EAP");
			}
			
			if (allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_PSK)) {
				jsonallowedKeyManagement.put("WPA_PSK");
			}
		}
		
		return jsonallowedKeyManagement;
	}
	
	public static JsonWifiConfigurationAllowedKeyManagement getInstance(BitSet allowedKeyManagement) {
		return new JsonWifiConfigurationAllowedKeyManagement(allowedKeyManagement);
	}
}
