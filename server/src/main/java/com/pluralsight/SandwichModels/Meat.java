package com.pluralsight.SandwichModels;

public class Meat
{
    private int meatId;
    private String meatType;
    private boolean extraMeat;

    public Meat() {
    }

    public Meat(int meatId, String meatType, boolean extraMeat)
    {
        this.meatId = meatId;
        this.meatType = meatType;
        this.extraMeat = extraMeat;
    }

    public int getMeatId() {
        return meatId;
    }

    public void setMeatId(int meatId) {
        this.meatId = meatId;
    }

    public String getMeatType() {
        return meatType;
    }

    public void setMeatType(String meatType) {
        this.meatType = meatType;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
    }
}
