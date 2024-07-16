package com.pluralsight.models.toppings;

public class Sauces extends FreeTopping {
    private int sauceId;
    public Sauces(int sauceId, String type) {
        super(type);
        this.sauceId = sauceId;
    }
}
