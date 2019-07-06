package com.example.gpeegpee.oreobackgroundservicetestapplication.receiver;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
public class AppUpgradeReceiver extends BroadcastReceiver {
    private static String TAG = "AppUpgradeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "App was upgraded");
        craete_notify(context);
    }

    @TargetApi(26)
    private void craete_notify(Context context) {
        String CHANNEL_ID = "ChannelID";
        int notificationId = 111;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Channel title",NotificationManager.IMPORTANCE_DEFAULT);
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle("App is installed")
                .setContentText("Content").build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, notification);
    }
}
