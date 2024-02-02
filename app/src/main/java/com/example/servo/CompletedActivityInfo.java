package com.example.servo;

public class CompletedActivityInfo {

    private String idNo;
    private String type;
    private String sDate;
    private String sTime;
    private String eDate;
    private String eTime;

    public CompletedActivityInfo(String idNo, String type, String sDate, String sTime, String eDate, String eTime) {
        this.idNo = idNo;
        this.type = type;
        this.sDate = sDate;
        this.sTime = sTime;
        this.eDate = eDate;
        this.eTime = eTime;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getType() {
        return type;
    }

    public String getsDate() {
        return sDate;
    }

    public String getsTime() {
        return sTime;
    }

    public String geteDate() {
        return eDate;
    }

    public String geteTime() {
        return eTime;
    }
}
