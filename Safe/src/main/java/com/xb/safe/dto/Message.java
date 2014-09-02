package com.xb.safe.dto;


public class Message {
    private String status;

    public Message() {

    }

    public Message(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setOk() {
        this.status = "ok";
    }

    public void setError() {
        this.status = "error";
    }

    @Override
    public String toString() {
        return "Message{" +
                "status='" + status + '\'' +
                '}';
    }
}
