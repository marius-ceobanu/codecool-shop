package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Payment;
import com.codecool.shop.model.UserDetails;

public interface OrderDao {
    Order getOrder(int userId);
    void addCart(int userId, Cart cart);
    void addUserDetails(int userId, UserDetails userDetails);
    void addPayment(int userId, Payment payment);
    void deleteOrder(int userId);
}
