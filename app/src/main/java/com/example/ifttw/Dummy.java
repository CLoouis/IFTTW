package com.example.ifttw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ifttw.R;
import com.example.ifttw.Routines;

import static com.example.ifttw.MyApp.db;


public class Dummy extends AppCompatActivity {

    Routines rutin;
    EditText aksi;
    EditText kondisi;
    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        aksi = findViewById(R.id.aksi);
        kondisi = findViewById(R.id.kondisi);
        insert = findViewById(R.id.insert);

    }

//    @Override
//    public void onClick(View v) {
//        if (v == insert) {
//            rutin = new Routines();
//            rutin.setAction(aksi.getText().toString());
//            rutin.setCondition(kondisi.getText().toString());
            // Insert rutin to database

//            startActivity(new Intent(this, DummyDetailActivity.class));
//        }
//    }
}

