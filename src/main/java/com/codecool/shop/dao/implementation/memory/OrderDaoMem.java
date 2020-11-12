package com.codecool.shop.dao.implementation.memory;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.manager.DaoManager;
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

    private OrderDaoMem() {
    }

    private final HashMap<Integer, Order> orders = new HashMap<>();
    private final CartDao cartDao = DaoManager.getInstance().getCartDao();

    @Override
    public Order getOrder(int userId) {
        return orders.get(userId);
    }

    @Override
    public void addCart(int userId, Cart cart) {
        if (!orders.containsKey(userId)) {
            orders.put(userId, new Order());
            orders.get(userId).setOrderStart(new Date());
        }

        orders.get(userId).setCart(cart);
    }

    @Override
    public void addUserDetails(int userId, UserDetails userDetails) {
        if (!orders.containsKey(userId)) {
            orders.put(userId, new Order());
            orders.get(userId).setOrderStart(new Date());
        }

        orders.get(userId).setUserDetails(userDetails);
    }

    @Override
    public void addPayment(int userId, Payment payment) {
        if (!orders.containsKey(userId)) {
            orders.put(userId, new Order());
            orders.get(userId).setOrderStart(new Date());
        }

        orders.get(userId).setPayment(payment);
    }

    @Override
    public void deleteOrder(int userId) {
        cartDao.delete(userId);
        orders.remove(userId);
    }
}
