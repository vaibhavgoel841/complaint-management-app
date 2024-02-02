package com.example.servo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.servo.R;
import com.example.servo.StudentPendingInfo;


import java.util.ArrayList;

public class StudentPendingActivityAdapter extends RecyclerView.Adapter<StudentPendingActivityAdapter.StudentPendingActivityHolder> {
//    Adapter

    private Context context;
    private ArrayList<StudentPendingInfo> studentPendingInfos;
    private OnPatchListener onPatchListener;



    public StudentPendingActivityAdapter(Context context, ArrayList<StudentPendingInfo> studentPendingInfos) {
        this.context = context;
        this.studentPendingInfos = studentPendingInfos;

        try
        {
            this.onPatchListener = ((OnPatchListener) context);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @NonNull
    @Override
    public StudentPendingActivityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pending_student_card_layout, parent, false);
        return new StudentPendingActivityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentPendingActivityAdapter.StudentPendingActivityHolder holder, int position) {
        StudentPendingInfo studentPendingInfo = studentPendingInfos.get(position);
        holder.SetDetails(studentPendingInfo);
    }

    @Override
    public int getItemCount() {
        return studentPendingInfos.size();
    }


    //    Holder

    public class StudentPendingActivityHolder extends RecyclerView.ViewHolder {

        private TextView txtIdNo, txtType, txtDate, txtTime;
        private CheckBox checkBox;


//        currToken =
        public StudentPendingActivityHolder(@NonNull View itemView) {
            super(itemView);
            txtIdNo = itemView.findViewById(R.id.txtIdNo);
            txtType = itemView.findViewById(R.id.txtType);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTime = itemView.findViewById(R.id.txtTime);
            checkBox = itemView.findViewById(R.id.checkBox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkBox.setBackgroundResource(R.drawable.ic_baseline_check_circle_outline_24);
                    int adapterPos = getAdapterPosition();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    // Setting Alert Dialog Title
                    alertDialogBuilder.setTitle("Confirm..!!!");
                    // Icon Of Alert Dialog
                    alertDialogBuilder.setIcon(R.drawable.ic_deplete_complaint_alert);
                    // Setting Alert Dialog Message
                    alertDialogBuilder.setMessage("Are you sure that you want to mark this complaint as done?");
                    alertDialogBuilder.setCancelable(false);

                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            Intent intent = new Intent();
                            intent.putExtra("compId", studentPendingInfos.get(adapterPos).getIdNo());

                            onPatchListener.onPatchListener(intent);




//                finish();
                        }
                    });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(.this,"You clicked over No",Toast.LENGTH_SHORT).show();
                            checkBox.setBackgroundResource(R.drawable.ic_baseline_radio_button_unchecked_24);

                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            });

        }

        @SuppressLint("SetTextI18n")
        void SetDetails(StudentPendingInfo studentPendingInfo) {
            txtIdNo.setText("No : " + studentPendingInfo.getIdNo());
            txtType.setText("Type : " + studentPendingInfo.getType());
            txtDate.setText("Date : " + studentPendingInfo.getDate());
            txtTime.setText("Time : " + studentPendingInfo.getTime());
        }
    }

    public interface OnPatchListener{
        public void onPatchListener(Intent intent);
    }

    




}
