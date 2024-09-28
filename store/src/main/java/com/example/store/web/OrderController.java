package com.example.store.web;

import com.example.store.model.Order;
import com.example.store.model.User;
import com.example.store.service.OrderService;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/checkout")
    public String checkout(Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());

        if (user.isPresent()) {
            orderService.createOrder(user.get());
            return "redirect:/orders";
        }

        return "redirect:/cart";
    }
    @GetMapping("/orders")
    public String listAllOrders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<User> optionalUser = userService.findByUsername(currentUserName);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Order> orders = orderService.getOrdersForUser(user);
            model.addAttribute("orders", orders);
        } else {
            model.addAttribute("orders", new ArrayList<>());
            model.addAttribute("error", "User not found");
        }

        return "orders";
    }


}