package com.pluralsight.models.sandwiches;

import com.pluralsight.models.toppings.*;

import java.util.HashSet;

public class Philly extends Sandwich{
    public Philly() {
        super(3, "White", true, new HashSet<>());
        setToppings(this.loadToppings());
    }
    private HashSet<Topping> loadToppings() {
        HashSet<Topping> toppings = new HashSet<>();
        toppings.add(new PremiumTopping("Meat", "Steak", false));
        toppings.add(new PremiumTopping("Cheese", "American", false));
        toppings.add(new RegularTopping("Peppers"));
        toppings.add(new Sauces(sauceId, "Mayo"));
        return toppings;
    }
}
