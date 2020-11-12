package com.codecool.shop.dao.implementation.memory;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;

import java.util.HashMap;
import java.util.Map;

public class CartDaoMem implements CartDao {

    private static CartDaoMem instance = null;

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    private CartDaoMem() {
    }

    Map<Integer, Cart> data = new HashMap<>();

    @Override
    public void add(Cart cart) {
        data.put(cart.getUserId(), cart);
    }

    @Override
    public Cart find(int userId) {
        if (data.containsKey(userId)) {
            return data.get(userId);
        }

        Cart cart = new Cart(userId);

        add(cart);

        return cart;
    }

    @Override
    public void delete(int userId) {
        data.remove(userId);
    }

    @Override
    public void update(Cart cart) {
        data.replace(cart.getUserId(), cart);
    }
}
