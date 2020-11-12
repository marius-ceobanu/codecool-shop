package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {

    private static OrderDaoJdbc instance = null;

    public static OrderDaoJdbc getInstance() {
        if (instance == null) {
            instance = new OrderDaoJdbc();
        }
        return instance;
    }

    private OrderDaoJdbc() {
    }

    @Override
    public Order get(int id) {
        Order order = null;

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.order_history WHERE id=?"
            );

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UserDetails details = new UserDetails(
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("county"),
                        resultSet.getString("zip_code"),
                        resultSet.getBoolean("billing_address"),
                        resultSet.getString("payment")
                );

                order = new Order(
                        resultSet.getInt("id"),
                        new Date(resultSet.getTimestamp("date").getTime()),
                        resultSet.getInt("user_id"),
                        details
                );

                order.setCart(getCart(order.getId(), order.getUserId(), connection));
            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void add(Order order) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO public.order_history (user_id, payment, billing_address , name, phone, email, address, city, county, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getUserDetails().getPaymentMethod());
            statement.setBoolean(3, order.getUserDetails().getBillingSameAsShipping());
            statement.setString(4, order.getUserDetails().getFullName());
            statement.setString(5, order.getUserDetails().getPhoneNumber());
            statement.setString(6, order.getUserDetails().getEmail());
            statement.setString(7, order.getUserDetails().getAddress());
            statement.setString(8, order.getUserDetails().getCity());
            statement.setString(9, order.getUserDetails().getCounty());
            statement.setString(10, order.getUserDetails().getZipCode());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                order.setId(resultSet.getInt("id"));
                order.setDate(new Date(resultSet.getTimestamp("date").getTime()));
            }

            addCartItems(order, connection);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getByUser(int userId) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.order_history WHERE user_id=?"
            );

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UserDetails details = new UserDetails(
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("county"),
                        resultSet.getString("zip_code"),
                        resultSet.getBoolean("billing_address"),
                        resultSet.getString("payment")
                );

                Order order = new Order(
                        resultSet.getInt("id"),
                        new Date(resultSet.getTimestamp("date").getTime()),
                        resultSet.getInt("user_id"),
                        details
                );

                order.setCart(getCart(order.getId(), order.getUserId(), connection));

                orders.add(order);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private Cart getCart(int orderId, int userId, Connection connection) throws SQLException {
        Cart cart = new Cart(userId);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT id AS p_id, name AS p_name, description AS p_description, price AS p_price, currency AS p_currency, category_id, supplier_id, quantity FROM public.order_products inner join public.product p on p.id = order_products.product_id WHERE order_id=?"
        );

        statement.setInt(1, orderId);

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

        return cart;
    }

    private void addCartItems(Order order, Connection connection) throws SQLException {
        for (CartItem item : order.getCart().getCartItems()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO public.order_products (order_id, product_id, quantity) VALUES (?, ?, ?)"
            );

            statement.setInt(1, order.getId());
            statement.setInt(2, item.getProduct().getId());
            statement.setInt(3, item.getQuantity());

            statement.executeUpdate();
        }
    }
}
