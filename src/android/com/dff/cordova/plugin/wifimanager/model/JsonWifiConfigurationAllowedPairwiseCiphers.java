package com.dff.cordova.plugin.wifimanager.model;

import java.util.BitSet;

import org.json.JSONArray;
import org.json.JSONException;

import android.net.wifi.WifiConfiguration;

public class JsonWifiConfigurationAllowedPairwiseCiphers {
	protected BitSet allowedPairwiseCiphers;
	
	private JsonWifiConfigurationAllowedPairwiseCiphers(BitSet allowedPairwiseCiphers) {
		this.allowedPairwiseCiphers = allowedPairwiseCiphers;
	}
	
	public JSONArray toJson() throws JSONException {
		JSONArray jsonAllowedPairwiseCiphers = new JSONArray();		
		
		if (this.allowedPairwiseCiphers != null) {			
			if (allowedPairwiseCiphers.get(WifiConfiguration.PairwiseCipher.CCMP)) {
				jsonAllowedPairwiseCiphers.put("CCMP");
			}
		}
		
		if (this.allowedPairwiseCiphers != null) {			
			if (allowedPairwiseCiphers.get(WifiConfiguration.PairwiseCipher.NONE)) {
				jsonAllowedPairwiseCiphers.put("NONE");
			}
		}
		
		if (this.allowedPairwiseCiphers != null) {			
			if (allowedPairwiseCiphers.get(WifiConfiguration.PairwiseCipher.TKIP)) {
				jsonAllowedPairwiseCiphers.put("TKIP");
			}
		}
		
		return jsonAllowedPairwiseCiphers;
	}
	
	public static JsonWifiConfigurationAllowedPairwiseCiphers getInstance(BitSet allowedPairwiseCiphers) {
		return new JsonWifiConfigurationAllowedPairwiseCiphers(allowedPairwiseCiphers);
	}
}
