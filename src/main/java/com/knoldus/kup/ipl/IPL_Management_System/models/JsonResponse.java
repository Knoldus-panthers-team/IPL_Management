package com.knoldus.kup.ipl.IPL_Management_System.models;

public class JsonResponse {
    private String status = null;
    private Object result = null;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }

}