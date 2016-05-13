package com.dff.cordova.plugin.wifimanager.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.wifi.WifiConfiguration;

public class JsonWifiConfiguration {
	protected WifiConfiguration wifiConfiguration;
	protected JSONObject jsonWifiConfiguration;
	
	private JsonWifiConfiguration(WifiConfiguration wifiConfiguration) {
		this.wifiConfiguration = wifiConfiguration;
	}
	
	private JsonWifiConfiguration(JSONObject jsonWifiConfiguration) {
		this.jsonWifiConfiguration = jsonWifiConfiguration;
	}
	
	public WifiConfiguration fromJson() throws JSONException {
		this.wifiConfiguration = new WifiConfiguration();
		
		if (this.jsonWifiConfiguration != null) {
			
		}
		
		return this.wifiConfiguration;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject jsonWifiConfiguration = new JSONObject();
		
		
		if (this.wifiConfiguration != null) {
			JSONArray jsonAllowedAuthAlgorithms = JsonWifiConfigurationAllowedAuthAlgorithm
					.getInstance(this.wifiConfiguration.allowedAuthAlgorithms)
					.toJson();
			
			JSONArray jsonAllowedGroupCiphers = JsonWifiConfigurationAllowedGroupCiphers
					.getInstance(this.wifiConfiguration.allowedGroupCiphers)
					.toJson();
			
			JSONArray jsonAllowedKeyManagement = JsonWifiConfigurationAllowedKeyManagement
					.getInstance(this.wifiConfiguration.allowedKeyManagement)
					.toJson();
			JSONArray jsonAllowedPairwiseCiphers = JsonWifiConfigurationAllowedPairwiseCiphers
					.getInstance(this.wifiConfiguration.allowedPairwiseCiphers)
					.toJson();
			JSONArray jsonAllowedProtocols = JsonWifiConfigurationAllowedProtocols
					.getInstance(this.wifiConfiguration.allowedProtocols)
					.toJson();

			
			jsonWifiConfiguration.put("allowedAuthAlgorithms", jsonAllowedAuthAlgorithms);
			jsonWifiConfiguration.put("allowedGroupCiphers", jsonAllowedGroupCiphers);
			jsonWifiConfiguration.put("allowedKeyManagement", jsonAllowedKeyManagement);
			jsonWifiConfiguration.put("allowedPairwiseCiphers", jsonAllowedPairwiseCiphers);
			jsonWifiConfiguration.put("allowedProtocols", jsonAllowedProtocols);
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
	
	public static JsonWifiConfiguration getInstance(JSONObject jsonWifiConfiguration) {
		return new JsonWifiConfiguration(jsonWifiConfiguration);
	}
}
