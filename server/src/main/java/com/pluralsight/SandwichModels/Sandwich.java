package com.pluralsight.SandwichModels;

public class Sandwich
{
    private int sandwichId;
    private int breadId;
    private int meatId;
    private int cheeseId;
    private int toppingId;
    private int sauceId;
    private int sideId;
    private double price;

    public Sandwich() {
    }

    public Sandwich(int sandwichId, int breadId, int meatId, int cheeseId, int toppingId, int sauceId, int sideId, double price)
    {
        this.sandwichId = sandwichId;
        this.breadId = breadId;
        this.meatId = meatId;
        this.cheeseId = cheeseId;
        this.toppingId = toppingId;
        this.sauceId = sauceId;
        this.sideId = sideId;
        this.price = price;
    }

    public int getSandwichId() {
        return sandwichId;
    }

    public void setSandwichId(int sandwichId) {
        this.sandwichId = sandwichId;
    }

    public int getBreadId() {
        return breadId;
    }

    public void setBreadId(int breadId) {
        this.breadId = breadId;
    }

    public int getMeatId() {
        return meatId;
    }

    public void setMeatId(int meatId) {
        this.meatId = meatId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public int getSauceId() {
        return sauceId;
    }

    public void setSauceId(int sauceId) {
        this.sauceId = sauceId;
    }

    public int getSideId() {
        return sideId;
    }

    public void setSideId(int sideId) {
        this.sideId = sideId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
