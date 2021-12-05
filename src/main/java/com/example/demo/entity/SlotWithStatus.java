package com.example.demo.entity;


public class SlotWithStatus {
    private boolean isFree;
    private boolean isRecommended;
    private long freeTime;
    private int slotID;

    public void setRecommended(boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public boolean getIsRecommended() {
        return isRecommended;
    }

    public int getSlot() {
        return slotID;
    }

    public boolean getIsFree() {
        return isFree;
    }
     
    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }
    
    public void setSlot(int slot) {
        this.slotID = slot;
    }

    public long getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(long freeTime) {
        this.freeTime = freeTime;
    }

}
