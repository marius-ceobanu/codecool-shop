package com.codecool.shop.manager;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

public class CartManager {
    public static void addProduct(int userId, Product product, int quantity) {
        CartDao cartDataStore = DaoManager.getInstance().getCartDao();

        Cart cart = cartDataStore.find(userId);
        cart.addProduct(product, quantity);

        cartDataStore.update(cart);
    }

    public static void decreaseQuantity(int userId, Product product, int quantity) {
        CartDao cartDataStore = DaoManager.getInstance().getCartDao();

        Cart cart = cartDataStore.find(userId);
        cart.decreaseQuantity(product, quantity);

        cartDataStore.update(cart);
    }

    public static void removeProduct(int userId, Product product) {
        CartDao cartDataStore = DaoManager.getInstance().getCartDao();

        Cart cart = cartDataStore.find(userId);
        cart.removeProduct(product);

        cartDataStore.update(cart);
    }
}
