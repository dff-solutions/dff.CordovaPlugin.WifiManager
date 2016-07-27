package com.dff.cordova.plugin.wifimanager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.action.CordovaAction;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.action.AddNetwork;
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
import com.dff.cordova.plugin.wifimanager.action.PingSupplicant;
import com.dff.cordova.plugin.wifimanager.action.Reassociate;
import com.dff.cordova.plugin.wifimanager.action.Reconnect;
import com.dff.cordova.plugin.wifimanager.action.RemoveNetwork;
import com.dff.cordova.plugin.wifimanager.action.SaveConfiguration;
import com.dff.cordova.plugin.wifimanager.action.SetWifiEnabled;
import com.dff.cordova.plugin.wifimanager.action.StartScan;
import com.dff.cordova.plugin.wifimanager.action.WifiManagerAction;

import android.content.Context;
import android.net.wifi.WifiManager;

public class WifiManagerPlugin extends CommonPlugin {
	private static final String LOG_TAG = "com.dff.cordova.plugin.wifimanager.WifiManagerPlugin";	
	protected NetworkConnectivityReceiver networkConnectivityReceiver;
	protected NetworkScanReceiver networkScanReceiver;
	
	public static HashMap<String, Class<? extends WifiManagerAction>> actions = new HashMap<String, Class<? extends WifiManagerAction>>();
	
	protected WifiManager wifiManager;
	
	public WifiManagerPlugin() {
		super(LOG_TAG);
		
		registerAction(AddNetwork.ACTION_NAME, AddNetwork.class);
		registerAction(CalculateSignalLevel.ACTION_NAME, CalculateSignalLevel.class);
		registerAction(CompareSignalLevel.ACTION_NAME, CompareSignalLevel.class);
		registerAction(DisableNetwork.ACTION_NAME, DisableNetwork.class);
		registerAction(Disconnect.ACTION_NAME, Disconnect.class);
		registerAction(EnableNetwork.ACTION_NAME, EnableNetwork.class);
		registerAction(GetConfiguredNetworks.ACTION_NAME, GetConfiguredNetworks.class);
		registerAction(GetConnectionInfo.ACTION_NAME, GetConnectionInfo.class);		
		registerAction(GetDhcpInfo.ACTION_NAME, GetDhcpInfo.class);
		registerAction(GetScanResults.ACTION_NAME, GetScanResults.class);
		registerAction(GetWifiState.ACTION_NAME, GetWifiState.class);
		registerAction(IsScanAlwaysAvailable.ACTION_NAME, IsScanAlwaysAvailable.class);
		registerAction(IsWifiEnabled.ACTION_NAME, IsWifiEnabled.class);
		registerAction(PingSupplicant.ACTION_NAME, PingSupplicant.class);
		registerAction(Reassociate.ACTION_NAME, Reassociate.class);
		registerAction(Reconnect.ACTION_NAME, Reconnect.class);
		registerAction(RemoveNetwork.ACTION_NAME, RemoveNetwork.class);
		registerAction(SaveConfiguration.ACTION_NAME, SaveConfiguration.class);
		registerAction(SetWifiEnabled.ACTION_NAME, SetWifiEnabled.class);
		registerAction(StartScan.ACTION_NAME, StartScan.class);
	}
	
	public static void registerAction(String name, Class<? extends WifiManagerAction> action) {
		actions.put(name, action);
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
     	else if (actions.containsKey(action)) {     		
     		Class<? extends WifiManagerAction> actionClass = actions.get(action);
     		
     		CordovaPluginLog.d(LOG_TAG, "found action: " + actionClass.getName());
     		
     		try {
				cordovaAction = actionClass.getConstructor(String.class
						, JSONArray.class
						, CallbackContext.class
						, CordovaInterface.class
						, WifiManager.class
					)
					.newInstance(action, args, callbackContext, this.cordova, this.wifiManager);
			} catch (InstantiationException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			} catch (IllegalAccessException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			} catch (InvocationTargetException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			} catch (SecurityException e) {
				CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
			}
     	}
     	
     	if (cordovaAction != null) {
     		this.cordova.getThreadPool().execute(cordovaAction);
     		return true;
     	}
     	
     	return super.execute(action, args, callbackContext);
     }
}
