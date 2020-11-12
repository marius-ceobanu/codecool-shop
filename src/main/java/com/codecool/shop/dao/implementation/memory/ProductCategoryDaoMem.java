package com.codecool.shop.dao.implementation.memory;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private static ProductCategoryDaoMem instance = null;

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    private ProductCategoryDaoMem() {
    }

    private List<ProductCategory> data = new ArrayList<>();

    public ProductCategoryDaoMem(List<ProductCategory> data) {
        this.data = data;
    }

    @Override
    public void add(ProductCategory category) {
        category.setId(data.size() + 1);
        data.add(category);
    }

    @Override
    public ProductCategory find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return data;
    }
}
