package com.pluralsight.data;

import com.pluralsight.SandwichModels.Orders;
import com.pluralsight.models.Order;

import java.util.List;

public interface OrderDao
{
    List<Orders> getAllOrders();
    Orders getById(int orderId);
    Orders create(Orders order);
    void update(int orderId, Orders order);
    void delete(int orderId);
}
