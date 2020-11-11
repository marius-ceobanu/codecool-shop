package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Account;

public interface AccountDao {

    void register(Account account);
    Account getById(int id);
    Account getByEmail(String email);
}
