package com.example.ifttw.date;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ifttw.NotificationReceiver;
import com.example.ifttw.R;
import com.example.ifttw.TimerReceiver;
import com.example.ifttw.create_routine;

import java.util.Calendar;

public class TriggerDate1 extends AppCompatActivity implements  View.OnClickListener {

    Button btnConfirm, btnTimePicker;
    EditText txtTime;
    private int mHour, mMinute;

    // Default value
    Calendar currentTime = Calendar.getInstance();
    int currHour = currentTime.get(Calendar.HOUR_OF_DAY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigger_date1);

        final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        btnTimePicker = findViewById(R.id.button5);
        txtTime = findViewById(R.id.editText2);
        txtTime.setEnabled(false);
        txtTime.setText(currHour + ":00");
        btnTimePicker.setOnClickListener(this);

        btnConfirm = findViewById(R.id.confirmbtn);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnTimePicker) {
            // Open Time Picker Dialog
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
            launchConfirmation(v);
        }
    }

    public void launchConfirmation(View view) {
        Intent intent = new Intent(this, create_routine.class);
        intent.putExtra("triggerType", 1);
        intent.putExtra("hour", mHour);
        intent.putExtra("minute", mMinute);
        startActivity(intent);
    }
}
