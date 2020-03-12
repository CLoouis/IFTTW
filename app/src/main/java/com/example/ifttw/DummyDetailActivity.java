package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.RotateAnimation;

import java.util.ArrayList;
import java.util.List;

import static com.example.ifttw.MyApp.db;

public class DummyDetailActivity extends AppCompatActivity {

    RecyclerView myRecyclerview;
    RecyclerAdapter recycleAdapter;
    List<Routines> listRoutine = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_detail);

        myRecyclerview = findViewById(R.id.myRecyclerView);

        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();

    }
    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"Routines").allowMainThreadQueries().build();
        listRoutine = db.userDao().getAll();


//        //just checking data from db
//        for (int i = 0 ; i < listRoutine.size(); i++) {
//            Log.e("Aplikasi",listRoutine.get(i).getCondition()+i);
//            Log.e("Aplikasi",listRoutine.get(i).getAction()+i);
//        }
    }
    private void initRecyclerView() {
        myRecyclerview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerview.setLayoutManager(llm);
        recycleAdapter =new RecyclerAdapter(this,listRoutine);
    }
    private void setAdapter() {
        myRecyclerview.setAdapter(recycleAdapter);
    }
}
