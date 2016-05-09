package com.dff.cordova.plugin.wifimanager;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.action.CordovaAction;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.action.CalculateSignalLevel;
import com.dff.cordova.plugin.wifimanager.action.CompareSignalLevel;
import com.dff.cordova.plugin.wifimanager.action.DisableNetwork;
import com.dff.cordova.plugin.wifimanager.action.Disconnect;
import com.dff.cordova.plugin.wifimanager.action.EnableNetwork;
import com.dff.cordova.plugin.wifimanager.action.GetConfiguredNetworks;
import com.dff.cordova.plugin.wifimanager.action.GetConnectionInfo;
import com.dff.cordova.plugin.wifimanager.action.GetDhcpInfo;
import com.dff.cordova.plugin.wifimanager.action.GetScanResults;
import com.dff.cordova.plugin.wifimanager.action.GetWifiState;
import com.dff.cordova.plugin.wifimanager.action.IsScanAlwaysAvailable;
import com.dff.cordova.plugin.wifimanager.action.IsWifiEnabled;
import com.dff.cordova.plugin.wifimanager.action.Reconnect;
import com.dff.cordova.plugin.wifimanager.action.SetWifiEnabled;
import com.dff.cordova.plugin.wifimanager.action.StartScan;

import android.content.Context;
import android.net.wifi.WifiManager;

public class WifiManagerPlugin extends CommonPlugin {
	private static final String LOG_TAG = "com.dff.cordova.plugin.wifimanager.WifiManagerPlugin";	
	protected NetworkConnectivityReceiver networkConnectivityReceiver;
	protected NetworkScanReceiver networkScanReceiver;
	
	protected WifiManager wifiManager;
	
	public WifiManagerPlugin() {
		super();
	}
	
	   /**
		* Called after plugin construction and fields have been initialized.
		*/
		@Override
		public void pluginInitialize() {
			super.pluginInitialize();
			this.wifiManager = (WifiManager) this.cordova
					.getActivity()
					.getApplicationContext()
					.getSystemService(Context.WIFI_SERVICE);
			this.networkConnectivityReceiver = new NetworkConnectivityReceiver(this.cordova.getActivity());
			this.networkScanReceiver = new NetworkScanReceiver(this.cordova.getActivity(), this.wifiManager);
		}
		
		@Override
		public void onDestroy() {
			super.onDestroy();
			this.networkConnectivityReceiver.onDestroy();
			this.networkScanReceiver.onDestroy();
		}
		
	    /**
	     * Executes the request.
	     *
	     * This method is called from the WebView thread.
	     * To do a non-trivial amount of work, use:
	     * cordova.getThreadPool().execute(runnable);
	     *
	     * To run on the UI thread, use:
	     * cordova.getActivity().runOnUiThread(runnable);
	     *
	     * @param action The action to execute.
	     * @param args The exec() arguments.
	     * @param callbackContext The callback context used when calling back into JavaScript.
	     * @return Whether the action was valid.
	     */
	 	@Override
	     public boolean execute(String action
	     		, final JSONArray args
	     		, final CallbackContext callbackContext)
	         throws JSONException {
	 		
	     	CordovaPluginLog.i(LOG_TAG, "call for action: " + action + "; args: " + args);
	     	CordovaAction cordovaAction = null;
	     	
	     	if (action.equals("onNetworkStateChanged")) {
	     		this.networkConnectivityReceiver.setNetworStateCallbackcontext(callbackContext);
	     		return true;
	     	}
	     	else if (action.equals("onWifiStateChanged")) {
	     		this.networkConnectivityReceiver.setWifistateCallbackcontext(callbackContext);
	     		
	     		return true;
	     	}
	     	else if (action.equals("onScanResultsAvaiblable")) {
	     		this.networkScanReceiver.setScanResultCallback(callbackContext);
	     		
	     		return true;
	     	}
	     	else if (action.equals(CalculateSignalLevel.ACTION_NAME)) {
	     		cordovaAction = new CalculateSignalLevel(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(CompareSignalLevel.ACTION_NAME)) {
	     		cordovaAction = new CompareSignalLevel(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(DisableNetwork.ACTION_NAME)) {
	     		cordovaAction = new DisableNetwork(action
	     				, args
	     				, callbackContext
	     				, cordova
	     				, wifiManager);
	     	}
	     	else if (action.equals(Disconnect.ACTION_NAME)) {
	     		cordovaAction = new Disconnect(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(EnableNetwork.ACTION_NAME)) {
	     		cordovaAction = new EnableNetwork(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(GetConfiguredNetworks.ACTION_NAME)) {
	     		cordovaAction = new GetConfiguredNetworks(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(GetConnectionInfo.ACTION_NAME)) {
	     		cordovaAction = new GetConnectionInfo(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(GetDhcpInfo.ACTION_NAME)) {
	     		cordovaAction = new GetDhcpInfo(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(GetWifiState.ACTION_NAME)) {
	     		cordovaAction = new GetWifiState(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(GetScanResults.ACTION_NAME)) {
	     		cordovaAction = new GetScanResults	(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(IsWifiEnabled.ACTION_NAME)) {
	     		cordovaAction = new IsWifiEnabled(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(Reconnect.ACTION_NAME)) {
	     		cordovaAction = new Reconnect(action, args, callbackContext, this.cordova, this.wifiManager);
	     	}
	     	else if (action.equals(SetWifiEnabled.ACTION_NAME)) {
	     		cordovaAction = new SetWifiEnabled(action
	     				, args
	     				, callbackContext
	     				, this.cordova
	     				, this.wifiManager);
	     	}
	     	else if (action.equals(IsScanAlwaysAvailable.ACTION_NAME)) {
	     		cordovaAction = new IsScanAlwaysAvailable(action, args, callbackContext, this.cordova, this.wifiManager);
	     	}
	     	else if (action.equals(StartScan.ACTION_NAME)) {
	     		cordovaAction = new StartScan(action, args, callbackContext, this.cordova, this.wifiManager);
	     	}
	     	
	     	if (cordovaAction != null) {
	     		this.cordova.getThreadPool().execute(cordovaAction);
	     		return true;
	     	}
	     	
	     	return super.execute(action, args, callbackContext);
	     }
}
