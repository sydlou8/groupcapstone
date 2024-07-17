package com.pluralsight.SandwichModels;

public class Drinks
{
    private int drinkId;
    private String drinkType;
    private double drinkPrice;

    public Drinks() {
    }

    public Drinks(int drinkId, String drinkType, double drinkPrice)
    {
        this.drinkId = drinkId;
        this.drinkType = drinkType;
        this.drinkPrice = drinkPrice;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(double drinkPrice) {
        this.drinkPrice = drinkPrice;
    }
}
