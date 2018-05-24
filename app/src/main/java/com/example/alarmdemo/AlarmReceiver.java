package com.example.alarmdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getSimpleName();
    private static final String CHANNEL_ID = "1";


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Alarm broadcast received");
        onAlarmTriggered(context);
    }

    private void onAlarmTriggered(Context context) {
        createNotificationChannel(context);
        showNotification(context);
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if(notificationManager != null) {
                CharSequence name = "Demo_App_Channel";
                String description = "Demo_App_Channel_Description";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private void showNotification(Context context) {
        long milliSeconds = System.currentTimeMillis();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Alarm triggered")
                .setContentText("Alarm time: " + Utils.getDate(milliSeconds))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null) {
            notificationManager.notify(1, builder.build());
        }
    }
}
