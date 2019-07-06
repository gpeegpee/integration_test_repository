package com.example.gpeegpee.oreobackgroundservicetestapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AidlStartAndBindService extends Service {
    private static final String TAG = "AidlServiceTest";

    IBinder mBinder = new AidlStartAndBindServiceBinder();

    public class AidlStartAndBindServiceBinder extends Binder {
        public AidlStartAndBindService getService() {
            return AidlStartAndBindService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        Toast.makeText(this, "AidlStartAndBindService is created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "AidlStartAndBindService is binded", Toast.LENGTH_SHORT).show();
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        Toast.makeText(this, "AidlStartAndBindService is destroyed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "onTrimMemory");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        Toast.makeText(this, "AidlStartAndBindService is unbinded", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }
}
