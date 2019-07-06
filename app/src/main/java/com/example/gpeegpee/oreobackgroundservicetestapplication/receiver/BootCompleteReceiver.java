package com.example.gpeegpee.oreobackgroundservicetestapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.gpeegpee.oreobackgroundservicetestapplication.service.SampleJobIntentService;
import com.example.gpeegpee.oreobackgroundservicetestapplication.service.SampleJobService;
import com.example.gpeegpee.oreobackgroundservicetestapplication.service.StartService;

public class BootCompleteReceiver extends BroadcastReceiver {
    private static String TAG = "OreoBootCompleteReceiver";
    private Context mContext = null;

    @Override
    public void onReceive(Context context, final Intent intent) {
        mContext = context;
        String action = intent.getAction();
        Log.i(TAG, "service is not running, action:" + action);
        startService();
    }

    private void startService() {
        Intent intent = new Intent();
        intent.setClass(mContext, StartService.class);
        try {
            //mContext.startService(intent);
            //ContextCompat.startForegroundService(mContext,intent);
            SampleJobService.schedule(mContext);
            //SampleJobIntentService.enqueueWork(mContext, new Intent());
        } catch (SecurityException e) {
            Log.w(TAG, "exception : " + e);
        } catch (IllegalStateException e) {
            Log.w(TAG, "exception : " + e);
        }
    }
}
