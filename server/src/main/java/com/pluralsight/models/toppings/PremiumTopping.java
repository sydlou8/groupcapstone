package com.pluralsight.models.toppings;

public class PremiumTopping implements Topping {
    // This should be any paid-for toppings like cheese and meat
    private String type;
    private String name;
    private boolean isExtra;

    public PremiumTopping(String type, String name, boolean isExtra) {
        this.type = type;
        this.name = name;
        this.isExtra = isExtra;
    }

    @Override
    public double getPrice(int size) {
        double base = type.equalsIgnoreCase("meat") ?
                switch (size) {
                    case 1 -> 1.00;
                    case 2 -> 2.00;
                    case 3 -> 3.00;
                    default -> throw new IllegalStateException("Unexpected value: " + size);
                } :
                switch (size) {
                    case 1 -> 0.75;
                    case 2 -> 1.50;
                    case 3 -> 2.25;
                    default -> throw new IllegalStateException("Unexpected value: " + size);
                };
        double extra = isExtra ? ( type.equalsIgnoreCase("meat") ?
                switch (size) {
                    case 1 -> 0.50;
                    case 2 -> 1.00;
                    case 3 -> 1.50;
                    default -> throw new IllegalStateException("Unexpected value: " + size);
                } :
                switch (size) {
                    case 1 -> 0.30;
                    case 2 -> 0.60;
                    case 3 -> 0.90;
                    default -> throw new IllegalStateException("Unexpected value: " + size);
                }) : 0;
        return base + extra;
    }
    @Override
    public String getType() {
        return type;
    }
    @Override
    public String toString() {
        return "- " + getName() +
                "\n\t\t" +
                (isExtra ? "-- add Extra": "-- Standard")
                + "\n";
    }

    public String getName() {
        return name;
    }
}
