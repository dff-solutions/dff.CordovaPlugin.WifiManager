package com.dff.cordova.plugin.wifimanager;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkScanReceiver extends BroadcastReceiver {
	private static final String LOG_TAG = "com.dff.cordova.plugin.wifimanager.NetworkScanReceiver";
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		CordovaPluginLog.d(LOG_TAG, "Action Received: " + intent.getAction() + " From intent: " + intent);
	}

}
