package com.example.servo;

public class WorkerPendingInfo {
    private String idNo;
    private String type;
    private String date;
    private String time;
    private String roomNO;
    private String rollNO;
    private String contactNo;

    public WorkerPendingInfo(String idNo, String type, String date, String time, String roomNO, String rollNO, String contactNo) {
        this.idNo = idNo;
        this.type = type;
        this.date = date;
        this.time = time;
        this.roomNO = roomNO;
        this.rollNO = rollNO;
        this.contactNo = contactNo;
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

    public String getRoomNO() {
        return roomNO;
    }

    public String getRollNO() {
        return rollNO;
    }

    public String getContactNo() {
        return contactNo;
    }
}
