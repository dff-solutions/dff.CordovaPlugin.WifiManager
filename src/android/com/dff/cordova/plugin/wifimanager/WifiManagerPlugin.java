package com.dff.cordova.plugin.wifimanager;

import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class WifiManagerPlugin extends CommonPlugin {
	private static final String LOG_TAG = "com.dff.cordova.plugin.wifimanager.WifiManagerPlugin";
	public static final String NETWORK_STATE_CHANGED_ACTION = "com.dff.cordova.plugin.wifimanager.STATE_CHANGE";
	public static final String WIFI_STATE_CHANGED_ACTION = "com.dff.cordova.plugin.wifimanager.WIFI_STATE_CHANGED";
	
	public WifiManagerPlugin() {
		super();
	}
	
	   /**
		* Called after plugin construction and fields have been initialized.
		*/
		@Override
		public void pluginInitialize() {
			super.pluginInitialize();
			
	    	IntentFilter networkStateFilter = new IntentFilter(NETWORK_STATE_CHANGED_ACTION);
	    	this.cordova.getActivity().registerReceiver(new BroadcastReceiver() {

				@Override
				public void onReceive(Context context, Intent intent) {
					CordovaPluginLog.d(LOG_TAG, NETWORK_STATE_CHANGED_ACTION + ": " + intent);
					
				}
	    		
	    	}, networkStateFilter);
		}
}
