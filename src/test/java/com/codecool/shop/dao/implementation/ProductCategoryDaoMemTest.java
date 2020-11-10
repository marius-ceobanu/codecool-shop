package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMemTest {
    private ProductCategoryDao productCategoryDao = null;
    private final ProductCategory testProductCategory = new ProductCategory("category a", "dep a", "test category a");
    private final ProductCategory testProductCategory2 = new ProductCategory("category b", "dep b", "test category b");
    private final List<ProductCategory> testData = new ArrayList<>();

    protected void setUpEmpty() {
        productCategoryDao = new ProductCategoryDaoMem(); // Modify DAO instance
    }

    protected void setUp() {
        testProductCategory.setId(1);
        testProductCategory2.setId(2);
        testData.add(testProductCategory);
        testData.add(testProductCategory2);
        productCategoryDao = new ProductCategoryDaoMem(testData); // Modify DAO instance
    }

    @Test
    void getAll_alreadyContainingProductCategories_returnAllContainingProductCategories() {
        setUp();
        Assertions.assertEquals(2, productCategoryDao.getAll().size());
    }

    @Test
    void add_newProductCategory_returnInsertedProductCategory() {
        setUpEmpty();
        productCategoryDao.add(testProductCategory);
        Assertions.assertEquals(testProductCategory, productCategoryDao.getAll().get(0));
    }

    @Test
    void find_alreadyContainingProductCategoryWithSpecifiedId_returnProductCategoryWithSpecifiedId() {
        setUp();
        Assertions.assertEquals(testProductCategory, productCategoryDao.find(1));
    }

    @Test
    void remove_alreadyContainingProductCategoryWithSpecifiedId_returnProductCategoriesWithoutOneWithSpecifiedId () {
        setUp();
        productCategoryDao.remove(2);
        Assertions.assertFalse(productCategoryDao.getAll().contains(testProductCategory2));
    }
}
