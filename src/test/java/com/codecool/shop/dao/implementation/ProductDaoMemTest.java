package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.memory.ProductDaoMem;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

class ProductDaoMemTest {
    private ProductDao productDao = null;
    private final Product testProduct = new Product("Product a", 49.9f, "USD", "test1", 3, 4);
    private final Product testProduct2 = new Product("Product b", 49.9f, "USD", "test2", 5, 6);
    private final List<Product> testData = new ArrayList<>();

    protected void setUpEmpty() {
        productDao = new ProductDaoMem(); // Modify DAO instance
    }

    protected void setUp() {
        testProduct.setId(1);
        testProduct2.setId(2);
        testData.add(testProduct);
        testData.add(testProduct2);
        productDao = new ProductDaoMem(testData); // Modify DAO instance
    }


    @Test
    void getAll_alreadyContainingProducts_returnAllContainingProducts() {
        setUp();
        Assertions.assertEquals(2, productDao.getAll().size());
    }

    @Test
    void addProduct_newProduct_returnInsertedProduct() {
        setUpEmpty();
        productDao.add(testProduct);
        Assertions.assertEquals(testProduct, productDao.getAll().get(0));
    }

    @Test
    void findById_alreadyContainingProductWithSpecifiedId_returnProductWithSpecifiedId() {
        setUp();
        Assertions.assertEquals(testProduct, productDao.find(1));
    }

    @Test
    void removeById_alreadyContainingProductWithSpecifiedId_returnProductsWithoutOneWithSpecifiedId () {
        setUp();
        productDao.remove(2);
        Assertions.assertFalse(productDao.getAll().contains(testProduct2));
    }

    @Test
    void getBySupplier_alreadyContainingProductWithSpecifiedSupplierId_returnProductWithSpecifiedSupplierId() {
        setUp();
        Assertions.assertEquals(testProduct, productDao.getBySupplier(4).get(0));
    }

    @Test
    void getByCategory_alreadyContainingProductWithSpecifiedCategoryId_returnProductWithSpecifiedCategoryId() {
        setUp();
        Assertions.assertEquals(testProduct2, productDao.getByCategory(5).get(0));
    }
}
