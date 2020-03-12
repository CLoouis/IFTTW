//package com.example.ifttw;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.animation.RotateAnimation;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.ifttw.MyApp.db;
//
//public class DummyDetailActivity extends AppCompatActivity {
//
//    private RecyclerView myRecyclerview;
//    private RecyclerAdapter recyclerAdapter;
//    private List<Routines> listRoutine = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dummy_detail);
//
//        myRecyclerview = findViewById(R.id.recycler_view);
//
//        fetchDataFromRoom();
//        initRecyclerView();
//        setAdapter();
//
//    }
//    private void fetchDataFromRoom() {
//        db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class,"Routines").allowMainThreadQueries().fallbackToDestructiveMigration().build();
//        listRoutine = db.userDao().getAll();
//        Log.d("size : ", Integer.toString(listRoutine.size()));
//
//    }
//    private void initRecyclerView() {
//        myRecyclerview.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        myRecyclerview.setLayoutManager(llm);
//        recyclerAdapter =new RecyclerAdapter(this,listRoutine,this);
//    }
//    private void setAdapter() {
//        myRecyclerview.setAdapter(recyclerAdapter);
//    }
//}
