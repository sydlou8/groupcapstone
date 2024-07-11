package com.pluralsight.SandwichModels;

public class Cheese
{
    private int cheeseId;
    private String cheeseType;
    private  boolean extraCheese;

    public Cheese() {
    }

    public Cheese(int cheeseId, String cheeseType, boolean extraCheese)
    {
        this.cheeseId = cheeseId;
        this.cheeseType = cheeseType;
        this.extraCheese = extraCheese;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public String getCheeseType() {
        return cheeseType;
    }

    public void setCheeseType(String cheeseType) {
        this.cheeseType = cheeseType;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }
}
