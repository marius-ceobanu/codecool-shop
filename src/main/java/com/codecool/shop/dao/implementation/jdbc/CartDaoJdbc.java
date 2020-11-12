package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoJdbc implements CartDao {

    private static CartDaoJdbc instance = null;

    public static CartDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CartDaoJdbc();
        }
        return instance;
    }

    private CartDaoJdbc() {
    }

    @Override
    public void add(Cart cart) {
        try (Connection connection = DatabaseManager.connect()) {
            for (CartItem item : cart.getCartItems()) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO public.cart (user_id, product_id, quantity) VALUES (?, ?, ?)"
                );

                statement.setInt(1, cart.getUserId());
                statement.setInt(2, item.getProduct().getId());
                statement.setInt(3, item.getQuantity());

                statement.executeUpdate();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cart find(int userId) {
        Cart cart = new Cart(userId);

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id AS p_id, name AS p_name, description AS p_description, price AS p_price, currency AS p_currency, category_id, supplier_id, quantity FROM public.cart inner join public.product p on p.id = cart.product_id WHERE user_id=?"
            );

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            List<CartItem> cartItems = new ArrayList<>();

            while (resultSet.next()) {
                cartItems.add(new CartItem(
                        new Product(
                                resultSet.getInt("p_id"),
                                resultSet.getString("p_name"),
                                resultSet.getFloat("p_price"),
                                resultSet.getString("p_currency"),
                                resultSet.getString("p_description"),
                                resultSet.getInt("category_id"),
                                resultSet.getInt("supplier_id")
                        ),
                        resultSet.getInt("quantity")
                ));
            }

            cart.setCartItems(cartItems);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return cart;
    }

    @Override
    public void delete(int userId) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM public.cart WHERE user_id=?"
            );

            statement.setInt(1, userId);

            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cart cart) {
        delete(cart.getUserId());
        add(cart);
    }
}
