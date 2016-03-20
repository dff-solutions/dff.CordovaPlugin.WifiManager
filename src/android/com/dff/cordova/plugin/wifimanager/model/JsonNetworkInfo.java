package com.dff.cordova.plugin.wifimanager.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.NetworkInfo;

public class JsonNetworkInfo {
	protected NetworkInfo networkInfo;
	
	private JsonNetworkInfo(NetworkInfo networkInfo) {
		this.networkInfo = networkInfo;
	}
	
	public JSONObject toJson() throws JSONException {
		JSONObject jsonNetworkInfo = new JSONObject();
		
		if (this.networkInfo != null) {
			jsonNetworkInfo.put("type", this.networkInfo.getType());
			jsonNetworkInfo.put("typeName", this.networkInfo.getTypeName());
			jsonNetworkInfo.put("subtype", this.networkInfo.getSubtype());
			jsonNetworkInfo.put("subtypeName", this.networkInfo.getSubtypeName());
			jsonNetworkInfo.put("state", this.networkInfo.getState());
			//jsonNetworkInfo.put("stateName", this.networkInfo.getState().name());
			jsonNetworkInfo.put("reason", this.networkInfo.getReason());
			jsonNetworkInfo.put("detailedState", this.networkInfo.getDetailedState());
			jsonNetworkInfo.put("detailedStateName", this.networkInfo.getDetailedState().name());
			jsonNetworkInfo.put("extraInfo", this.networkInfo.getExtraInfo());
			jsonNetworkInfo.put("isAvailable", this.networkInfo.isAvailable());
			jsonNetworkInfo.put("isConnected", this.networkInfo.isConnected());
			jsonNetworkInfo.put("isConnectedOrConnecting", this.networkInfo.isConnectedOrConnecting());
			jsonNetworkInfo.put("isFailover", this.networkInfo.isFailover());
			jsonNetworkInfo.put("isRoaming", this.networkInfo.isRoaming());
		}
		
		return jsonNetworkInfo;
	}
	
	public static JsonNetworkInfo getInstance(NetworkInfo networkInfo) {
		return new JsonNetworkInfo(networkInfo);
	}
}
