package com.codecool.shop.dao.implementation.memory;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class CartDaoMem implements CartDao {

    Map<Integer, Cart> data = new HashMap<>();
    private static CartDaoMem instance = null;

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(int userId, Product product, int quantity) {
        if (!data.containsKey(userId)) {
            data.put(userId, new Cart());
        }

        Cart userCart = data.get(userId);

        if (!userCart.containsKey(product)) {
            userCart.put(product, quantity);
        } else {
            userCart.replace(product, userCart.get(product) + 1);
        }
    }

    @Override
    public Cart find(int userId) {
        if (!data.containsKey(userId)) {
            data.put(userId, new Cart());
        }

        return data.get(userId);
    }

    @Override
    public void remove(int userId, Product product) {
        Cart userCart = getCart(userId, product);

        userCart.remove(product);
    }

    @Override
    public void removeCount(int userId, Product product, int count) {
        Cart userCart = getCart(userId, product);

        int quantity = userCart.get(product) - count;
        if (quantity > 0) {
            userCart.replace(product, quantity);
        } else {
            userCart.remove(product);
        }

    }

    private Cart getCart(int userId, Product product) {
        if (!data.containsKey(userId)) {
            throw new IllegalArgumentException(String.format("user %d doesn't have a cart", userId));
        }

        Cart userCart = data.get(userId);
        if (!userCart.containsKey(product)) {
            throw new IllegalArgumentException(String.format("%s : no such product in cart", product));
        }

        return userCart;
    }

    @Override
    public void deleteCart(int userId) {
        data.remove(userId);
    }
}
