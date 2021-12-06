package com.example.demo.entity;

public class Booking {
    private String bookingID;
    private long checkIn;
    private long checkOut;
    private int cleaningWorker;
    private int repairWorker;
    private int washingWorker;
    private int slotID;
    private int userID;

    public String getBookingID() {
        return bookingID;
    }

    public long getCheckIn() {
        return checkIn;
    }

    public long getCheckOut() {
        return checkOut;
    }

    public int getSlotID() {
        return slotID;
    }

    public int getUserID() {
        return userID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setCheckIn(long checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(long checkOut) {
        this.checkOut = checkOut;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCleaningWorker() {
        return cleaningWorker;
    }

    public int getRepairWorker() {
        return repairWorker;
    }

    public int getWashingWorker() {
        return washingWorker;
    }

    public void setCleaningWorker(int cleaningWorker) {
        this.cleaningWorker = cleaningWorker;
    }
    
    public void setRepairWorker(int repairWorker) {
        this.repairWorker = repairWorker;
    }

    public void setWashingWorker(int washingWorker) {
        this.washingWorker = washingWorker;
    }
}
