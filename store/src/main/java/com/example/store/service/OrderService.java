package com.example.store.service;

import com.example.store.model.Order;
import com.example.store.model.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user);
    List<Order> getOrdersForUser(User user);
}
