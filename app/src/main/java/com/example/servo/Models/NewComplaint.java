package com.example.servo.Models;

public class NewComplaint {


    private String time;
    private String date;
    private String type;
    private int uid;
    private int studentID;
    private int workerID; // null
    private String roomno;
    private String rollno;
    private String Description;
    private String phoneStudent;
    private String phoneWorker;
    private long completedTime;
    private long completedDate;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getWorkerID() {
        return workerID;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPhoneStudent() {
        return phoneStudent;
    }

    public void setPhoneStudent(String phoneStudent) {
        this.phoneStudent = phoneStudent;
    }

    public String getPhoneWorker() {
        return phoneWorker;
    }

    public long getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(long completedTime) {
        this.completedTime = completedTime;
    }

    public long getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(long completedDate) {
        this.completedDate = completedDate;
    }

    public void setPhoneWorker(String phoneWorker) {
        this.phoneWorker = phoneWorker;
    }

//    public newComplaint(long time, long date, String type, String uid, String studentID, String workerID, long roomno, long rollno, String description, long phoneStudent, long phoneWorker) {
//        this.time = time;
//        this.date = date;
//        this.type = type;
//        this.uid = uid;
//        this.studentID = studentID;
//        this.workerID = workerID;
//        this.roomno = roomno;
//        this.rollno = rollno;
//        Description = description;
//        this.phoneStudent = phoneStudent;
//        this.phoneWorker = phoneWorker;
//    }

    public NewComplaint(String time, String date, String type, int uid, int studentID, int workerID, String roomno, String rollno, String description, String phoneStudent, String phoneWorker, long completedTime, long completedDate) {
        this.time = time;
        this.date = date;
        this.type = type;
        this.uid = uid;
        this.studentID = studentID;
        this.workerID = workerID;
        this.roomno = roomno;
        this.rollno = rollno;
        Description = description;
        this.phoneStudent = phoneStudent;
        this.phoneWorker = phoneWorker;
        this.completedTime = completedTime;
        this.completedDate = completedDate;
    }
}
