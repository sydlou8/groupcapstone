package com.pluralsight.SandwichModels;

import java.util.List;

public class Order {
    private int orderId;
    private List<Sandwich> sandwiches;
    private List<Chips> chips;
    private List<Drinks> drinks;
    private double orderPrice;

    public Order() {}

    public Order(int orderId, List<Sandwich> sandwiches, List<Chips> chips, List<Drinks> drinks, double orderPrice) {
        this.orderId = orderId;
        this.sandwiches = sandwiches;
        this.chips = chips;
        this.drinks = drinks;
        this.orderPrice = orderPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public List<Chips> getChips() {
        return chips;
    }

    public void setChips(List<Chips> chips) {
        this.chips = chips;
    }

    public List<Drinks> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drinks> drinks) {
        this.drinks = drinks;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
