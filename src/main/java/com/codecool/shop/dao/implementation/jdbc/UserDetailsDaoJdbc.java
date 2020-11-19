package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.UserDetailsDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.UserDetails;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsDaoJdbc implements UserDetailsDao {
    private static UserDetailsDaoJdbc instance = null;

    public static UserDetailsDaoJdbc getInstance() {
        if (instance == null) {
            instance = new UserDetailsDaoJdbc();
        }
        return instance;
    }

    private UserDetailsDaoJdbc() {
    }

    @Override
    public void add(int userId, UserDetails userDetails) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO public.user_info (user_id, name, phone, email, address, city, county, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setInt(1, userId);
            statement.setString(2, userDetails.getFullName());
            statement.setString(3, userDetails.getPhoneNumber());
            statement.setString(4, userDetails.getEmail());
            statement.setString(5, userDetails.getAddress());
            statement.setString(6, userDetails.getCity());
            statement.setString(7, userDetails.getCounty());
            statement.setString(8, userDetails.getZipCode());

            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDetails getById(int id) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM public.user_info WHERE user_id=?"
            );

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new UserDetails(
                resultSet.getString("name"),
                resultSet.getString("phone"),
                resultSet.getString("email"),
                resultSet.getString("address"),
                resultSet.getString("city"),
                resultSet.getString("county"),
                resultSet.getString("zip_code"),
                false,
                ""
                );
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(int userId) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
            "DELETE FROM public.user_info WHERE user_id=?"
            );

            statement.setInt(1, userId);

            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int userId, UserDetails userDetails) {
        delete(userId);
        add(userId, userDetails);
    }
}
