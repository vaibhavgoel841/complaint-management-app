package com.example.servo.Api;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("student/signup")
    Call<NewStudentUser> createStudent(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password2") String password2,
            @Field("contact_number") String contact_number,
            @Field("roll_number") String roll_number,
            @Field("room_number") String room_number
    );

    @FormUrlEncoded
    @POST("worker/signup")
    Call<NewWorkerUser> createWorker(

            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password2") String password2,
            @Field("contact_number") String contact_number,
            @Field("usertype") String usertype

    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("complaint/new")
    Call<NewComplaint> newComp(
            @Header("Authorization") String token,
            @Field("type") String type,
            @Field("description") String description,
            @Field("roll_number") String roll_number,
            @Field("room_number") String room_number,
            @Field("contact_number") String contact_number

    );

    @FormUrlEncoded
    @PATCH("complaint/{id}")
    Call<NewComplaint> markDone(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Field("mark_done") Boolean mark_done
    );

    @GET("logout")
    Call<Void> logOut(
            @Header("Authorization") String token
    );

    @GET("authdetail/{username}")
    Call<NewStudentUser> getUserDetails(
            @Header("Authorization") String token,
            @Path("username") String username
    );

    @GET("complaint/pending")
    Call<ArrayList<NewComplaint>> getPending(
            @Header("Authorization") String token
    );

    @GET("complaint/done")
    Call<ArrayList<NewComplaint>> getDone(
            @Header("Authorization") String token
    );







}
