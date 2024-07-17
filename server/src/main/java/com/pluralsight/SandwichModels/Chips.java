package com.pluralsight.SandwichModels;

public class Chips
{
    private int chipsId;
    private String chipType;
    private double chipsPrice;

    public Chips() {
    }

    public Chips(int chipsId, String chipType, double chipsPrice)
    {
        this.chipsId = chipsId;
        this.chipType = chipType;
        this.chipsPrice = chipsPrice;
    }

    public int getChipsId() {
        return chipsId;
    }

    public void setChipsId(int chipsId) {
        this.chipsId = chipsId;
    }

    public String getChipType() {
        return chipType;
    }

    public void setChipType(String chipType) {
        this.chipType = chipType;
    }

    public double getChipsPrice() {
        return chipsPrice;
    }

    public void setChipsPrice(double chipsPrice) {
        this.chipsPrice = chipsPrice;
    }
}

