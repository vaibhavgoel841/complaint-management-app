package com.example.servo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.servo.Adapter.CompletedActivityAdapter;
import com.example.servo.Adapter.StudentPendingActivityAdapter;
import com.example.servo.Api.NewComplaint;
import com.example.servo.Api.NewWorkerUser;
import com.example.servo.Api.RetrofitClient;
import com.example.servo.Models.NewUser;
import com.example.servo.databinding.PendingStudentCardLayoutBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class StudentActivity extends AppCompatActivity implements StudentPendingActivityAdapter.OnPatchListener {

    private BottomNavigationView bottomNavigationView;
    static RecyclerView studentPendingActivityRecyclerView;
    RecyclerView completedActivityRecyclerView;
    private CompletedActivityAdapter completedActivityAdapter;
    private ArrayList<CompletedActivityInfo> completedActivityInfos;
    static StudentPendingActivityAdapter studentPendingActivityAdapter;
    static ArrayList<StudentPendingInfo> studentPendingInfos;
    FloatingActionButton floatingActionButton;
    Button Pending;
    Button Completed;
    String Phone;
    public static String Token;
    ImageView logout;

    static ArrayList<NewComplaint> newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Token = getIntent().getStringExtra("token");

        logout = findViewById(R.id.menuButton);
        floatingActionButton = findViewById(R.id.fab);
        Pending = findViewById(R.id.pendingBtn);
        Completed = findViewById(R.id.completedBtn);
        studentPendingActivityRecyclerView = findViewById(R.id.studentPendingRecyclerView);
        studentPendingActivityRecyclerView.setNestedScrollingEnabled(true);
        completedActivityRecyclerView = findViewById(R.id.completedRecyclerView);
        completedActivityRecyclerView.setNestedScrollingEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        studentPendingActivityRecyclerView.setLayoutManager(linearLayoutManager);
        studentPendingInfos = new ArrayList<StudentPendingInfo>();
        studentPendingActivityAdapter = new StudentPendingActivityAdapter(StudentActivity.this, studentPendingInfos);
        studentPendingActivityRecyclerView.setAdapter(studentPendingActivityAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        completedActivityRecyclerView.setLayoutManager(linearLayoutManager1);
        completedActivityInfos = new ArrayList<CompletedActivityInfo>();
        completedActivityAdapter = new CompletedActivityAdapter(this, completedActivityInfos);
        completedActivityRecyclerView.setAdapter(completedActivityAdapter);

        Phone = getIntent().getStringExtra("phone");

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.servo", Context.MODE_PRIVATE);
        completedActivityAdapter.notifyDataSetChanged();



        createCompletedListData();
        createStudentPendingListData();
        Pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pending.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.colorPrimary)));
                Completed.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.white)));
                Pending.setTextColor(getColor(R.color.white));
                Completed.setTextColor(getColor(R.color.colorPrimary));
                studentPendingActivityRecyclerView.setVisibility(View.VISIBLE);
                completedActivityRecyclerView.setVisibility(View.INVISIBLE);
            }
        });

        Completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Completed.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.colorPrimary)));
                Pending.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.white)));
                Completed.setTextColor(getColor(R.color.white));
                Pending.setTextColor(getColor(R.color.colorPrimary));
                studentPendingActivityRecyclerView.setVisibility(View.INVISIBLE);
                completedActivityRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StudentActivity.this);
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
                                    startActivity(new Intent(StudentActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));

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


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(StudentActivity.this, ComplaintActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                Intent intent = new Intent(StudentActivity.this, ComplaintActivity.class);
                intent.putExtra("token",Token);
                intent.putExtra("phone", Phone);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//                studentPendingInfos.clear();
//                createStudentPendingListData();
////        studentPendingActivityAdapter.notifyDataSetChanged();
//                studentPendingActivityRecyclerView.setAdapter(studentPendingActivityAdapter);




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
                    Toast.makeText(StudentActivity.this, s, Toast.LENGTH_LONG).show();

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

    public static void createStudentPendingListData() {

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
                            StudentPendingInfo studentPendingInfo = new StudentPendingInfo(Integer.toString(id), type, date, time);
                            studentPendingInfos.add(studentPendingInfo);



                        }
                    }


                    studentPendingActivityAdapter.notifyDataSetChanged();

                }
                else
                {
                    String s = response.errorBody().toString();
//                    Toast.makeText(StudentActivity.this, s, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<NewComplaint>> call, Throwable t) {

            }
        });




//        StudentPendingInfo studentPendingInfo1 = new StudentPendingInfo("1234", "ELECTRICIAN", "1/4/2022", "5:10 PM");
//        studentPendingInfos.add(studentPendingInfo);
//        studentPendingInfos.add(studentPendingInfo1);
    }


    @Override
    public void onPatchListener(Intent intent) {

        String ID = String.valueOf(intent.getStringExtra("compId") );
        Log.e("Data =================>",intent.getStringExtra("compId"));
        int copId = Integer.parseInt(ID);
        Log.e("Data =================>", String.valueOf(copId));
        //chk

        Call<NewComplaint> call = RetrofitClient
                .getInstance()
                .getApi()
                .markDone(Token, copId, true);

        call.enqueue(new Callback<NewComplaint>() {
            @Override
            public void onResponse(Call<NewComplaint> call, Response<NewComplaint> response) {
                if (response.code() == 200)
                {
                    Toast.makeText(StudentActivity.this, "Complaint completed", Toast.LENGTH_SHORT).show();

                    studentPendingInfos.clear();
                    createStudentPendingListData();
//        studentPendingActivityAdapter.notifyDataSetChanged();
                    studentPendingActivityRecyclerView.setAdapter(studentPendingActivityAdapter);

                    completedActivityInfos.clear();
                    createCompletedListData();
//                    completedActivityAdapter.notifyDataSetChanged();
                    completedActivityRecyclerView.setAdapter(completedActivityAdapter);

                }
                else
                {
                    String s = response.errorBody().toString();
                    Toast.makeText(StudentActivity.this, s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NewComplaint> call, Throwable t) {

            }
        });

        //endchk

//        studentPendingInfos.clear();
//        createStudentPendingListData();
////        studentPendingActivityAdapter.notifyDataSetChanged();
//        studentPendingActivityRecyclerView.setAdapter(studentPendingActivityAdapter);
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//                studentPendingActivityAdapter.notifyDataSetChanged();
//
//
//    }
}