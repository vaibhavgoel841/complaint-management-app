package com.example.servo.Api;

public class NewComplaint {
    private int id;
    private String type;
    private String description;
    private String date_lodged;
    private Boolean mark_done;
    private String roll_number;
    private String room_number;
    private int student;
    private String date_done;



    private String contact_number;



    public NewComplaint(int id, String type, String description, String date_lodged, Boolean mark_done, String roll_number, String room_number, int student, String date_done, String contact_number) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date_lodged = date_lodged;
        this.mark_done = mark_done;
        this.roll_number = roll_number;
        this.room_number = room_number;
        this.student = student;
        this.date_done = date_done;
        this.contact_number = contact_number;
    }

    public String getDate_done() {
        return date_done;
    }

    public void setDate_done(String date_done) {
        this.date_done = date_done;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_lodged() {
        return date_lodged;
    }

    public void setDate_lodged(String date_lodged) {
        this.date_lodged = date_lodged;
    }

    public Boolean getMark_done() {
        return mark_done;
    }

    public void setMark_done(Boolean mark_done) {
        this.mark_done = mark_done;
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

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

}
