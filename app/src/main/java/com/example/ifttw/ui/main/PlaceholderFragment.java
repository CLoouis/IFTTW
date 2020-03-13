package com.example.ifttw.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.ifttw.AppDatabase;
import com.example.ifttw.DetailRoutine;
import com.example.ifttw.Dummy;
import com.example.ifttw.MainActivity;
import com.example.ifttw.R;
import com.example.ifttw.RecyclerAdapter;
import com.example.ifttw.Routines;
import com.example.ifttw.create_routine;

import java.util.ArrayList;
import java.util.List;

import static com.example.ifttw.MyApp.db;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements RecyclerAdapter.OnEventListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private RecyclerView myRecyclerview;
    private RecyclerAdapter recyclerAdapter;
    private List<Routines> listRoutine = new ArrayList<>();


//    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
//        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        myRecyclerview = root.findViewById(R.id.recycler_view);

        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        fetchDataFromRoom(index);
        initRecyclerView();
        setAdapter();

        return  root;
    }

    private void fetchDataFromRoom(int index) {
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class,"Routines").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        if (index == 1) {
            listRoutine = db.userDao().getAllActive();
        } else {
            listRoutine = db.userDao().getAllInactive();
        }
        Log.d("size : ", Integer.toString(listRoutine.size()));

    }
    private void initRecyclerView() {
        myRecyclerview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerview.setLayoutManager(llm);

        recyclerAdapter =new RecyclerAdapter(getActivity(),listRoutine, this);
    }
    private void setAdapter() {
        myRecyclerview.setAdapter(recyclerAdapter);
    }

    @Override
    public void onEventClick(int position) {
        Routines row = listRoutine.get(position);
        Log.d("row", Integer.toString(row.getIdRoutine()));
        Intent intent = new Intent(getActivity(), DetailRoutine.class);
        intent.putExtra("idRoutine", row.getIdRoutine());
        intent.putExtra("triggerType", row.getTriggerType());
        intent.putExtra("actionType", row.getActionType());
        intent.putExtra("status", row.getStatus());
        startActivity(intent);
    }
}