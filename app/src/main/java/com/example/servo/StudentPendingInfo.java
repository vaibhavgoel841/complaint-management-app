package com.example.servo;

public class StudentPendingInfo {

    private String idNo;
    private String type;
    private String date;
    private String time;

    public StudentPendingInfo(String idNo, String type, String date, String time) {
        this.idNo = idNo;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }


}
