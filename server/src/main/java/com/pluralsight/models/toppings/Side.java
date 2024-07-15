package com.pluralsight.models.toppings;

public class Side extends FreeTopping{
    private int sideId;
    public Side(int sideId, String type) {
        super(type);
        this.sideId =sideId;
    }
}
