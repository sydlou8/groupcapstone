package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private List<Order> orders = new ArrayList<>();

    public void addToOrder(Order o) {
        orders.add(o);
    }
    public void removeFromOrder(Order o) {
        orders.remove(o);
    }
    public void clearOrder() {
        orders.clear();
    }

    public double getTotalPrice() {
        return orders.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your Current Order Details: \n");
        orders.stream().filter(Objects::nonNull).forEach(order -> {
            sb.append("\n");
            sb.append(order.toString());
        });
        sb.append("\n").append(".".repeat(50)).append("\n");
        sb.append(String.format("%-44s", "Total"));
        sb.append("$").append(String.format("%4.2f", this.getTotalPrice()));
        return sb.toString();
    }

}
