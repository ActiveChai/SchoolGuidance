package com.example.schoolguidance.data;

public class Administrator {
    private int adminNo;
    private String adminName;
    private String adminTelephone;
    private String adminPassword;
    private String adminSex;
    private String adminOffice;
    private String adminGrade;

    public void setPassword(String password){
        adminPassword = password;
    }

    public boolean login(String name, String password){
        if(adminName == name && adminPassword == password)return true;
        else return false;
    }


    public int getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(int adminNo) {
        this.adminNo = adminNo;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminTelephone() {
        return adminTelephone;
    }

    public void setAdminTelephone(String adminTelephone) {
        this.adminTelephone = adminTelephone;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    public String getAdminOffice() {
        return adminOffice;
    }

    public void setAdminOffice(String adminOffice) {
        this.adminOffice = adminOffice;
    }

    public String getAdminGrade() {
        return adminGrade;
    }

    public void setAdminGrade(String adminGrade) {
        this.adminGrade = adminGrade;
    }
}
