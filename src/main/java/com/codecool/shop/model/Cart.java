package com.codecool.shop.model;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart extends HashMap<Product, Integer> {

    public int productCount() {
        AtomicInteger count = new AtomicInteger();
        this.forEach(((product, quantity) -> count.addAndGet(quantity)));
        return count.get();
    }

    public float totalPrice() {
        var lambdaContext = new Object() {
            float count = 0;
        };
        this.forEach(((product, quantity) -> lambdaContext.count += (product.getDefaultPrice() * quantity)));
        return lambdaContext.count;
    }
}
