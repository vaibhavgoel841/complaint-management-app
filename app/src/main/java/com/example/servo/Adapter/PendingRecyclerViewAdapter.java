package com.example.servo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servo.ComplaintDetails;
import com.example.servo.R;
import com.example.servo.Models.NewComplaint;

import java.util.ArrayList;

public class PendingRecyclerViewAdapter extends RecyclerView.Adapter<PendingRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NewComplaint> complaints;
    private ArrayList<NewComplaint> completedComplaints;


    public PendingRecyclerViewAdapter(Context context, ArrayList<NewComplaint> complaints) {
        this.context = context;
        this.complaints = complaints;
        completedComplaints = new ArrayList<>();
    }

    //where to get the single card to show in list
    @NonNull
    @Override
    public PendingRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieved_layout,parent,false);


        return new ViewHolder(view);
    }

    // what will happen after getting card object
    @Override
    public void onBindViewHolder(@NonNull PendingRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NewComplaint currComp = complaints.get(position);
        String date = String.valueOf(currComp.getDate());
        String time = String.valueOf(currComp.getTime());
        holder.Date.setText(date);
        holder.Time.setText(time);
        holder.Type.setText(currComp.getType());
        holder.Uid.setText(currComp.getUid());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked())
                {
                    NewComplaint temp = complaints.get(position);
                    if (temp != null)
                    {
                        completedComplaints.add(temp);

                    }
                }
                else
                {
                    NewComplaint temp = complaints.get(position);
                    if (temp != null)
                    {
                        completedComplaints.remove(temp);

                    }

                }
            }
        });


    }

    // Kitne items hai
    @Override
    public int getItemCount() {
        return complaints.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView Uid;
        public TextView Type;
        public TextView Date;
        public TextView Time;
        public Button confirmClearTask;
        CheckBox checkBox;

         public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            Uid = itemView.findViewById(R.id.uidComp);
            Type = itemView.findViewById(R.id.typeComp);
            Date = itemView.findViewById(R.id.dateComp);
            Time = itemView.findViewById(R.id.timeComp);
            checkBox = itemView.findViewById(R.id.isComp);
            confirmClearTask = itemView.findViewById(R.id.confirmCompletedTasks);


            Uid.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
//            Log.d("Yo boi: ","sdf dsf");
            int pos = this.getAdapterPosition();
            NewComplaint tmp = complaints.get(pos);
            Intent intent = new Intent(context, ComplaintDetails.class);
            intent.putExtra("Rtype", tmp.getType());
            intent.putExtra("Rdesc", tmp.getDescription());
            context.startActivity(intent);

        }

    }

    public ArrayList<NewComplaint> listOfCompleted()
    {
        return completedComplaints;
    }

}
