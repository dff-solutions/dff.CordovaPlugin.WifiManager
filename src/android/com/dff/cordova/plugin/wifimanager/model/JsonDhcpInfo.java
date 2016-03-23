package com.dff.cordova.plugin.wifimanager.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.DhcpInfo;

public class JsonDhcpInfo {
	protected DhcpInfo dhcpInfo;
	
	private JsonDhcpInfo(DhcpInfo dhcpInfo) {
		this.dhcpInfo = dhcpInfo;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject jsonDhcpInfo = new JSONObject();
		
		
		if (this.dhcpInfo != null) {
			jsonDhcpInfo.put("dns1", this.dhcpInfo.dns1);
			jsonDhcpInfo.put("dns2", this.dhcpInfo.dns2);
			jsonDhcpInfo.put("gateway", this.dhcpInfo.gateway);
			jsonDhcpInfo.put("ipAddress", this.dhcpInfo.ipAddress);
			jsonDhcpInfo.put("leaseDuration", this.dhcpInfo.leaseDuration);
			jsonDhcpInfo.put("netmask", this.dhcpInfo.netmask);
			jsonDhcpInfo.put("serverAddress", this.dhcpInfo.serverAddress);
		}
		
		return jsonDhcpInfo;
	}
	
	public static JsonDhcpInfo getInstance(DhcpInfo dhcpInfo) {
		return new JsonDhcpInfo(dhcpInfo);
	}
}
