package com.pluralsight.SandwichModels;

public class Sides
{
    private int sideId;
    private String sideType;

    public Sides() {
    }

    public Sides(int sideId, String sideType)
    {
        this.sideId = sideId;
        this.sideType = sideType;
    }

    public int getSideId() {
        return sideId;
    }

    public void setSideId(int sideId) {
        this.sideId = sideId;
    }

    public String getSideType() {
        return sideType;
    }

    public void setSideType(String sideType) {
        this.sideType = sideType;
    }
}
