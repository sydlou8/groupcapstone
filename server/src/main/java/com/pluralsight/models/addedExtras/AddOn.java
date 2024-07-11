package com.pluralsight.models.addedExtras;

import com.pluralsight.models.Order;

public abstract class AddOn extends Order {
    private String name;

    public AddOn(int size, double price, String name) {
        super(size, price);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
