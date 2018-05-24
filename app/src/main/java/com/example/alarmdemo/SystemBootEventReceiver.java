package com.example.alarmdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class SystemBootEventReceiver extends BroadcastReceiver {
    private static final String TAG = SystemBootEventReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!TextUtils.isEmpty(intent.getAction())) {
            switch(intent.getAction()) {
                case Intent.ACTION_BOOT_COMPLETED:
                    onSystemBooted(context);
                    break;
            }
        }
    }

    private void onSystemBooted(Context context) {
        // TODO: Reset alarms
        Log.d(TAG, "System booted");
    }
}
