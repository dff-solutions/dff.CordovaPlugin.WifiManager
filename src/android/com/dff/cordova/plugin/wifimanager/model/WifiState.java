package com.dff.cordova.plugin.wifimanager.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.wifi.WifiManager;

public class WifiState {
	protected int wifiState;
	
	private WifiState(int wifiState) {
		this.wifiState = wifiState;
	}
	
	public int getWifiState() {
		return this.wifiState;
	}
	
	public String toString() {
		String wifiStateDesc = "WIFI_STATE_UNKNOWN";
		
		if (this.wifiState == WifiManager.WIFI_STATE_DISABLED) {
			wifiStateDesc = "WIFI_STATE_DISABLED";
		}
		else if (this.wifiState == WifiManager.WIFI_STATE_DISABLING) {
			wifiStateDesc = "WIFI_STATE_DISABLING";
		}
		else if (this.wifiState == WifiManager.WIFI_STATE_ENABLED) {
			wifiStateDesc = "WIFI_STATE_ENABLED";
		}
		else if (this.wifiState == WifiManager.WIFI_STATE_ENABLING) {
			wifiStateDesc = "WIFI_STATE_ENABLING";
		}
		
		return wifiStateDesc;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject jsonWifiState = new JSONObject();
		
		jsonWifiState.put("wifiState", this.wifiState);
		jsonWifiState.put("wifiStateDesc", this.toString());
		
		return jsonWifiState;
	}
	
	public static WifiState getInstance(int wifiState) {
		return new WifiState(wifiState);
	}
}
