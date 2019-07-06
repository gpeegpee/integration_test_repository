package com.example.gpeegpee.oreobackgroundservicetestapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
public class LocalBroadcastReceiver extends BroadcastReceiver {

    private static String TAG = "LocalBroadcastReceiver";
    private Context mContext = null;

    @Override
    public void onReceive(Context context, final Intent intent) {
        Log.i(TAG, "onReceive");
        mContext = context;
        String action = intent.getAction();
        Log.i(TAG, "Action:" + action);
    }
}
