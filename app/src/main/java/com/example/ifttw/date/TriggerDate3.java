package com.example.ifttw.date;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ifttw.NotificationReceiver;
import com.example.ifttw.R;
import com.example.ifttw.TimerReceiver;
import com.example.ifttw.create_routine;

import java.util.Calendar;
import java.util.Date;

public class TriggerDate3 extends AppCompatActivity implements View.OnClickListener {
    Button btnConfirm, btnTimePicker, btnDatePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    // Default value ; nb
    Calendar currentTime = Calendar.getInstance();
    int currHour = currentTime.get(Calendar.HOUR_OF_DAY);
    final String DEFAULT_DATE = "1-1-2021";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigger_date3);

        final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        btnDatePicker = (Button)findViewById(R.id.button6);
        txtDate = (EditText)findViewById((R.id.editText4));
        txtDate.setText(DEFAULT_DATE);
        btnDatePicker.setOnClickListener(this);

        btnTimePicker = (Button)findViewById(R.id.button5);
        txtTime = (EditText)findViewById(R.id.editText2);
        txtTime.setText(currHour + ":00");
        btnTimePicker.setOnClickListener(this);

        btnConfirm = (Button)findViewById(R.id.confirmbtn);
        btnConfirm.setOnClickListener(this);

        ToggleButton alarmToggle = findViewById(R.id.CheckTrigger3);
        Intent notifyIntent = new Intent(this, NotificationReceiver.class);
        final PendingIntent alarmUp = PendingIntent.getBroadcast(this, 3, notifyIntent,
                PendingIntent.FLAG_NO_CREATE);
        alarmToggle.setChecked(alarmUp != null);

        alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        String toastMassage;
                        if (isChecked) {
                            toastMassage = "Stand up Alarm On";
                        } else {
                            if (alarmManager != null) {
                                alarmManager.cancel(alarmUp);
                                alarmUp.cancel();
                            }
                            toastMassage = "Stand up Alarm Off";
                        }
                        Toast.makeText(TriggerDate3.this, toastMassage, Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            mYear = year;
                            mMonth = monthOfYear;
                            mDay = dayOfMonth;

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if (minute < 10) txtTime.setText(hourOfDay + ":0" + minute);
                            else txtTime.setText(hourOfDay + ":" + minute);

                            mHour = hourOfDay;
                            mMinute = minute;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnConfirm) {
            callTimer(mYear, mMonth, mDay, mHour, mMinute);
            launchConfirmation(v);
        }
    }

    public void callTimer(int mYear, int mMonth, int mDay, int mHour, int mMinute) {
        Calendar calSet = Calendar.getInstance();
        calSet.set(Calendar.YEAR, mYear);
        calSet.set(Calendar.MONTH, mMonth);
        calSet.set(Calendar.DAY_OF_MONTH, mDay);

        calSet.set(Calendar.HOUR_OF_DAY, mHour);
        calSet.set(Calendar.MINUTE, mMinute);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        Intent timerModuleIntent = new Intent(this, TimerReceiver.class);
        timerModuleIntent.putExtra("type", 3);
        timerModuleIntent.putExtra("date", calSet.getTimeInMillis());

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, 0, timerModuleIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        try {
            notifyPendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public void launchConfirmation(View view) {
        Intent intent = new Intent(this, create_routine.class);
        startActivity(intent);
    }
}






