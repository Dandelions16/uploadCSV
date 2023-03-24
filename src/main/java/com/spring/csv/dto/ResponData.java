package com.spring.csv.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponData {

    private boolean status;
    private final List<String> message = new ArrayList<>();
    private Object payload;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMessage() {
        return message;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
