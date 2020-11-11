package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.Product;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    @Override
    public void add(Product product) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO public.product (name, description, price, currency, category_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency().getCurrencyCode());
            statement.setInt(5, product.getCategoryId());
            statement.setInt(6, product.getSupplierId());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getInt("id"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Product find(int id) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.product WHERE id=?"
            );

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt("supplier_id")
                );
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void remove(int id) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM public.product WHERE id=?"
            );

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.product"
            );

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt("supplier_id")
                ));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getBySupplier(int supplierId) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.product WHERE supplier_id=?"
            );

            statement.setInt(1, supplierId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt("supplier_id")
                ));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public List<Product> getByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.product WHERE category_id=?"
            );

            statement.setInt(1, categoryId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt("supplier_id")
                ));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}
