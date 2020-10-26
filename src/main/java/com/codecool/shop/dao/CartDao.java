package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

public interface CartDao {

    void add(int userId, Product product, int quantity);
    Cart find(int userId);
    void remove(int userId, Product product);
    void removeOne(int userId, Product product);
}
