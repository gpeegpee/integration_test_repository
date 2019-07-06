package com.example.gpeegpee.oreobackgroundservicetestapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.JobIntentService;
import android.util.Log;

public class SampleJobIntentService extends JobIntentService {

    private static final String TAG = "SampleJobIntentService";
    private static final int DOWNLOAD_JOB_ID = 1000;

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, SampleJobIntentService.class, DOWNLOAD_JOB_ID, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(@NonNull Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setInterruptIfStopped(boolean interruptIfStopped) {
        super.setInterruptIfStopped(interruptIfStopped);
    }

    @Override
    public boolean isStopped() {
        return super.isStopped();
    }

    @Override
    public boolean onStopCurrentWork() {
        return super.onStopCurrentWork();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        // We have received work to do.  The system or framework is already
        // holding a wake lock for us at this point, so we can just go.
        Log.i(TAG, "Executing work: " + intent);
        String label = intent.getStringExtra("label");
        if (label == null) {
            label = intent.toString();
        }

        for (int i = 0; i < 5; i++) {
            Log.i(TAG, "Running service " + (i + 1) + "/5 @ " + SystemClock.elapsedRealtime());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        Log.i(TAG, "Completed service @ " + SystemClock.elapsedRealtime());
    }
}
