package com.example.gpeegpee.oreobackgroundservicetestapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.gpeegpee.oreobackgroundservicetestapplication.service.SampleJobIntentService;
import com.example.gpeegpee.oreobackgroundservicetestapplication.service.SampleJobService;
import com.example.gpeegpee.oreobackgroundservicetestapplication.service.StartService;

import java.util.HashMap;
import java.util.Map;

public class BluetoothTestReceiver extends BroadcastReceiver {
    private static String TAG = "OreoBluetoothTestReceiver";
    private Context mContext = null;

    private static final Map<String, String> StringToAction = new HashMap<>();


    @Override
    public void onReceive(Context context, final Intent intent) {
        Log.i(TAG, "onReceive");
        mContext = context;
        String action = intent.getAction();
        Log.i(TAG, "Action:" + action);

        StringToAction.put("Test", "Result");
        String result = StringToAction.get(null);
        Log.i(TAG, "Map Result" + result);

        startService();
    }

    private void startService() {
        Intent intent = new Intent();
        intent.setClass(mContext, StartService.class);
        try {
            mContext.startService(intent);
            //ContextCompat.startForegroundService(mContext,intent);
//            SampleJobService.schedule(mContext);
//            SampleJobIntentService.enqueueWork(mContext, new Intent());
        } catch (SecurityException e) {
            Log.e(TAG, "exception : " + e);
        } catch (IllegalStateException e) {
            Log.e(TAG, "exception : " + e);
        }
    }
}
