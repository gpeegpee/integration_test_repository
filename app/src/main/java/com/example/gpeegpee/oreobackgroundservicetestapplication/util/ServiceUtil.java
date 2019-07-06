package com.example.gpeegpee.oreobackgroundservicetestapplication.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class ServiceUtil {
    private static final String TAG = ServiceUtil.class.getSimpleName();

    public static boolean isServiceRunning(Context ctx, Class<?> serviceClass) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

        for(ActivityManager.RunningServiceInfo runningServiceInfo : services) {
            if (runningServiceInfo.service.getClassName().equals(serviceClass.getName())) {
                Log.d(TAG, "Equal: " + serviceClass.getName() + " with " + runningServiceInfo.service.getClassName());
                return true;
            }
        }
        return false;
    }
}
