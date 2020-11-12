package com.codecool.shop.model;

import java.util.Date;

public class Order {
    private int id;
    private Date date;
    private int userId;
    private UserDetails userDetails;
    private Cart cart;

    public Order(int userId, UserDetails userDetails, Cart cart) {
        this.userId = userId;
        this.userDetails = userDetails;
        this.cart = cart;
    }

    public Order(int id, Date date, int userId, UserDetails userDetails) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.userDetails = userDetails;
    }

    public Order(int id, Date date, int userId, UserDetails userDetails, Cart cart) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.userDetails = userDetails;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
