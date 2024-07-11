package com.pluralsight.models.sandwiches;

import com.pluralsight.models.Order;
import com.pluralsight.models.toppings.*;

import java.util.HashSet;

public class Sandwich extends Order {
    String breadChoice;
    boolean toasted;
    HashSet<Topping> toppings;

    public Sandwich(int size, String breadChoice, boolean toasted, HashSet<Topping> toppings) {
        super(
                size,
                switch (size) {
                    case 1 -> 5.50;
                    case 2 -> 7.00;
                    case 3 -> 8.50;
                    default -> throw new IllegalStateException("Unexpected value: " + size);
                }
        );
        this.breadChoice = breadChoice;
        this.toasted = toasted;
        this.toppings = toppings;
    }

    public String getBreadChoice() {
        return breadChoice;
    }

    public boolean isToasted() {
        return toasted;
    }

    public HashSet<Topping> getToppings() {
        return toppings;
    }
    protected void setToppings(HashSet<Topping> toppings) {
        this.toppings.clear();
        this.toppings.addAll(toppings);
    }

    @Override
    public double getTotalPrice() {
        return super.getPrice() + toppings.stream()
                .mapToDouble(topping -> topping.getPrice(super.getSize()))
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sandwich = new StringBuilder(String.format("%-33s %-9s $%4.2f\n",
                "Sandwich",
                switch(getSize()) {
                    case 1 -> "Small";
                    case 2 -> "Medium";
                    case 3 -> "Large";
                    default -> throw new IllegalStateException("Unexpected value: " + super.getSize());
                },
                this.getTotalPrice()));
        sandwich.append("\tToppings: \n");
        sandwich.append("\tPremium Toppings\n");
        toppings.stream()
                .filter(topping -> topping instanceof PremiumTopping)
                .forEach(topping -> {
                    sandwich.append("\t\t");
                    sandwich.append(topping);
                });
        sandwich.append("\tRegular Toppings\n");
        toppings.stream()
                .filter(topping -> topping instanceof RegularTopping)
                .filter(topping -> !topping.getType().isBlank())
                .forEach(topping -> {
                    sandwich.append("\t\t");
                    sandwich.append(topping);
                });
        if(toppings.stream().filter(topping -> topping instanceof RegularTopping).toList().isEmpty()) sandwich.append("\t\tNone\n");
        sandwich.append("\tSauces\n");
        toppings.stream()
                .filter(topping -> topping instanceof Sauces)
                .filter(topping -> !topping.getType().isBlank())
                .forEach(topping -> {
                    sandwich.append("\t\t");
                    sandwich.append(topping);
                });
        if(toppings.stream().filter(topping -> topping instanceof Sauces).toList().isEmpty()) sandwich.append("\t\tNone\n");
        sandwich.append("\tSides\n");
        toppings.stream()
                .filter(topping -> topping instanceof Side)
                .filter(topping -> !topping.getType().isBlank())
                .forEach(topping -> {
                    sandwich.append("\t\t");
                    sandwich.append(topping);
                });
        if(toppings.stream().filter(topping -> topping instanceof Side).toList().isEmpty()) sandwich.append("\t\tNone\n");
        sandwich.append(isToasted() ? "\tToasted" : "\tNot Toasted");
        return sandwich.toString();
    }
}
