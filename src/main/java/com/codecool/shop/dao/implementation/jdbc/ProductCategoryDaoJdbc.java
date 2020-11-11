package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.ProductCategory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO public.product_category (name, description, department) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getDepartment());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                category.setId(resultSet.getInt("id"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.product_category WHERE id=?"
            );

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
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
                    "DELETE FROM public.product_category WHERE id=?"
            );

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> categories = new ArrayList<>();

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.product_category"
            );

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                categories.add(new ProductCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return categories;
    }
}
