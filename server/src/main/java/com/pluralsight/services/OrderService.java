package com.pluralsight.services;

import com.pluralsight.SandwichModels.Order;
import com.pluralsight.data.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
    public Order createOrder(Order order) {
        return orderDao.create(order);
    }
}
