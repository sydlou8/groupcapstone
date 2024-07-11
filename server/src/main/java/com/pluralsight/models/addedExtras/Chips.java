package com.pluralsight.models.addedExtras;

public class Chips extends AddOn {
    private final double price = 1.50;
    public Chips(String name) {
        super(1, 1.50, name);
    }

    @Override
    public String toString () {
        return String.format("%-43s $%4.2f", getName(), this.price);
    }
}
