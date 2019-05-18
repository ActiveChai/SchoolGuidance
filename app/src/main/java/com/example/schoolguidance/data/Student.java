package com.example.schoolguidance.data;

public abstract class Student {
    private int Sno;
    private String Sname;
    private String Ssex;
    private String Spassword;
    private String Sdept;
    private String Smajor;
    private String Sclass;
    private String Stelephone;
    private String Scampus;

    void setInfo(String stuName,String stuSex,String stuPassword,String stuDept,String stuMajor,String stuClass,String stuTelephone,String stuCampus){
        this.Sname = stuName;
        this.Ssex = stuSex;
        this.Spassword = stuPassword;
        this.Sdept = stuDept;
        this.Smajor = stuMajor;
        this.Sclass = stuClass;
        this.Stelephone = stuTelephone;
        this.Scampus = stuCampus;
    }
    void resetPssword(String stuPassword){
        this.Spassword = stuPassword;
    }
    boolean login(String stuName, String stuPassword){
        if(this.Sname == stuName && this.Spassword == stuPassword)return true;
        else return false;
    }
    void logOut(){
        //...
    }
}
