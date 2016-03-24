package com.dff.cordova.plugin.wifimanager.model;

import java.util.BitSet;

import org.json.JSONArray;
import org.json.JSONException;

import android.net.wifi.WifiConfiguration;

public class JsonWifiConfigurationAllowedProtocols {
	protected BitSet allowedProtocols;
	
	private JsonWifiConfigurationAllowedProtocols(BitSet allowedProtocols) {
		this.allowedProtocols = allowedProtocols;
	}
	
	public JSONArray toJson() throws JSONException {
		JSONArray jsonAllowedProtocols = new JSONArray();		
		
		if (this.allowedProtocols != null) {			
			if (allowedProtocols.get(WifiConfiguration.Protocol.RSN)) {
				jsonAllowedProtocols.put("RSN");
			}
		}
		
		if (this.allowedProtocols != null) {			
			if (allowedProtocols.get(WifiConfiguration.Protocol.WPA)) {
				jsonAllowedProtocols.put("WPA");
			}
		}
		
		return jsonAllowedProtocols;
	}
	
	public static JsonWifiConfigurationAllowedProtocols getInstance(BitSet allowedProtocols) {
		return new JsonWifiConfigurationAllowedProtocols(allowedProtocols);
	}
}
