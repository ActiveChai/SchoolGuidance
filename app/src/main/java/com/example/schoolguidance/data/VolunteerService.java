package com.example.schoolguidance.data;

//import java.awt.*;

public class VolunteerService {
    private int activityNo;
    private int volunNo;
    private int FreshNo;
    private String serviceType;
    private String serviceContent;
    private String startTime;
    private String endTime;
    private String serviceStatus;

    public int getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(int activityNo) {
        this.activityNo = activityNo;
    }

    public int getVolunNo() {
        return volunNo;
    }

    public void setVolunNo(int volunNo) {
        this.volunNo = volunNo;
    }

    public int getFreshNo() {
        return FreshNo;
    }

    public void setFreshNo(int freshNo) {
        FreshNo = freshNo;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
