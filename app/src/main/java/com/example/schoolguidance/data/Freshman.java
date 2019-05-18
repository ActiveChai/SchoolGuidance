package com.example.schoolguidance.data;

import java.util.List;

public class Freshman extends Student {
    private List<RegistrationItem> registration;
    private boolean isRequest;

    public List<RegistrationItem> getRegistration() {
        return registration;
    }

    public void setRegistration(List<RegistrationItem> registration) {
        this.registration = registration;
    }

    public boolean isRequest() {
        return isRequest;
    }

    public void setRequest(boolean request) {
        isRequest = request;
    }



}
