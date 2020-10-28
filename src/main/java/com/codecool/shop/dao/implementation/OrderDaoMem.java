package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Payment;
import com.codecool.shop.model.UserDetails;

import java.util.Date;
import java.util.HashMap;

public class OrderDaoMem implements OrderDao {

    private static OrderDaoMem instance = null;

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    private final HashMap<Integer, Order> orders = new HashMap<>();

    public Order getOrder(int userId) {
        return orders.get(userId);
    }

    public void addCart(int userId, Cart cart) {
        if (!orders.containsKey(userId)) {
            orders.put(userId, new Order());
            orders.get(userId).setOrderStart(new Date());
        }

        orders.get(userId).setCart(cart);
    }

    public void addUserDetails(int userId, UserDetails userDetails) {
        if (!orders.containsKey(userId)) {
            orders.put(userId, new Order());
            orders.get(userId).setOrderStart(new Date());
        }

        orders.get(userId).setUserDetails(userDetails);
    }

    public void addPayment(int userId, Payment payment) {
        if (!orders.containsKey(userId)) {
            orders.put(userId, new Order());
            orders.get(userId).setOrderStart(new Date());
        }

        orders.get(userId).setPayment(payment);
    }

    @Override
    public void deleteOrder(int userId) {
        orders.remove(userId);
    }
}
