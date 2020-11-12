package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Cart {
    private int userId;
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        cartItems.add(new CartItem(product, quantity));
    }

    public void decreaseQuantity(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() - quantity);
                if (item.getQuantity() <= 0) {
                    cartItems.remove(item);
                }
                return;
            }
        }
    }

    public void removeProduct(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                cartItems.remove(item);
                return;
            }
        }
    }

    public int productCount() {
        return cartItems.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public float totalPrice() {
        AtomicReference<Float> total = new AtomicReference<>(0f);

        cartItems.forEach(item ->
            total.updateAndGet(v -> v + item.getProduct().getDefaultPrice() * item.getQuantity())
        );

        float x = total.get();
        return (float)((int)x) + (float)((int)(x * 100) % 100) / 100; // Floating points for the f-ing win
    }
}
