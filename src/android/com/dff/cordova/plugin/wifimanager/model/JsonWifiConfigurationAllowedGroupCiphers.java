package com.dff.cordova.plugin.wifimanager.model;

import java.util.BitSet;

import org.json.JSONArray;
import org.json.JSONException;

import android.net.wifi.WifiConfiguration;

public class JsonWifiConfigurationAllowedGroupCiphers {
	protected BitSet allowedGroupCiphers;
	
	private JsonWifiConfigurationAllowedGroupCiphers(BitSet allowedGroupCiphers) {
		this.allowedGroupCiphers = allowedGroupCiphers;
	}
	
	public JSONArray toJson() throws JSONException {
		JSONArray jsonAllowedGroupCiphers = new JSONArray();		
		
		if (this.allowedGroupCiphers != null) {			
			if (allowedGroupCiphers.get(WifiConfiguration.GroupCipher.CCMP)) {
				jsonAllowedGroupCiphers.put("CCMP");
			}
			if (allowedGroupCiphers.get(WifiConfiguration.GroupCipher.TKIP)) {
				jsonAllowedGroupCiphers.put("TKIP");
			}
			if (allowedGroupCiphers.get(WifiConfiguration.GroupCipher.WEP104)) {
				jsonAllowedGroupCiphers.put("WEP104");
			}
			if (allowedGroupCiphers.get(WifiConfiguration.GroupCipher.WEP40)) {
				jsonAllowedGroupCiphers.put("WEP40");
			}
		}
		
		return jsonAllowedGroupCiphers;
	}
	
	public static JsonWifiConfigurationAllowedGroupCiphers getInstance(BitSet allowedGroupCiphers) {
		return new JsonWifiConfigurationAllowedGroupCiphers(allowedGroupCiphers);
	}
}
