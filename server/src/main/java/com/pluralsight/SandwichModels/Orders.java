package com.pluralsight.SandwichModels;

import java.time.LocalDate;

public class Orders
{
    private int orderId;
    private int sandwichId;
    private int drinkId;
    private LocalDate orderDate;
    private int size;
    private double price;

    public Orders(int orderId, int sandwichId, int drinkId, LocalDate orderDate, int size, double price) {
        this.orderId = orderId;
        this.sandwichId = sandwichId;
        this.drinkId = drinkId;
        this.orderDate = orderDate;
        this.size = size;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSandwichId() {
        return sandwichId;
    }

    public void setSandwichId(int sandwichId) {
        this.sandwichId = sandwichId;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
