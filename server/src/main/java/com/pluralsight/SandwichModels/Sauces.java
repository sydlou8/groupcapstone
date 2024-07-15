package com.pluralsight.SandwichModels;

public class Sauces
{
    private int sauceId;
    private String sauceType;

    public Sauces() {
    }

    public Sauces(int sauceId, String sauceType)
    {
        this.sauceId = sauceId;
        this.sauceType = sauceType;
    }

    public int getSauceId() {
        return sauceId;
    }

    public void setSauceId(int sauceId) {
        this.sauceId = sauceId;
    }

    public String getSauceType() {
        return sauceType;
    }

    public void setSauceType(String sauceType) {
        this.sauceType = sauceType;
    }
}
