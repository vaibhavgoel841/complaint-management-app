package com.example.servo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servo.Api.LoginResponse;
import com.example.servo.Api.NewStudentUser;
import com.example.servo.Api.NewWorkerUser;
import com.example.servo.Api.RetrofitClient;
import com.example.servo.Models.NewUser;

import java.io.IOException;
import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText LogUser;
    EditText LogPass;

    Button loginButton;
    String token;
    TextView notuser;

    NewStudentUser currUser;
    NewUser loggedUser;
    String type;
    String tmp2;
    String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LogUser = findViewById(R.id.logUser);
        LogPass = findViewById(R.id.logPass);

        loginButton = findViewById(R.id.loginBtn);

        notuser = findViewById(R.id.notAuser);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.servo", Context.MODE_PRIVATE);

        notuser.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                notuser.setTextColor(R.color.colorPrimary);
                notuser.setTypeface(Typeface.DEFAULT_BOLD);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = LogUser.getText().toString();
                String pass = LogPass.getText().toString();

                if (user.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill username", Toast.LENGTH_SHORT).show();
                } else if (pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill password", Toast.LENGTH_SHORT).show();
                } else {

                    //chk
//                    token = null;
                    Call<LoginResponse> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .userLogin(user,pass);

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            LoginResponse loginResponse = response.body();

                            if(response.code() == 200)
                            {

                                token = loginResponse.getToken();
//                                Toast.makeText(LoginActivity.this, "User logged in successfully "+token, Toast.LENGTH_SHORT).show();
                                //chk2
                                // synchronise
//                    if(token != null) {
                                tmp2 = "Token " + token;

                                Call<NewStudentUser> call2 = RetrofitClient
                                        .getInstance()
                                        .getApi()
                                        .getUserDetails(tmp2, user);

                                call2.enqueue(new Callback<NewStudentUser>() {
                                    @Override
                                    public void onResponse(Call<NewStudentUser> call, Response<NewStudentUser> response) {
//                                        currUser = response.body();
////                                        loggedUser = new NewUser(currUser.getId(),currUser.getEmail(), currUser.getUsername(), pass,pass, currUser.getContact_number(), currUser.getRoll_number(), currUser.getRoom_number(), currUser.getUsertype(), currUser.getToken());
////                                    type = currUser.getUsertype();
//                            Toast.makeText(LoginActivity.this, currUser.getUsertype(), Toast.LENGTH_SHORT).show();

                                        if (response.code() == 200) {
                                            currUser = response.body();
                                            loggedUser = new NewUser(currUser.getId(), currUser.getEmail(), currUser.getUsername(), pass, pass, currUser.getContact_number(), currUser.getRoll_number(), currUser.getRoom_number(), currUser.getUsertype(), currUser.getToken());
                                            type = currUser.getUsertype();
                                            sharedPreferences.edit().putString("currID", String.valueOf(currUser.getId())).apply();
                                            sharedPreferences.edit().putString("currEmail", currUser.getEmail()).apply();
                                            sharedPreferences.edit().putString("currUser", currUser.getUsername()).apply();
                                            sharedPreferences.edit().putString("currContact",currUser.getContact_number()).apply();
                                            sharedPreferences.edit().putString("currRoll", currUser.getRoll_number()).apply();
                                            sharedPreferences.edit().putString("currRoom", currUser.getRoom_number()).apply();
                                            sharedPreferences.edit().putString("currType", currUser.getUsertype()).apply();
                                            sharedPreferences.edit().putString("currToken", currUser.getToken()).apply();
                                            phone = currUser.getContact_number();

//                                    Toast.makeText(LoginActivity.this, "Logged in Successfully " + type, Toast.LENGTH_SHORT).show();

                                        } else {
                                            String s = response.errorBody().toString();
                                            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();

                                        }

                                        if (type != null && type.equals("STUDENT"))
                                        {
                                            Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
                                            intent.putExtra("token",tmp2);
                                            intent.putExtra("phone",phone);
//                        Toast.makeText(LoginActivity.this, tmp2, Toast.LENGTH_LONG).show();

//                        token = null;
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                        else if(type != null && (type.equals("PLUMBER") || type.equals("CARPENTER") || type.equals("ELECTRICIAN") || type.equals("CLEANER") || type.equals("OTHERS") ))
                                        {
                                            Intent intent = new Intent(LoginActivity.this, WorkerActivity.class);
                                            intent.putExtra("token",tmp2);
//                        token = null;

                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                        }



                                    }

                                    @Override
                                    public void onFailure(Call<NewStudentUser> call, Throwable t) {

                                    }
                                });

                                //endchk2










                            }
                            else
                            {
                                String s = response.errorBody().toString();
                                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();

                            }


                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });

                    //endchk


                    // if is successful
//                    String tmp2 = "Token 701db904612321df94ef4554dadff06ae3ecc485";
//                    Toast.makeText(LoginActivity.this, tmp2, Toast.LENGTH_LONG).show();

//                    //chk2
//                    // synchronise
////                    if(token != null) {
//                        Call<NewStudentUser> call2 = RetrofitClient
//                                .getInstance()
//                                .getApi()
//                                .getUserDetails(tmp2, user);
//
//                        call2.enqueue(new Callback<NewStudentUser>() {
//                            @Override
//                            public void onResponse(Call<NewStudentUser> call, Response<NewStudentUser> response) {
////                                        currUser = response.body();
//////                                        loggedUser = new NewUser(currUser.getId(),currUser.getEmail(), currUser.getUsername(), pass,pass, currUser.getContact_number(), currUser.getRoll_number(), currUser.getRoom_number(), currUser.getUsertype(), currUser.getToken());
//////                                    type = currUser.getUsertype();
////                            Toast.makeText(LoginActivity.this, currUser.getUsertype(), Toast.LENGTH_SHORT).show();
//
//                                if (response.code() == 200) {
//                                    currUser = response.body();
//                                    loggedUser = new NewUser(currUser.getId(), currUser.getEmail(), currUser.getUsername(), pass, pass, currUser.getContact_number(), currUser.getRoll_number(), currUser.getRoom_number(), currUser.getUsertype(), currUser.getToken());
//                                    type = currUser.getUsertype();
////                                    Toast.makeText(LoginActivity.this, "Logged in Successfully " + type, Toast.LENGTH_SHORT).show();
//
//                                } else {
//                                    String s = response.errorBody().toString();
//                                    Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
//
//                                }
//
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<NewStudentUser> call, Throwable t) {
//
//                            }
//                        });
//
//                        //endchk2




                    }



                }
//            }
        });
    }
}