package com.example.servo;

import static com.example.servo.StudentActivity.studentPendingActivityAdapter;
import static com.example.servo.StudentActivity.studentPendingActivityRecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.servo.Api.NewComplaint;
import com.example.servo.Api.RetrofitClient;
import com.example.servo.Models.NewUser;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner Reqperson;
    EditText description;
    EditText rollNo;
    EditText roomNo;
    Button addComplaint;
    String room;
    String roll;
    String reqProff;
    NewComplaint comp;
    String token;
    String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        Reqperson = findViewById(R.id.reqPerson);
        description = findViewById(R.id.complaintDesc);
        addComplaint = findViewById(R.id.newComplaintBtn);
        rollNo = findViewById(R.id.rollEdit);
        roomNo = findViewById(R.id.roomEdit);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.servo", Context.MODE_PRIVATE);

        room = sharedPreferences.getString("currRoom","0000" );
        roll = sharedPreferences.getString("currRoll", "iec2020000");
        token = getIntent().getStringExtra("token");
        phone = getIntent().getStringExtra("phone");

        rollNo.setText(roll);
        roomNo.setText(room);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Profession, R.layout.custom_spinner);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);



        Reqperson.setAdapter(adapter);
        Reqperson.setOnItemSelectedListener(this);
        addComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String desc = description.getText().toString();
                roll = rollNo.getText().toString();
                room = roomNo.getText().toString();



                if (desc.isEmpty())
                {
                    Toast.makeText(ComplaintActivity.this, "Please write a small description", Toast.LENGTH_SHORT).show();
                }
                else if(reqProff.isEmpty())
                {
                    Toast.makeText(ComplaintActivity.this, "Please select a required professional", Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    Toast.makeText(ComplaintActivity.this, token+" "+roll+" "+room+" "+desc+" "+reqProff, Toast.LENGTH_SHORT).show();
                    String prof = reqProff.toUpperCase(Locale.ROOT);


                    Call<NewComplaint> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .newComp(token,prof,desc,roll,room,phone);

                    call.enqueue(new Callback<NewComplaint>() {
                        @Override
                        public void onResponse(Call<NewComplaint> call, Response<NewComplaint> response) {
                            if(response.code() == 201)
                            {

                                comp = response.body();
//                                Toast.makeText(ComplaintActivity.this, comp.getDescription(), Toast.LENGTH_SHORT).show();
                                StudentActivity.studentPendingInfos.clear();
                                StudentActivity.createStudentPendingListData();
//        studentPendingActivityAdapter.notifyDataSetChanged();
                                studentPendingActivityRecyclerView.setAdapter(studentPendingActivityAdapter);



//                                completedActivityAdapter.notifyDataSetChanged();


                            }
                            else
                            {
                                String s = response.errorBody().toString();
//                                Toast.makeText(ComplaintActivity.this, s, Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<NewComplaint> call, Throwable t) {

                        }
                    });

                    if (comp != null)
                    {
                        Toast.makeText(ComplaintActivity.this, "Complaint Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                    // register Complaint
                    finish();
                }

            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        reqProff = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}