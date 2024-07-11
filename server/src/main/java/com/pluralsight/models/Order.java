package com.pluralsight.models;

public abstract class Order {
    private int size;
    private double price;

    public Order(int size, double price) {
        this.size = size;
        this.price = price;
    }

    public int getSize() {
        return size;
    }
    public double getPrice() {
        return price;
    }
    public double getTotalPrice() {
        return price;
    }
}
