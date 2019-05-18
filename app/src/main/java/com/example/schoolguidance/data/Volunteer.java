package com.example.schoolguidance.data;

public class Volunteer extends Student {
    private int Vno;
    private boolean isWork;
    private long totalServiceTime;


    public int getVno() {
        return Vno;
    }

    public void setVno(int vno) {
        Vno = vno;
    }

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }

    public long getTotalServiceTime() {
        return totalServiceTime;
    }

    public void setTotalServiceTime(long totalServiceTime) {
        this.totalServiceTime = totalServiceTime;
    }
}
