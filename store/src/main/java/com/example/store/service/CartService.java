package com.example.store.service;

import com.example.store.model.Cart;
import com.example.store.model.CartItem;
import com.example.store.model.Product;
import com.example.store.model.User;
import com.example.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartForUser(Optional<User> user) {
        return user.flatMap(u -> cartRepository.findByUserId(u.getId()))
                .orElseGet(() -> createCartForUser(user.get()));
    }

    private Cart createCartForUser(User user) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        return cartRepository.save(newCart);
    }

    public void addProductToCart(Long cartId, Product product, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        boolean productExistsInCart = false;
        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                productExistsInCart = true;
                break;
            }
        }

        if (!productExistsInCart) {
            CartItem cartItem = new CartItem(product, quantity);
            cart.addCartItem(cartItem);
        }

        cartRepository.save(cart);
    }
    public double calculateTotal(Cart cart) {
        double total = 0.0;
        for (CartItem cartItem : cart.getCartItems()) {
            double productPrice = cartItem.getProduct().getPrice();
            int quantity = cartItem.getQuantity();
            total += productPrice * quantity;
        }
        return total;
    }
}