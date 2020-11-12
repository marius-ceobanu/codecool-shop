package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Payment;
import com.codecool.shop.model.UserDetails;

import java.util.List;

public interface OrderDao {

    Order get(int id);
    void add(Order order);

    List<Order> getByUser(int userId);
}
