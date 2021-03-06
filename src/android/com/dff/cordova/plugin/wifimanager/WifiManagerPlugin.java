package com.dff.cordova.plugin.wifimanager;

import android.Manifest;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.action.CordovaAction;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.wifimanager.action.*;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class WifiManagerPlugin extends CommonPlugin {
    private static final String LOG_TAG = "com.dff.cordova.plugin.wifimanager.WifiManagerPlugin";
    private static final String[] PERMISSIONS =
        {
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,
            Manifest.permission.CHANGE_WIFI_STATE
        };

    protected NetworkConnectivityReceiver networkConnectivityReceiver;
    protected NetworkScanReceiver networkScanReceiver;
    protected HashMap<String, Class<? extends WifiManagerAction>> actions = new HashMap<String, Class<? extends WifiManagerAction>>();
    protected WifiManager wifiManager;

    public WifiManagerPlugin() {
        super(LOG_TAG);

        // actions.put(AddNetwork.ACTION_NAME, AddNetwork.class);
        actions.put(CalculateSignalLevel.ACTION_NAME, CalculateSignalLevel.class);
        actions.put(CompareSignalLevel.ACTION_NAME, CompareSignalLevel.class);
        actions.put(DisableNetwork.ACTION_NAME, DisableNetwork.class);
        actions.put(Disconnect.ACTION_NAME, Disconnect.class);
        actions.put(EnableNetwork.ACTION_NAME, EnableNetwork.class);
        actions.put(GetConfiguredNetworks.ACTION_NAME, GetConfiguredNetworks.class);
        actions.put(GetConnectionInfo.ACTION_NAME, GetConnectionInfo.class);
        actions.put(GetDhcpInfo.ACTION_NAME, GetDhcpInfo.class);
        actions.put(GetScanResults.ACTION_NAME, GetScanResults.class);
        actions.put(GetWifiState.ACTION_NAME, GetWifiState.class);
        actions.put(IsScanAlwaysAvailable.ACTION_NAME, IsScanAlwaysAvailable.class);
        actions.put(IsWifiEnabled.ACTION_NAME, IsWifiEnabled.class);
        actions.put(PingSupplicant.ACTION_NAME, PingSupplicant.class);
        actions.put(Reassociate.ACTION_NAME, Reassociate.class);
        actions.put(Reconnect.ACTION_NAME, Reconnect.class);
        actions.put(RemoveNetwork.ACTION_NAME, RemoveNetwork.class);
        actions.put(SaveConfiguration.ACTION_NAME, SaveConfiguration.class);
        actions.put(SetWifiEnabled.ACTION_NAME, SetWifiEnabled.class);
        actions.put(StartScan.ACTION_NAME, StartScan.class);
    }

    private void requestPermissions() {
        for (String permission : PERMISSIONS) {
            CommonPlugin.addPermission(permission);
        }
    }

    /**
     * Called after plugin construction and fields have been initialized.
     */
    @Override
    public void pluginInitialize() {
        super.pluginInitialize();
        requestPermissions();
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
     * <p>
     * This method is called from the WebView thread.
     * To do a non-trivial amount of work, use:
     * cordova.getThreadPool().execute(runnable);
     * <p>
     * To run on the UI thread, use:
     * cordova.getActivity().runOnUiThread(runnable);
     *
     * @param action          The action to execute.
     * @param args            The exec() arguments.
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
        } else if (action.equals("onWifiStateChanged")) {
            this.networkConnectivityReceiver.setWifistateCallbackcontext(callbackContext);

            return true;
        } else if (action.equals("onScanResultsAvaiblable")) {
            this.networkScanReceiver.setScanResultCallback(callbackContext);

            return true;
        } else if (actions.containsKey(action)) {
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
