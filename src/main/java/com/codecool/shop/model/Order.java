package com.codecool.shop.model;

import java.util.Date;

public class Order {
    private Date orderStart;
    private Date orderFinish;
    private UserDetails userDetails;
    private Cart cart;
    private Payment payment;

    public Date getOrderStart() {
        return orderStart;
    }

    public void setOrderStart(Date orderStart) {
        this.orderStart = orderStart;
    }

    public Date getOrderFinish() {
        return orderFinish;
    }

    public void setOrderFinish(Date orderFinish) {
        this.orderFinish = orderFinish;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
