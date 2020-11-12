package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.Supplier;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {

    private static SupplierDaoJdbc instance = null;

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    private SupplierDaoJdbc() {
    }

    @Override
    public void add(Supplier supplier) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO public.supplier (name, description) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                supplier.setId(resultSet.getInt("id"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.supplier WHERE id=?"
            );

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
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
                    "DELETE FROM public.supplier WHERE id=?"
            );

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.supplier"
            );

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                suppliers.add(new Supplier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return suppliers;
    }
}
