package com.example.ifttw.date;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.example.ifttw.R;
import com.example.ifttw.CreateRoutine;

import java.util.Calendar;

public class TriggerDate2 extends AppCompatActivity implements View.OnClickListener {
    Button btnConfirm, btnTimePicker;
    EditText txtTime;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private int  mDay, mHour, mMinute;

    // Default value
    Calendar currentTime = Calendar.getInstance();
    int currHour = currentTime.get(Calendar.HOUR_OF_DAY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigger_date2);

        // get selected radio button from radioGroup
        radioGroup = findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);

        btnTimePicker = (Button)findViewById(R.id.button_time);
        txtTime = (EditText)findViewById(R.id.textTime);
        txtTime.setText(currHour + ":00");

        btnTimePicker.setOnClickListener(this);
        btnConfirm = (Button)findViewById(R.id.confirmbtn);
        btnConfirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnTimePicker) {
            // Get Current Time as default value
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
            intent.putExtra("triggerType", 2);
            intent.putExtra("day", mDay);
            intent.putExtra("hour", mHour);
            intent.putExtra("minute", mMinute);
            startActivity(intent);
        }
    }







