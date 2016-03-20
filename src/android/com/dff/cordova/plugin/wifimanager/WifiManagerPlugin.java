package com.dff.cordova.plugin.wifimanager;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;

public class WifiManagerPlugin extends CommonPlugin {
	private static final String LOG_TAG = "com.dff.cordova.plugin.wifimanager.WifiManagerPlugin";	
	protected NetworkConnectivityReceiver networkConnectivityReceiver;
	
	public WifiManagerPlugin() {
		super();
	}
	
	   /**
		* Called after plugin construction and fields have been initialized.
		*/
		@Override
		public void pluginInitialize() {
			super.pluginInitialize();
			networkConnectivityReceiver = new NetworkConnectivityReceiver(this.cordova.getActivity());
		}
		
		@Override
		public void onDestroy() {
			super.onDestroy();
			this.networkConnectivityReceiver.onDestroy();
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
	     	
	     	if (action.equals("onNetworkStateChanged")) {
	     		this.networkConnectivityReceiver.setNetworStateCallbackcontext(callbackContext);
	     		return true;
	     	}
	     	else if (action.equals("onWifiStateChanged")) {
	     		this.networkConnectivityReceiver.setWifistateCallbackcontext(callbackContext);
	     		
	     		return true;
	     	}
	     	
	     	return super.execute(action, args, callbackContext);
	     }
}
