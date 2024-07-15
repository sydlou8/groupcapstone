package com.pluralsight.SandwichModels;

public class Drinks
{
    private int drinkId;
    private String drinkType;

    public Drinks() {
    }

    public Drinks(int drinkId, String drinkType)
    {
        this.drinkId = drinkId;
        this.drinkType = drinkType;
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
}
