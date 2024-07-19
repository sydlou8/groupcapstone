package com.pluralsight.data;

import com.pluralsight.SandwichModels.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getAllOrders();
    Order create(Order order);
}
