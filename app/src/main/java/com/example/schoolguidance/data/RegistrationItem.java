package com.example.schoolguidance.data;

public class RegistrationItem {
    private int registItemNo;
    private boolean isFinished;
    private int stuNo;
    private int registrationno;


    public int getRegistItemNo() {
        return registItemNo;
    }

    public void setRegistItemNo(int registItemNo) {
        this.registItemNo = registItemNo;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public int getRegistrationno() {
        return registrationno;
    }

    public void setRegistrationno(int registrationno) {
        this.registrationno = registrationno;
    }
}
