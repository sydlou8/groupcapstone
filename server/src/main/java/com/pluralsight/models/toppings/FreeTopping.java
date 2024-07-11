package com.pluralsight.models.toppings;

public abstract class FreeTopping implements Topping {
    private String type;
    public FreeTopping (String type) {
        this.type = type;
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public double getPrice(int size) {
        return 0;
    }
    @Override
    public String toString() {
        return "- " + getType() + "\n";
    }
}
