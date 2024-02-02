package com.example.servo.Api;

public class NewStudentUser {

    private int id;
    private String username;
    private String email;
    private String contact_number;
    private String roll_number;
    private String room_number;
    private String usertype;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public NewStudentUser(int id, String username, String email, String contact_number, String roll_number, String room_number, String usertype, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.contact_number = contact_number;
        this.roll_number = roll_number;
        this.room_number = room_number;
        this.usertype = usertype;
        this.token = token;
    }
}
