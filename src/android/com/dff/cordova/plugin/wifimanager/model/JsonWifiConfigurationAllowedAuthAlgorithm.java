package com.dff.cordova.plugin.wifimanager.model;

import java.util.BitSet;

import org.json.JSONArray;
import org.json.JSONException;

import android.net.wifi.WifiConfiguration;

public class JsonWifiConfigurationAllowedAuthAlgorithm {
	protected BitSet allowedAuthAlgorithms;
	
	private JsonWifiConfigurationAllowedAuthAlgorithm(BitSet allowedAuthAlgorithms) {
		this.allowedAuthAlgorithms = allowedAuthAlgorithms;
	}
	
	public JSONArray toJson() throws JSONException {
		JSONArray jsonAllowedAuthAlgorithms = new JSONArray();
		
		if (this.allowedAuthAlgorithms != null) {			
			if (allowedAuthAlgorithms.get(WifiConfiguration.AuthAlgorithm.LEAP)) {
				jsonAllowedAuthAlgorithms.put("LEAP");
			}
			if (allowedAuthAlgorithms.get(WifiConfiguration.AuthAlgorithm.OPEN)) {
				jsonAllowedAuthAlgorithms.put("OPEN");
			}
			
			if (allowedAuthAlgorithms.get(WifiConfiguration.AuthAlgorithm.SHARED)) {
				jsonAllowedAuthAlgorithms.put("SHARED");
			}
		}
		
		return jsonAllowedAuthAlgorithms;
	}
	
	public static JsonWifiConfigurationAllowedAuthAlgorithm getInstance(BitSet allowedAuthAlgorithms) {
		return new JsonWifiConfigurationAllowedAuthAlgorithm(allowedAuthAlgorithms);
	}
}
