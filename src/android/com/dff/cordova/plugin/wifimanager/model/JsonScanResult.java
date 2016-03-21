package com.dff.cordova.plugin.wifimanager.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.wifi.ScanResult;

public class JsonScanResult {
	protected ScanResult scanResult;
	
	private JsonScanResult(ScanResult scanResult) {
		this.scanResult = scanResult;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject jsonScanResult = new JSONObject();
		
		if (this.scanResult != null) {
			jsonScanResult.put("frequency", this.scanResult.frequency);
			jsonScanResult.put("level", this.scanResult.level);
			jsonScanResult.put("timestamp", this.scanResult.timestamp);
			jsonScanResult.put("bssid", this.scanResult.BSSID);
			jsonScanResult.put("capabilities", this.scanResult.capabilities);
			jsonScanResult.put("ssid", this.scanResult.SSID);
		}
		
		return jsonScanResult;
	}
	
	public static JsonScanResult getInstance(ScanResult scanResult) {
		return new JsonScanResult(scanResult);
	}
}
