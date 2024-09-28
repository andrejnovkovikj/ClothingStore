package com.example.store.web;

import com.example.store.model.Cart;
import com.example.store.model.CartItem;

import com.example.store.model.Product;
import com.example.store.model.User;
import com.example.store.service.CartService;
import com.example.store.service.ProductService;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        Cart cart = cartService.getCartForUser(user);

        double cartTotal = cartService.calculateTotal(cart);
        model.addAttribute("cart", cart);
        model.addAttribute("products", cart.getCartItems());
        model.addAttribute("cartTotal", cartTotal);

        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartService.getCartForUser(Optional.ofNullable(user));
        Product product = productService.findById(productId);

        if (product != null) {
            cartService.addProductToCart(cart.getId(), product, quantity);
        } else {
            System.out.println("Product not found for ID: " + productId);
        }

        return "redirect:/cart";
    }

}
