package com.example.schoolguidance.data;

public class RegistrationItem {
    private int registItemNo;
    private String registItemContent;
    private String registItemStartTime;
    private String registItemEndTime;
    private String registItemPlace;
    private String registItemMaterials;
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

    public String getRegistItemStartTime() {
        return registItemStartTime;
    }

    public void setRegistItemStartTime(String registItemStartTime) {
        this.registItemStartTime = registItemStartTime;
    }

    public String getRegistItemEndTime() {
        return registItemEndTime;
    }

    public void setRegistItemEndTime(String registItemEndTime) {
        this.registItemEndTime = registItemEndTime;
    }

    public String getRegistItemPlace() {
        return registItemPlace;
    }

    public void setRegistItemPlace(String registItemPlace) {
        this.registItemPlace = registItemPlace;
    }

    public String getRegistItemMaterials() {
        return registItemMaterials;
    }

    public void setRegistItemMaterials(String registItemMaterials) {
        this.registItemMaterials = registItemMaterials;
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
