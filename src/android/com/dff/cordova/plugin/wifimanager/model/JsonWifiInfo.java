package com.dff.cordova.plugin.wifimanager.model;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

import org.json.JSONException;
import org.json.JSONObject;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;

public class JsonWifiInfo {
	public static final String LOG_TAG = "JsonWifiInfo";
	
	protected WifiInfo wifiInfo;
	
	private JsonWifiInfo(WifiInfo wifiInfo) {
		this.wifiInfo = wifiInfo;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject jsonWifiInfo = new JSONObject();
		
		
		if (this.wifiInfo != null) {
			jsonWifiInfo.put("hiddenSSID", this.wifiInfo.getHiddenSSID());
			
			int ipAddress = this.wifiInfo.getIpAddress();
			
			if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
				ipAddress = Integer.reverseBytes(ipAddress);
			}
			
			byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();
			
		    String ipAddressString;
		    try {
		        ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
		    } catch (UnknownHostException ex) {
		        CordovaPluginLog.e(LOG_TAG, "Unable to get host address.");
		        ipAddressString = null;
		    }
			
			jsonWifiInfo.put("ipAddress", ipAddressString);
			jsonWifiInfo.put("linkSpeed", this.wifiInfo.getLinkSpeed());
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
