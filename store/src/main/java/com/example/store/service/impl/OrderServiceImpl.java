package com.example.store.service.impl;

import com.example.store.model.*;
import com.example.store.repository.CartRepository;
import com.example.store.repository.OrderRepository;
import com.example.store.repository.ProductRepository;
import com.example.store.service.CartService;
import com.example.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(User user) {
        Cart cart = cartService.getCartForUser(Optional.of(user));

        if (cart.getCartItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty, cannot place order.");
        }

        Order order = new Order();
        order.setUser(user);
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            OrderItem orderItem = new OrderItem(order, product, quantity);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);

        cart.getCartItems().clear();
        cartRepository.save(cart);

        return savedOrder;
    }

    @Override
    public List<Order> getOrdersForUser(User user) {
        return orderRepository.findByUserId(user.getId());
    }
}