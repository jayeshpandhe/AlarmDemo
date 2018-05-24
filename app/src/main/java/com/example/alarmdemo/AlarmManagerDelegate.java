package com.example.alarmdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.util.Log;

public class AlarmManagerDelegate {
    private static final String TAG = AlarmManagerDelegate.class.getSimpleName();
    private Context mContext;

    public AlarmManagerDelegate(Context context) {
        mContext = context.getApplicationContext();
    }

    public void set(long triggerTime, PendingIntent pendingIntent) {
        AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        if(am != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                am.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
            else
                am.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
            Log.d(TAG, "Alarm scheduled: " + Utils.getDate(triggerTime));

            // TODO: Delete previous alarm if any and store new alarm
        }
    }

    public void cancel(PendingIntent pendingIntent) {
        AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        if(am != null) {
            am.cancel(pendingIntent);
            Log.d(TAG, "Alarm cancelled");
            // TODO: Delete stored alarm
        }
    }
}
