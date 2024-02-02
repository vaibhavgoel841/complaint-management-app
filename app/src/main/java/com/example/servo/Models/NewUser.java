package com.example.servo.Models;

public class NewUser {
    private int id;
    private String email;
    private String username;
    private String password;
    private String password2;
    private String contact_number;
    private String roll_number;
    private String room_number;
    private String usertype;
    private String token;


    public NewUser(int id, String email, String username, String password, String password2, String contact_number, String roll_number, String room_number, String usertype, String token) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.contact_number = contact_number;
        this.roll_number = roll_number;
        this.room_number = room_number;
        this.usertype = usertype;
        this.token = token;
    }


//    public NewUser(int id, String email, String username, String password, String password2, String contact_number, String rollno, String room_number, String usertype) {
//        this.id = id;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.password2 = password2;
//        this.contact_number = contact_number;
//        this.rollno = rollno;
//        this.room_number = room_number;
//        this.usertype = usertype;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
