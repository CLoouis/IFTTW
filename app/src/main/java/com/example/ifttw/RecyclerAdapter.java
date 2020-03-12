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

        TextView triggerTxt, actionTxt, idRoutine, statusTxt;
        public MyViewHolder(View v) {

            super(v);
            idRoutine = (TextView)v.findViewById(R.id.label_idrutin);
            triggerTxt = (TextView)v.findViewById(R.id.label_trigger);
            actionTxt = (TextView)v.findViewById(R.id.label_action);
            statusTxt = (TextView)v.findViewById(R.id.label_status);
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

        holder.idRoutine.setText((int) album.getIdRoutine());

        String valueTrigger = null;
        int triggerType = album.getTriggerType();
        if (triggerType == 1) valueTrigger = "Timer : Daily";
        else if (triggerType == 2) valueTrigger = "Timer : Weekly";
        else if (triggerType == 3) valueTrigger = "Timer : Once";
        else if (triggerType == 4) valueTrigger = "Sensor : Proximity";
        holder.triggerTxt.setText(valueTrigger);

        String valueAction = null;
        int actionType = album.getActionType();
        if (actionType == 1) valueAction = "Notification";
        else if (actionType == 2) valueAction = "Turn on Wifi";
        else if (actionType == 3) valueAction = "Turn off Wifi";
        else if (actionType == 4) valueAction = "Send email";
        holder.actionTxt.setText(valueAction);

        String valueStatus = null;
        int status = album.getStatus();
        if (status == 1) valueStatus = "Active";
        else if (status == 2) valueStatus = "No Active";
        holder.statusTxt.setText(valueStatus);

    }

    @Override
    public int getItemCount() {
        return (routinesList != null) ? routinesList.size() : 0;
    }
}