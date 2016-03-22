package com.dff.cordova.plugin.wifimanager.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.wifi.WifiConfiguration;

public class JsonWifiConfiguration {
	protected WifiConfiguration wifiConfiguration;
	
	private JsonWifiConfiguration(WifiConfiguration wifiConfiguration) {
		this.wifiConfiguration = wifiConfiguration;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject jsonWifiConfiguration = new JSONObject();
		
		
		if (this.wifiConfiguration != null) {
			jsonWifiConfiguration.put("allowedAuthAlgorithms", this.wifiConfiguration.allowedAuthAlgorithms);
			jsonWifiConfiguration.put("allowedGroupCiphers", this.wifiConfiguration.allowedGroupCiphers);
			jsonWifiConfiguration.put("allowedKeyManagement", this.wifiConfiguration.allowedKeyManagement);
			jsonWifiConfiguration.put("allowedPairwiseCiphers", this.wifiConfiguration.allowedPairwiseCiphers);
			jsonWifiConfiguration.put("allowedProtocols", this.wifiConfiguration.allowedProtocols);
			jsonWifiConfiguration.put("bssid", this.wifiConfiguration.BSSID);
			jsonWifiConfiguration.put("ssid", this.wifiConfiguration.SSID);
			jsonWifiConfiguration.put("hiddenSSID", this.wifiConfiguration.hiddenSSID);
			jsonWifiConfiguration.put("networkId", this.wifiConfiguration.networkId);
			jsonWifiConfiguration.put("preSharedKey", this.wifiConfiguration.preSharedKey);
			jsonWifiConfiguration.put("priority", this.wifiConfiguration.priority);
			
			int status = this.wifiConfiguration.status;
			String statusName = "";
			
			switch (status) {
			case WifiConfiguration.Status.CURRENT:
				statusName = "CURRENT";
				break;
			case WifiConfiguration.Status.DISABLED:
				statusName = "DISABLED";
				break;
			case WifiConfiguration.Status.ENABLED:
				statusName = "ENABLED";
				break;

			default:
				break;
			}
			
			jsonWifiConfiguration.put("status", status);
			jsonWifiConfiguration.put("statusName", statusName);
			
			String[] webKeys = this.wifiConfiguration.wepKeys;
			JSONArray jsonWebKeys = new JSONArray();
			
			for (String wk : webKeys) {
				jsonWebKeys.put(wk);
			}
			
			jsonWifiConfiguration.put("webKeys", jsonWebKeys);
			jsonWifiConfiguration.put("wepTxKeyIndex", this.wifiConfiguration.wepTxKeyIndex);
		}
		
		return jsonWifiConfiguration;
	}
	
	public static JsonWifiConfiguration getInstance(WifiConfiguration wifiConfiguration) {
		return new JsonWifiConfiguration(wifiConfiguration);
	}
}
