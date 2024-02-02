package com.example.servo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.servo.Adapter.CompletedActivityAdapter;
import com.example.servo.Adapter.StudentPendingActivityAdapter;
import com.example.servo.Adapter.WorkerPendingActivityAdapter;
import com.example.servo.Api.NewComplaint;
import com.example.servo.Api.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerActivity extends AppCompatActivity {

    RecyclerView workerPendingActivityRecyclerView;
    RecyclerView completedActivityRecyclerView;
    private CompletedActivityAdapter completedActivityAdapter;
    private ArrayList<CompletedActivityInfo> completedActivityInfos;
    private WorkerPendingActivityAdapter workerPendingActivityAdapter;
    private ArrayList<WorkerPendingInfo> workerPendingInfos;
    Button pendingWorker;
    Button completedWorker;
    String Token;
    ArrayList<NewComplaint> newUser;
    SharedPreferences sharedPreferences;
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        logout = findViewById(R.id.menuButton);
        pendingWorker = findViewById(R.id.pendingBtnWorker);
        completedWorker = findViewById(R.id.completedBtnWorker);
        workerPendingActivityRecyclerView = findViewById(R.id.workerPendingRecyclerView);
        completedActivityRecyclerView = findViewById(R.id.workerCompletedRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        workerPendingActivityRecyclerView.setLayoutManager(linearLayoutManager);
        workerPendingInfos = new ArrayList<WorkerPendingInfo>();
        workerPendingActivityAdapter = new WorkerPendingActivityAdapter(this, workerPendingInfos);
        workerPendingActivityRecyclerView.setAdapter(workerPendingActivityAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        completedActivityRecyclerView.setLayoutManager(linearLayoutManager1);
        completedActivityInfos = new ArrayList<CompletedActivityInfo>();
        completedActivityAdapter = new CompletedActivityAdapter(this, completedActivityInfos);
        completedActivityRecyclerView.setAdapter(completedActivityAdapter);

        Token = getIntent().getStringExtra("token");
       sharedPreferences= this.getSharedPreferences("com.example.servo", Context.MODE_PRIVATE);



        createCompletedListData();
        createStudentPendingListData();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(WorkerActivity.this);
                // Setting Alert Dialog Title
                alertDialogBuilder.setTitle("Confirm..!!!");
                // Icon Of Alert Dialog
                alertDialogBuilder.setIcon(R.drawable.ic_deplete_complaint_alert);
                // Setting Alert Dialog Message
                alertDialogBuilder.setMessage("Are you sure that you want to logout?");
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //                Log.e("chk","CHEK");
                        Call<Void> logoutCall = RetrofitClient
                            .getInstance()
                            .getApi().logOut(Token);

                        logoutCall.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.code() == 200) {
//                            Toast.makeText(StudentActivity.this, "log outed", Toast.LENGTH_SHORT).show();
                                    Log.e("logout==========>", "success");
                                    startActivity(new Intent(WorkerActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));

                                } else {
                                    String s = response.errorBody().toString();
                                    Log.e("logout==========>", response.errorBody().toString());

//                            Toast.makeText(StudentActivity.this, s, Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Log.e("logout==========>", t.toString());


                            }
                        });

//                startActivity();
                    }
                });

//                finish();}


                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(.this,"You clicked over No",Toast.LENGTH_SHORT).show();
//                        checkBox.setBackgroundResource(R.drawable.ic_baseline_radio_button_unchecked_24);

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Log.e("chk","CHEK");
//                Call<Void> logoutCall = RetrofitClient
//                        .getInstance()
//                        .getApi().logOut(Token);
//
//                logoutCall.enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        if (response.code() == 200)
//                        {
////                            Toast.makeText(StudentActivity.this, "log outed", Toast.LENGTH_SHORT).show();
//                            Log.e("logout==========>", "success");
//                            startActivity(new Intent(WorkerActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
//
//                        }
//                        else {
//                            String s = response.errorBody().toString();
//                            Log.e("logout==========>", response.errorBody().toString());
//
////                            Toast.makeText(StudentActivity.this, s, Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        Log.e("logout==========>", t.toString());
//
//
//                    }
//                });
//
////                startActivity();
//            }
//        });


        pendingWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pendingWorker.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.colorPrimary)));
                completedWorker.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.white)));
                pendingWorker.setTextColor(getColor(R.color.white));
                completedWorker.setTextColor(getColor(R.color.colorPrimary));
                workerPendingActivityRecyclerView.setVisibility(View.VISIBLE);
                completedActivityRecyclerView.setVisibility(View.INVISIBLE);
            }
        });

        completedWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completedWorker.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.colorPrimary)));
                pendingWorker.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.white)));
                completedWorker.setTextColor(getColor(R.color.white));
                pendingWorker.setTextColor(getColor(R.color.colorPrimary));
                workerPendingActivityRecyclerView.setVisibility(View.INVISIBLE);
                completedActivityRecyclerView.setVisibility(View.VISIBLE);
            }
        });



    }



    private void createCompletedListData() {

        Call<ArrayList<NewComplaint>> callDone = RetrofitClient
                .getInstance()
                .getApi()
                .getDone(Token);

        callDone.enqueue(new Callback<ArrayList<NewComplaint>>() {
            @Override
            public void onResponse(Call<ArrayList<NewComplaint>> call, Response<ArrayList<NewComplaint>> response) {
                if(response.code() == 200)
                {

                    newUser = response.body();
//                    if (newUser != null)
//                    {
//                        Log.i("size: ", Integer.toString(newUser.size()));
//
//                    }
//                    Toast.makeText(StudentActivity.this, newUser.size(), Toast.LENGTH_SHORT).show();

//                    int id = newUser.getId();
//                    token = newUser.getToken();

                    if (newUser != null)
                    {
                        for (int i=0; i< newUser.size(); i++)
                        {
                            int id = newUser.get(i).getId();
                            String type = newUser.get(i).getType();
                            String DateLog = newUser.get(i).getDate_lodged();
                            String compDateLog = newUser.get(i).getDate_done();
                            String date = DateLog.substring(0,10);
                            String time = DateLog.substring(11,16);
                            String codate = compDateLog.substring(0,10);
                            String cotime = compDateLog.substring(11,16);
                            CompletedActivityInfo completedActivityInfo = new CompletedActivityInfo(Integer.toString(id), type, date, time, codate, cotime);
                            completedActivityInfos.add(completedActivityInfo);



                        }
                    }


                    completedActivityAdapter.notifyDataSetChanged();


                }
                else
                {
                    String s = response.errorBody().toString();
                    Toast.makeText(WorkerActivity.this, s, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<NewComplaint>> call, Throwable t) {

            }
        });

        if (newUser != null)
        {

        }

//        CompletedActivityInfo completedActivityInfo = new CompletedActivityInfo("4827890", "casfdwadf", "1/4/2022", "5:10 PM", "1/4/2022", "5:10 PM");
//        CompletedActivityInfo completedActivityInfo1 = new CompletedActivityInfo("4827890", "casfdwadf", "1/4/2022", "5:10 PM", "1/4/2022", "5:10 PM");
//        completedActivityInfos.add(completedActivityInfo);
//        completedActivityInfos.add(completedActivityInfo1);
    }

    private void createStudentPendingListData() {

        Call<ArrayList<NewComplaint>> callPending = RetrofitClient
                .getInstance()
                .getApi()
                .getPending(Token);

        callPending.enqueue(new Callback<ArrayList<NewComplaint>>() {
            @Override
            public void onResponse(Call<ArrayList<NewComplaint>> call, Response<ArrayList<NewComplaint>> response) {
                if(response.code() == 200)
                {

                    newUser = response.body();
//                    if(newUser != null){
//                        Log.i("size: ", Integer.toString(newUser.size()));
//
//                    }
//                    Toast.makeText(StudentActivity.this, newUser.size(), Toast.LENGTH_SHORT).show();

//                    int id = newUser.getId(); asdf@1234
//                    token = newUser.getToken(); student1

                    if (newUser != null)
                    {
                        for (int i=0; i< newUser.size(); i++)
                        {
                            int id = newUser.get(i).getId();
                            String type = newUser.get(i).getType();
                            String DateLog = newUser.get(i).getDate_lodged();
                            String date = DateLog.substring(0,10);
                            String time = DateLog.substring(11,16);


//                                    places = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("places", ObjectSerializer.serialize(new ArrayList<String>())));

                            WorkerPendingInfo workerPendingInfo = new WorkerPendingInfo(Integer.toString(id),type,date,time,newUser.get(i).getRoom_number(),newUser.get(i).getRoll_number(), newUser.get(i).getContact_number());
                            workerPendingInfos.add(workerPendingInfo);



                        }
                    }


                    workerPendingActivityAdapter.notifyDataSetChanged();

                }
                else
                {
                    String s = response.errorBody().toString();
                    Toast.makeText(WorkerActivity.this, s, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<NewComplaint>> call, Throwable t) {

            }
        });
    }
}