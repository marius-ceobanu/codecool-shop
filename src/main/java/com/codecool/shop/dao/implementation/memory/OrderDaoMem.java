package com.codecool.shop.dao.implementation.memory;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    private final List<Order> data = new ArrayList<>();

    @Override
    public Order get(int id) {
        return data.stream().filter(order -> order.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void add(Order order) {
        order.setId(data.size() + 1);
        order.setDate(new Date());
        data.add(order);
    }

    @Override
    public List<Order> getByUser(int userId) {
        return data.stream().filter(order -> order.getUserId() == userId).collect(Collectors.toList());
    }
}
