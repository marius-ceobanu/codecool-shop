package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {

    Order get(int id);
    void add(Order order);

    List<Order> getByUser(int userId);
}
