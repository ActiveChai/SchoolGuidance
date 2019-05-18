package com.example.schoolguidance.data;

public class Feedback {
    private int feedno;
    private int Sno;
    private String feedcontent;
    private String feedreleasetime;

    public int getFeedno() {
        return feedno;
    }

    public void setFeedno(int feedno) {
        this.feedno = feedno;
    }

    public int getSno() {
        return Sno;
    }

    public void setSno(int sno) {
        Sno = sno;
    }

    public String getFeedcontent() {
        return feedcontent;
    }

    public void setFeedcontent(String feedcontent) {
        this.feedcontent = feedcontent;
    }

    public String getFeedreleasetime() {
        return feedreleasetime;
    }

    public void setFeedreleasetime(String feedreleasetime) {
        this.feedreleasetime = feedreleasetime;
    }
}
