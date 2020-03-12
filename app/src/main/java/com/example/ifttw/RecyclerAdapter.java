package com.example.ifttw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//package com.example.ifttw.ui.main;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.ifttw.R;
//import com.example.ifttw.Routines;
//
//import java.util.List;
//
public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Routines> routinesList;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView aksi, kondisi;
        public MyViewHolder(View v) {
            super(v);

            kondisi = (TextView)v.findViewById(R.id.condition);
            aksi = (TextView)v.findViewById(R.id.action);
        }
    }
    public RecyclerAdapter(Context mContext, List<Routines> routinesList) {
        this.mContext = mContext;
        this.routinesList = routinesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Routines album = routinesList.get(position);
        holder.kondisi.setText(album.getCondition());
        holder.aksi.setText(album.getAction());
    }

    @Override
    public int getItemCount() {
        return routinesList.size();
    }
}