package com.example.alarmdemo;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private DatePicker datePicker;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        findViewById(R.id.schedule_alarm_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.schedule_alarm_button:
                onScheduleAlarmButtonClicked();
                break;
        }
    }

    private void onScheduleAlarmButtonClicked() {
        Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());

        long time = calendar.getTimeInMillis();
        String formattedTime = Utils.getDate(time);
        scheduleAlarm(time);
        Toast.makeText(this, "Alarm will trigger at: " + formattedTime, Toast.LENGTH_SHORT).show();
    }

    private void scheduleAlarm(long triggerTime) {
        final int ALARM_REQUEST_CODE = 1;
        Intent alarmReceiver = new Intent(this, AlarmReceiver.class);
        AlarmManagerDelegate amd = new AlarmManagerDelegate(this);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, alarmReceiver, PendingIntent.FLAG_UPDATE_CURRENT);
        boolean isAlarmAlreadyScheduled = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, alarmReceiver, PendingIntent.FLAG_NO_CREATE) != null;
        if(isAlarmAlreadyScheduled) {
            Log.d(TAG, "Cancelling previous alarm");
            amd.cancel(pendingIntent);
        }
        amd.set(triggerTime, pendingIntent);
    }
}
