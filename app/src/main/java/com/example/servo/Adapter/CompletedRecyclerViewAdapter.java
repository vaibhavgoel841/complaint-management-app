package com.example.servo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servo.ComplaintDetails;
import com.example.servo.R;
import com.example.servo.Models.NewComplaint;

import java.util.ArrayList;

public class CompletedRecyclerViewAdapter extends RecyclerView.Adapter<CompletedRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NewComplaint> completedComplaints;

    public CompletedRecyclerViewAdapter(Context context, ArrayList<NewComplaint> completedComplaints) {
        this.context = context;
        this.completedComplaints = completedComplaints;
    }

    // kya dikhana hai
    @NonNull
    @Override
    public CompletedRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.completed_row, parent, false);
        return new ViewHolder(view);
    }

    // what to do with object
    @Override
    public void onBindViewHolder(@NonNull CompletedRecyclerViewAdapter.ViewHolder holder, int position) {
        NewComplaint tmp = completedComplaints.get(position);

        holder.CompletedUid.setText(tmp.getUid());
        holder.CompletedType.setText(tmp.getType());
        holder.CompletedTime.setText(String.valueOf(tmp.getCompletedTime()));
        holder.CompletedDate.setText(String.valueOf(tmp.getCompletedDate()));

        holder.ComplaintDate.setText(String.valueOf(tmp.getDate()));
        holder.ComplaintTime.setText(String.valueOf(tmp.getTime()));

    }

    // num of items
    @Override
    public int getItemCount() {
        return completedComplaints.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView CompletedUid;
        public TextView CompletedType;
        public TextView CompletedDate;
        public TextView CompletedTime;
        public TextView ComplaintTime;
        public TextView ComplaintDate;


        public ViewHolder(@NonNull View itemView) {            super(itemView);

            itemView.setOnClickListener(this);

            ComplaintTime = itemView.findViewById(R.id.complaintTime);
            ComplaintDate = itemView.findViewById(R.id.complaintDate);
            CompletedTime = itemView.findViewById(R.id.completedTime);
            CompletedDate = itemView.findViewById(R.id.compltedDate);
            CompletedType = itemView.findViewById(R.id.completedType);
            CompletedUid = itemView.findViewById(R.id.completedUid);

            CompletedUid.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            Log.d("df","dsfgfdg");
            int pos = this.getAdapterPosition();
            NewComplaint temp = completedComplaints.get(pos);
            Intent intent = new Intent(context, ComplaintDetails.class);
            intent.putExtra("Rtype", temp.getType());
            intent.putExtra("Rdesc", temp.getDescription());
            context.startActivity(intent);


        }
    }

}
