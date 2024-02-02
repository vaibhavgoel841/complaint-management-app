package com.example.servo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servo.CompletedActivityInfo;
import com.example.servo.R;
import com.example.servo.StudentPendingInfo;

import java.util.ArrayList;

public class CompletedActivityAdapter extends RecyclerView.Adapter<CompletedActivityAdapter.CompletedActivityHolder> {

    private Context context;
    private ArrayList<CompletedActivityInfo> completedActivityInfos;

    public CompletedActivityAdapter(Context context, ArrayList<CompletedActivityInfo> completedActivityInfos) {
        this.context = context;
        this.completedActivityInfos = completedActivityInfos;
    }

    @NonNull
    @Override
    public CompletedActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.completed_student_worker_card_layout, parent, false);
        return new CompletedActivityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedActivityHolder holder, int position) {
        CompletedActivityInfo completedActivityInfo = completedActivityInfos.get(position);
        holder.SetDetails(completedActivityInfo);
    }

    @Override
    public int getItemCount() {
        return completedActivityInfos.size();
    }

    public class CompletedActivityHolder extends RecyclerView.ViewHolder {

        private TextView txtIdNo, txtType, txtSDate, txtSTime, txtEDate, txtETime;
        public CompletedActivityHolder(@NonNull View itemView) {
            super(itemView);
            txtIdNo = itemView.findViewById(R.id.cswidNo);
            txtType = itemView.findViewById(R.id.cswType);
            txtSDate = itemView.findViewById(R.id.cswSDate);
            txtSTime = itemView.findViewById(R.id.cswSTime);
            txtEDate = itemView.findViewById(R.id.cswEDate);
            txtETime = itemView.findViewById(R.id.cswETime);
        }

        @SuppressLint("SetTextI18n")
        void SetDetails(CompletedActivityInfo completedActivityInfo) {
            txtIdNo.setText("No : " + completedActivityInfo.getIdNo());
            txtType.setText("Type : " + completedActivityInfo.getType());
            txtSDate.setText("Date : " + completedActivityInfo.getsDate());
            txtSTime.setText("Time : " + completedActivityInfo.getsTime());
            txtEDate.setText("Date : " + completedActivityInfo.geteDate());
            txtETime.setText("Time : " + completedActivityInfo.geteTime());
        }

    }

}
