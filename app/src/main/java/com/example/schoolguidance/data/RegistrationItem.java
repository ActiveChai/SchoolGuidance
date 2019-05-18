package com.example.schoolguidance.data;

public class RegistrationItem {
    private int registItemNo;
    private String registItemContent;
    private String registItemTime;
    private String registItemPlace;
    private String registItemps;
    private boolean isFinished;
    private int stuNo;


    public int getRegistItemNo() {
        return registItemNo;
    }

    public void setRegistItemNo(int registItemNo) {
        this.registItemNo = registItemNo;
    }

    public String getRegistItemContent() {
        return registItemContent;
    }

    public void setRegistItemContent(String registItemContent) {
        this.registItemContent = registItemContent;
    }

    public String getRegistItemTime() {
        return registItemTime;
    }

    public void setRegistItemTime(String registItemTime) {
        this.registItemTime = registItemTime;
    }


    public String getRegistItemPlace() {
        return registItemPlace;
    }

    public void setRegistItemPlace(String registItemPlace) {
        this.registItemPlace = registItemPlace;
    }

    public String getRegistItemps() {
        return registItemps;
    }

    public void setRegistItemps(String registItemps) {
        this.registItemps = registItemps;
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
}