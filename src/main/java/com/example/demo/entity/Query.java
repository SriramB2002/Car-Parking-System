package com.example.demo.entity;

public class Query {
    private String checkIn;
    private String checkOut;
    private String date;
    private String space;
    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public String getDate() {
        return date;
    }

    public String getSpace() {
        return space;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSpace(String space) {
        this.space = space;
    }
}
