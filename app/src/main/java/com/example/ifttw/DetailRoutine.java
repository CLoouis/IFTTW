package com.example.ifttw;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.Calendar;

import static com.example.ifttw.MyApp.db;

public class DetailRoutine extends AppCompatActivity {
    Routines row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_routine);

        final TextView idRoutine = findViewById(R.id.label_id_routine_detail);
        row = db.userDao().getById(getIntent().getIntExtra("idRoutine", 0));
        idRoutine.setText(Integer.toString(row.getIdRoutine()));

        TextView triggerType = findViewById(R.id.label_trigger_detail);
        int triggerCode = row.getTriggerType();
        if (triggerCode == 1) {
            triggerType.setText("Timer : Daily");
        } else if (triggerCode == 2) {
            triggerType.setText("Timer : Weekly");
        } else if (triggerCode == 3) {
            triggerType.setText("Timer : One time");
        } else if (triggerCode == 4) {
            triggerType.setText("Sensor");
        }

        TextView actionType = findViewById(R.id.label_action_detail);
        int actionCode = row.getActionType();
        if (actionCode == 1) {
            actionType.setText("Notification");
        } else if (actionCode == 2) {
            actionType.setText("Turn On Wifi");
        } else if (actionCode == 3) {
            actionType.setText("Turn Off Wifi");
        } else if (actionCode == 4) {
            actionType.setText("Call External API");
        }

        TextView details = findViewById(R.id.label_details);
        if (triggerCode == 1) {
            details.setText("Hour : " + row.getHour() + "\nMinute : " + row.getMinute());
        } else if (triggerCode == 2) {
            details.setText("Day : " + row.getDay() + "\nHour : " + row.getHour() + "\nMinute : " + row.getMinute());
        } else if (triggerCode == 3) {
            details.setText("Year : " + row.getYear() + "\nMonth : " + row.getMonth() + "\nDay : " + row.getDay() + "\nHour : " + row.getHour() + "\nMinute : " + row.getMinute());
        } else if (triggerCode == 4) {
            details.setText("Proximity");
        }

        ToggleButton checkStatus = findViewById(R.id.statusRoutine);
        checkStatus.setChecked(row.getStatus() == 1);

        Intent intent = new Intent(this, NotificationReceiver.class);
        final PendingIntent statusRoutinePendingIntent = PendingIntent.getBroadcast(
                this,
                row.getIdRoutine(),
                intent, PendingIntent.FLAG_NO_CREATE
        );
        checkStatus.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            // create new pending intent to create routine
                            // that have been cancelled
                            createRoutineBasedOnIdRoutine(getIntent().getIntExtra("idRoutine", 0));
                            db.userDao().update(1, getIntent().getIntExtra("idRoutine", 0));
                            int idRoutine = getIntent().getIntExtra("idRoutine", 0);
                            Routines row = db.userDao().getById(idRoutine);
                            if (row.getTriggerType() == 4) {
                                Bundle bundle = new Bundle();
                                bundle.putInt("actionType", row.getActionType());
                                bundle.putInt("idRoutine", row.getIdRoutine());
                                bundle.putString("title", row.getTitle());
                                bundle.putString("description", row.getDescription());
                                if (idRoutine != 0) {
                                    if (SensorService.listAction.size() == 0) {
                                        Intent serviceIntent = new Intent(getApplicationContext(), SensorService.class);
                                        startForegroundService(serviceIntent);
                                    }
                                    SensorService.listAction.put(idRoutine, bundle);
                                }
                            }
                        } else {
                            if (statusRoutinePendingIntent != null) {
                                statusRoutinePendingIntent.cancel();
                            }
                            //set status in db to 0
                            db.userDao().update(0, getIntent().getIntExtra("idRoutine", 0));
                            int idRoutine = getIntent().getIntExtra("idRoutine", 0);
                            Routines row = db.userDao().getById(idRoutine);
                            if (row.getTriggerType() == 4) {
                                if (idRoutine != 0) SensorService.listAction.remove(idRoutine);
                                if (SensorService.listAction.size() == 0) {
                                    Intent serviceIntent = new Intent(getApplicationContext(), SensorService.class);
                                    stopService(serviceIntent);
                                }
                            }
                        }
                    }
                }
        );

        Button deleteRoutine = findViewById(R.id.deleteRoutine);
        // delete database and launch main
        deleteRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (statusRoutinePendingIntent != null) {
                    statusRoutinePendingIntent.cancel();
                }
                Routines row = db.userDao().getById(getIntent().getIntExtra("idRoutine", 0));
                db.userDao().deleteUsers(getIntent().getIntExtra("idRoutine", 0));
                int idRoutine = row.getIdRoutine();
                if (row.getTriggerType() == 4) {
                    if (idRoutine != 0) SensorService.listAction.remove(idRoutine);
                    if (SensorService.listAction.size() == 0) {
                        Intent serviceIntent = new Intent(getApplicationContext(), SensorService.class);
                        stopService(serviceIntent);
                    }
                }
                goToHome(v);
            }
        });
    }

    public void createRoutineBasedOnIdRoutine(int idRoutine) {
//        Routines row = db.userDao().getById(idRoutine);
        int triggerType = row.getTriggerType();
        Bundle routineBundle = new Bundle();
        routineBundle.putInt("triggerType", triggerType);

        if (triggerType == 1 || triggerType == 2 || triggerType == 3) {
            Intent triggerTimer = new Intent(this, TimerReceiver.class);
            Calendar calSet = Calendar.getInstance();

            if (triggerType == 1) {

                calSet.set(Calendar.HOUR_OF_DAY, row.getHour());
                calSet.set(Calendar.MINUTE,row.getMinute());
                calSet.set(Calendar.SECOND, 0);
                calSet.set(Calendar.MILLISECOND, 0);

            } else if (triggerType == 2) {

                calSet.set(Calendar.DAY_OF_MONTH, row.getDay());
                calSet.set(Calendar.HOUR_OF_DAY,row.getHour());
                calSet.set(Calendar.MINUTE,row.getMinute());
                calSet.set(Calendar.SECOND, 0);
                calSet.set(Calendar.MILLISECOND, 0);

            } else if (triggerType == 3) {

                calSet.set(Calendar.YEAR, row.getYear());
                calSet.set(Calendar.MONTH, row.getMonth());
                calSet.set(Calendar.DAY_OF_MONTH, row.getDay());

                calSet.set(Calendar.HOUR_OF_DAY, row.getHour());
                calSet.set(Calendar.MINUTE,row.getMinute());
                calSet.set(Calendar.SECOND, 0);
                calSet.set(Calendar.MILLISECOND, 0);

            }

            routineBundle.putLong("date", calSet.getTimeInMillis());

            routineBundle.putInt("actionType", row.getActionType());
            if (row.getActionType() == 1) {
                routineBundle.putString("title", row.getTitle());
                routineBundle.putString("description", row.getDescription());
            }
            triggerTimer.putExtras(routineBundle);

            PendingIntent triggerTimerPendingIntent = PendingIntent.getBroadcast(
                    this, 0, triggerTimer, PendingIntent.FLAG_UPDATE_CURRENT
            );

            try {
                triggerTimerPendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }

    }

    public void goToHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
