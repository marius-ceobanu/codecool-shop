package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.implementation.AccountDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.Account;

import java.io.IOException;
import java.sql.*;

public class AccountDaoJdbc implements AccountDao {

    private static AccountDaoJdbc instance = null;

    public static AccountDaoJdbc getInstance() {
        if (instance == null) {
            instance = new AccountDaoJdbc();
        }
        return instance;
    }

    private AccountDaoJdbc() {
    }

    @Override
    public void register(Account account) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO public.user_account (name, email, password) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, account.getName());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getPassword());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                account.setId(resultSet.getInt("id"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getById(int id) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.user_account WHERE id=?"
            );

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Account getByEmail(String email) {
        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.user_account WHERE email=?"
            );

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
