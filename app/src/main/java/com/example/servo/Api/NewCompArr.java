package com.example.servo.Api;

import java.util.ArrayList;

public class NewCompArr {

    ArrayList <NewComplaint> complaints;

    public NewCompArr(ArrayList<NewComplaint> complaints) {
        this.complaints = complaints;
    }

    public ArrayList<NewComplaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(ArrayList<NewComplaint> complaints) {
        this.complaints = complaints;
    }
}
