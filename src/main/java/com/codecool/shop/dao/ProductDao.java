package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    void add(Product product);
    Product find(int id);
    void remove(int id);

    List<Product> getAll();
    List<Product> getBySupplier(int supplierId);
    List<Product> getByCategory(int categoryId);

}
