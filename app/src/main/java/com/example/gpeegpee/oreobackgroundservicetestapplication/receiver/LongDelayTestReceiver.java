package com.example.gpeegpee.oreobackgroundservicetestapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
public class LongDelayTestReceiver extends BroadcastReceiver {

    private static final String TAG = "LongDelayTestReceiver";
    private Context mContext;


    //Settings
    public static final String INTENT_NEARBY_SCAN_SETTING = "com.samsung.android.nearbyscanning";
    public static final String INTENT_NEARBY_RESPONSE_SETTING =
            "com.samsung.android.oneconnect.DEVICE_VISIBILITY";
    public static final String EXTRA_DEVICE_VISIBILITY_VALUE = "DEVICE_VISIBILITY_VALUE";
    // from QuickPannel and Settings extra value
    public static final String EXTRA_DEVICE_VISIBILITY_FROM = "DEVICE_VISIBILITY_FROM";
    // QuickPannel and Settings extra value [QuickPannel, QuickPannel_detail, Settings,
    // Settings_more]

    //Board Pending intent
    public static final String INTENT_QUICKCONNECT_START_MAIN =
            "com.samsung.android.oneconnect.START_MAIN_ACTIVITY";

    //StatusBar
    public static final String INTENT_STATUSBAR_EXPANDED =
            "com.samsung.systemui.statusbar.EXPANDED";

    //SmartView
    public static final String INTENT_SMARTVIEW_DLNA_START =
            "com.sec.android.screensharing.DLNA_STATUS";
    public static final String INTENT_SMARTVIEW_DLNA_START_O_OS =
            "com.samsung.intent.action.DLNA_STATUS_CHANGED";

    public static final String INTENT_SMARTVIEW_MIRRORING_SOURCE_START =
            "com.samsung.intent.action.WIFI_DISPLAY_SOURCE_STATE";
    public static final String INTENT_SMARTVIEW_MIRRORING_SINK_START =
            "com.samsung.intent.action.WIFI_DISPLAY_SINK_STATE";

    // Wearable
    private static final String INTENT_WEARABLE_WATCH_CONNECTED =
            "com.samsung.android.wearable.action.WEARABLE_DEVICE_CONNECTED";
    private static final String INTENT_WEARABLE_CIRCLE_CONNECTED =
            "com.samsung.android.necklet.ACTION_CIRCLE_CONNECTION_STATUS";
    public static final String INTENT_WEARABLE_GEAR360_CONNECTED =
            "com.samsung.android.appcessory.DEVICE_CONNECTION_STATE_CHANGED";

    //Bluetooth Profile
    private static final String INTENT_BLUETOOTH_A2DP_CONNECTED =
            "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED";
    //BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED:
    private static final String INTENT_BLUETOOTH_A2DPSINK_CONNECTED =
            "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED";
    //BluetoothA2dpSink.ACTION_CONNECTION_STATE_CHANGED:   // TODO: check .. can receive it? name
    // has"-"
    private static final String INTENT_BLUETOOTH_BOND_STATE_CHANGED =
            "android.bluetooth.device.action.BOND_STATE_CHANGED";
    private static final String INTENT_BLUETOOTH_HEADSET_CONNECTED =
            "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED";
    //BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED
    private static final String INTENT_BLUETOOTH_INPUTDEVICE_CONNECTED =
            "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED";
    //BluetoothInputDevice.ACTION_CONNECTION_STATE_CHANGED:
    private static final String INTENT_BLUETOOTH_ACL_CONNECTED =
            "android.bluetooth.device.action.ACL_CONNECTED";
    //BluetoothDevice.ACTION_ACL_CONNECTED  //TODO: check receive case
    private static final String INTENT_BLUETOOTH_HID_HOST_CONNECTED =
            "android.bluetooth.device.action.CONNECTION_HID_HOST";

    // Gear connection (SPP)
    private static final String INTENT_GEAR_CONNECTION_STATE_CHANGED =
            "com.samsung.bluetooth.action.GEAR_CONNECTION_STATE_CHANGED";

    //SideSync
    private static final String INTENT_SIDESYNC_CONNECTED =
            "com.sec.android.sidesync.source.SIDESYNC_CONNECTED";

    //DEX
    private static final String INTENT_DEX_MODE_START =
            "android.app.action.ENTER_KNOX_DESKTOP_MODE";

    private static final String INTENT_DIAGNOSTIC_INFO_CHANGED =
            "com.samsung.settings.DIAGNOSTIC_INFO_CHANGED";


    enum IntentActionType {
        ACTION_BOOT_COMPLETED(Intent.ACTION_BOOT_COMPLETED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }

            @Override
            void handleIntentForGuest(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_SIDESYNC_CONNECTED(LongDelayTestReceiver.INTENT_SIDESYNC_CONNECTED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }

            @Override
            void handleIntentForGuest(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        ACTION_PACKAGE_REPLACED(Intent.ACTION_PACKAGE_REPLACED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_NEARBY_SCAN_SETTING(LongDelayTestReceiver.INTENT_NEARBY_SCAN_SETTING) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_NEARBY_RESPONSE_SETTING(LongDelayTestReceiver.INTENT_NEARBY_RESPONSE_SETTING) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_QUICKCONNECT_START_MAIN(LongDelayTestReceiver.INTENT_QUICKCONNECT_START_MAIN) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_SMARTVIEW_DLNA_START(LongDelayTestReceiver.INTENT_SMARTVIEW_DLNA_START) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_SMARTVIEW_DLNA_START_O_OS(LongDelayTestReceiver.INTENT_SMARTVIEW_DLNA_START_O_OS) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_SMARTVIEW_MIRRORING_SINK_START(LongDelayTestReceiver.INTENT_SMARTVIEW_MIRRORING_SINK_START) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_SMARTVIEW_MIRRORING_SOURCE_START(LongDelayTestReceiver.INTENT_SMARTVIEW_MIRRORING_SOURCE_START) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_BLUETOOTH_A2DP_CONNECTED(LongDelayTestReceiver.INTENT_BLUETOOTH_A2DP_CONNECTED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }

            @Override
            void handleIntentForGuest(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_BLUETOOTH_A2DPSINK_CONNECTED(LongDelayTestReceiver.INTENT_BLUETOOTH_A2DPSINK_CONNECTED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_BLUETOOTH_HEADSET_CONNECTED(LongDelayTestReceiver.INTENT_BLUETOOTH_HEADSET_CONNECTED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }

            @Override
            void handleIntentForGuest(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_BLUETOOTH_INPUTDEVICE_CONNECTED(LongDelayTestReceiver.INTENT_BLUETOOTH_INPUTDEVICE_CONNECTED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_BLUETOOTH_ACL_CONNECTED(LongDelayTestReceiver.INTENT_BLUETOOTH_ACL_CONNECTED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_GEAR_CONNECTION_STATE_CHANGED(LongDelayTestReceiver.INTENT_GEAR_CONNECTION_STATE_CHANGED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_BLUETOOTH_HID_HOST_CONNECTED(LongDelayTestReceiver.INTENT_BLUETOOTH_HID_HOST_CONNECTED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_WEARABLE_WATCH_CONNECTED(LongDelayTestReceiver.INTENT_WEARABLE_WATCH_CONNECTED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_WEARABLE_CIRCLE_CONNECTED(LongDelayTestReceiver.INTENT_WEARABLE_CIRCLE_CONNECTED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_WEARABLE_GEAR360_CONNECTED(LongDelayTestReceiver.INTENT_WEARABLE_GEAR360_CONNECTED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_DEX_MODE_START(LongDelayTestReceiver.INTENT_DEX_MODE_START) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_STATUSBAR_EXPANDED(LongDelayTestReceiver.INTENT_STATUSBAR_EXPANDED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_DIAGNOSTIC_INFO_CHANGED(LongDelayTestReceiver.INTENT_DIAGNOSTIC_INFO_CHANGED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }
        },

        INTENT_BLUETOOTH_BOND_STATE_CHANGED(LongDelayTestReceiver.INTENT_BLUETOOTH_BOND_STATE_CHANGED) {
            @Override
            void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {

            }

            @Override
            void handleIntentForGuest(Intent intent, LongDelayTestReceiver instance) {

            }
        };

        private static final Map<String, IntentActionType> StringToAction = new HashMap<>();
        static {
            for (IntentActionType action : values()) {
                StringToAction.put(action.toString(), action);
            }
        }

        private final String actionString;

        IntentActionType(String actionString) {
            this.actionString = actionString;
        }

        void handleIntentForOwner(Intent intent, LongDelayTestReceiver instance) {};

        void handleIntentForGuest(Intent intent, LongDelayTestReceiver instance) {};

        @Override
        public String toString() {
            return actionString;
        }

        public static IntentActionType getActionType(String rep) {
            return StringToAction.get(rep);
        }
    };

    @Override
    public void onReceive(Context context, final Intent intent) {
        Log.d(TAG, "onReceive");

        mContext = context;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                handleIntent(intent);
            }
        }, 60000);
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "action: " + action);

        if (IntentActionType.getActionType(action) != null) {
            Log.d(TAG, "getActionType success");
        }
    }
}
