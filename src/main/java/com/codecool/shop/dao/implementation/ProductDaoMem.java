package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private final List<Product> data = new ArrayList<>();

    @Override
    public void add(Product product) {
        product.setId(data.size() + 1);
        data.add(product);
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    @Override
    public List<Product> getBySupplier(int supplierId) {
        return data.stream().filter(t -> t.getSupplierId() == supplierId).collect(Collectors.toList());
    }

    @Override
    public List<Product> getByCategory(int categoryId) {
        return data.stream().filter(t -> t.getCategoryId() == categoryId).collect(Collectors.toList());
    }
}
