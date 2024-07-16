package com.pluralsight.models.sandwiches;

import com.pluralsight.models.toppings.*;

import java.util.HashSet;

public class BLT extends Sandwich {
    public BLT() {
        super(3, "White", true, new HashSet<>());
        setToppings(this.loadToppings());
    }
    private HashSet<Topping> loadToppings() {
        HashSet<Topping> toppings = new HashSet<>();
        toppings.add(new PremiumTopping("Meat", "Bacon", false));
        toppings.add(new PremiumTopping("Cheese", "Cheddar", false));
        toppings.add(new RegularTopping("Lettuce"));
        toppings.add(new RegularTopping("Tomato"));
        toppings.add(new Sauces(99, "Ranch"));
        return toppings;
    }
}
