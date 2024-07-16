package com.pluralsight.SandwichModels;

public class Bread
{
    private int breadId;
    private String breadName;
    private double breadPrice;

    public Bread() {
    }

    public Bread(int breadId, String breadName, double breadPrice) {
        this.breadId = breadId;
        this.breadName = breadName;
        this.breadPrice = breadPrice;
    }

    public int getBreadId() {
        return breadId;
    }

    public void setBreadId(int breadId) {
        this.breadId = breadId;
    }

    public String getBreadName() {
        return breadName;
    }

    public void setBreadName(String breadName) {
        this.breadName = breadName;
    }

    public double getBreadPrice() {
        return breadPrice;
    }

    public void setBreadPrice(double breadPrice) {
        this.breadPrice = breadPrice;
    }
}
