package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.ifttw.MyApp.db;

public class create_routine extends AppCompatActivity {

    private Bundle routineBundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        final int triggerType = getIntent().getIntExtra("triggerType", 0);
        final int actionType = getIntent().getIntExtra("actionType", 0);

        if (triggerType != 0 && actionType == 0) {
            routineBundle = getIntent().getExtras();
        }

        if (actionType != 0) {
            routineBundle = getIntent().getExtras();
        }

        ImageView plus_bttn = findViewById(R.id.plusbttn);
        plus_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTriggerServiceActivity(view);
            }
        });


        TextView triggerWord = findViewById(R.id.this2);
        if (routineBundle.getInt("triggerType") == 1){
            triggerWord.setText("Timer 1");
        } else if (routineBundle.getInt("triggerType") == 2) {
            triggerWord.setText("Timer 2");
        } else if (routineBundle.getInt("triggerType") == 3) {
            triggerWord.setText("Timer 3");
        } else if (routineBundle.getInt("triggerType") == 4) {
            triggerWord.setText("Sensor");
        }

        TextView what = findViewById(R.id.what);
        if (actionType == 1) {
            what.setText("Notification");
        } else if (actionType == 2) {
            what.setText("Turn On Wifi");
        } else if (actionType == 3) {
            what.setText("Turn Off Wifi");
        } else if (actionType == 4) {
            what.setText("call API");
        }

        what.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActionActivity(v);
            }
        });

        Button data = findViewById(R.id.checkData);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String toastMessage = "triggerType" + triggerType + "title, desc" + routineBundle.getString("title") + routineBundle.getString("description") + "actionType" + routineBundle.getInt("actionType");
//                Toast.makeText(create_routine.this, toastMessage, Toast.LENGTH_SHORT).show();
                createRoutine();
                insertRoutineToDatabase();
                goToHome(v);
            }
        });
    }

    public void launchTriggerServiceActivity(View view) {
        Intent intent = new Intent(this, TriggerActivity.class);
        startActivity(intent);
    }

    public void launchActionActivity(View v) {
        Intent intent = new Intent(this, ActionActivity.class);
        intent.putExtras(routineBundle);
        startActivity(intent);
    }

    public void goToHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void createRoutine() {
        int triggerType = routineBundle.getInt("triggerType");

        if (triggerType == 1 || triggerType == 2 || triggerType == 3) {
            Intent triggerTimer = new Intent(this, TimerReceiver.class);
            Calendar calSet = Calendar.getInstance();
            int idRoutine = (int) (calSet.getTimeInMillis() % 1000000000);
            routineBundle.putInt("idRoutine", idRoutine);

            if (triggerType == 1) {

                calSet.set(Calendar.HOUR_OF_DAY,routineBundle.getInt("hour"));
                calSet.set(Calendar.MINUTE,routineBundle.getInt("minute"));
                calSet.set(Calendar.SECOND, 0);
                calSet.set(Calendar.MILLISECOND, 0);

            } else if (triggerType == 2) {

                calSet.set(Calendar.DAY_OF_MONTH, routineBundle.getInt("day"));
                calSet.set(Calendar.HOUR_OF_DAY,routineBundle.getInt("hour"));
                calSet.set(Calendar.MINUTE,routineBundle.getInt("minute"));
                calSet.set(Calendar.SECOND, 0);
                calSet.set(Calendar.MILLISECOND, 0);

            } else if (triggerType == 3) {

                calSet.set(Calendar.YEAR, routineBundle.getInt("year"));
                calSet.set(Calendar.MONTH, routineBundle.getInt("month"));
                calSet.set(Calendar.DAY_OF_MONTH, routineBundle.getInt("day"));

                calSet.set(Calendar.HOUR_OF_DAY,routineBundle.getInt("hour"));
                calSet.set(Calendar.MINUTE,routineBundle.getInt("minute"));
                calSet.set(Calendar.SECOND, 0);
                calSet.set(Calendar.MILLISECOND, 0);

            }
            routineBundle.putLong("date", calSet.getTimeInMillis());
            triggerTimer.putExtras(routineBundle);
            PendingIntent triggerTimerPendingIntent = PendingIntent.getBroadcast(
                    this, 0, triggerTimer, PendingIntent.FLAG_UPDATE_CURRENT
            );
            try {
                triggerTimerPendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        } else if (triggerType == 4) {
            Intent triggerSensor = new Intent(this, SensorService.class);
            Calendar calSet = Calendar.getInstance();
            int idRoutine = (int) (calSet.getTimeInMillis() % 1000000000);
                routineBundle.putInt("idRoutine", idRoutine);
            triggerSensor.putExtras(routineBundle);
            SensorService.listAction.put(idRoutine,routineBundle);
            startService(triggerSensor);
        }
    }

    public void insertRoutineToDatabase() {
        db.userDao().insert(routineBundle.getInt("idRoutine"),
                routineBundle.getInt("triggerType"),
                routineBundle.getInt("year"),
                routineBundle.getInt("month"),
                routineBundle.getInt("day"),
                routineBundle.getInt("hour"),
                routineBundle.getInt("minute"),
                routineBundle.getInt("actionType"),
                routineBundle.getString("title"),
                routineBundle.getString("description"),
                1
                );
    }
}

