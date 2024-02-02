package com.example.servo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servo.R;
import com.example.servo.Models.NewComplaint;

import java.util.ArrayList;

public class WorkerPendingRecycViewAdapter extends RecyclerView.Adapter<WorkerPendingRecycViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NewComplaint> complaints;

    public WorkerPendingRecycViewAdapter(Context context, ArrayList<NewComplaint> complaints) {
        this.context = context;
        this.complaints = complaints;
    }


    @NonNull
    @Override
    public WorkerPendingRecycViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_completed_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerPendingRecycViewAdapter.ViewHolder holder, int position) {
        NewComplaint tmp = complaints.get(position);

        holder.workerUid.setText(tmp.getUid());
        holder.workerDate.setText(String.valueOf(tmp.getDate()));
        holder.workerTime.setText(String.valueOf(tmp.getTime()));
        holder.workerPhone.setText(tmp.getPhoneStudent());
        holder.workerRoom.setText(String.valueOf(tmp.getRoomno()));

    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView workerUid;
        public TextView workerRoom;
        public TextView workerPhone;
        public TextView workerDate;
        public TextView workerTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            workerUid = itemView.findViewById(R.id.workerCompUid);
            workerRoom = itemView.findViewById(R.id.workerCompRoom);
            workerPhone = itemView.findViewById(R.id.workerCompPhone);
            workerDate = itemView.findViewById(R.id.workerCompDate);
            workerTime = itemView.findViewById(R.id.workerCompTIme);

            workerUid.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {

        }
    }

}
