package com.codecool.shop.dao;

import com.codecool.shop.model.UserDetails;

public interface UserDetailsDao {

    void add(int userId,UserDetails userDetails);
    UserDetails getById(int id);
    void delete(int userId);
    void update(int userId, UserDetails userDetails);
}
