package com.dff.cordova.plugin.wifimanager.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;

public class JsonWifiInfo {
	protected WifiInfo wifiInfo;
	
	private JsonWifiInfo(WifiInfo wifiInfo) {
		this.wifiInfo = wifiInfo;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject jsonWifiInfo = new JSONObject();
		
		
		if (this.wifiInfo != null) {
			jsonWifiInfo.put("hiddenSSID", this.wifiInfo.getHiddenSSID());
			jsonWifiInfo.put("ipAddress", this.wifiInfo.getIpAddress());
			jsonWifiInfo.put("LinkSpeed", this.wifiInfo.getLinkSpeed());
			jsonWifiInfo.put("networkId", this.wifiInfo.getNetworkId());
			jsonWifiInfo.put("rssi", this.wifiInfo.getRssi());
			jsonWifiInfo.put("bssid", this.wifiInfo.getBSSID());
			jsonWifiInfo.put("macAddress", this.wifiInfo.getMacAddress());
			jsonWifiInfo.put("ssid", this.wifiInfo.getSSID());
			jsonWifiInfo.put("supplicantState", this.wifiInfo.getSupplicantState());
			//jsonWifiInfo.put("supplicantStateName", this.wifiInfo.getSupplicantState().name());
			
			NetworkInfo.DetailedState detailedState = WifiInfo.getDetailedStateOf(this.wifiInfo.getSupplicantState());
			
			jsonWifiInfo.put("detailedState", detailedState);
			//jsonWifiInfo.put("detailedStateName", detailedState.name());
		}
		
		return jsonWifiInfo;
	}
	
	public static JsonWifiInfo getInstance(WifiInfo wifiInfo) {
		return new JsonWifiInfo(wifiInfo);
	}
}
