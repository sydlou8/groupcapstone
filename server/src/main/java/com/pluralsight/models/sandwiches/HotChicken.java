package com.pluralsight.models.sandwiches;

import com.pluralsight.models.toppings.*;

import java.util.HashSet;

public class HotChicken extends Sandwich{
    public HotChicken() {
        super(3, "White", true, new HashSet<>());
        setToppings(this.loadToppings());
    }
    private HashSet<Topping> loadToppings() {
        HashSet<Topping> toppings = new HashSet<>();
        toppings.add(new PremiumTopping("Meat", "Chicken", false));
        toppings.add(new PremiumTopping("Cheese", "American", false));
        toppings.add(new RegularTopping("Lettuce"));
        toppings.add(new RegularTopping("Tomato"));
        toppings.add(new RegularTopping("Onion"));
        toppings.add(new RegularTopping("Peppers"));
        toppings.add(new Sauces(sauceId, "Thousand Islands"));
        return toppings;
    }
}
