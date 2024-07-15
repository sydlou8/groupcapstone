package com.pluralsight.SandwichModels;

public class Chips
{
    private int chipsId;
    private String chipType;

    public Chips() {
    }

    public Chips(int chipsId, String chipType)
    {
        this.chipsId = chipsId;
        this.chipType = chipType;
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
}

