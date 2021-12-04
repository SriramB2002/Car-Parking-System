package com.example.demo.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Slot {
    
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String location;

    public Slot(String s) {
        id = count.incrementAndGet();
        location = s;
    }

    public Slot() {
        id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
