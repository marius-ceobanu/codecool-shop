package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.*;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductDaoJdbcTest {
    private ProductDao productDao = null;

    @BeforeEach
    protected void setUp() {
        productDao = new ProductDaoJdbc();
        DatabaseManager.setProperties("connection_test_db.properties");
        try (Connection connection = DatabaseManager.connect()) {
            ScriptRunner sr = new ScriptRunner(connection);
            Reader initDb = new BufferedReader(new FileReader("src/main/sql/init_db.sql"));
            Reader populateDb = new BufferedReader(new FileReader("src/main/sql/init_test_data.sql"));
            sr.runScript(initDb);
            sr.runScript(populateDb);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAll_alreadyContainingProducts_returnAllContainingProducts() {
        Assertions.assertEquals(2, productDao.getAll().size());
    }

    @Test
    void add_newProduct_returnInsertedProduct() {
        Product tested = new Product("testAdd", 3.56f, "USD", "testedAdd", 2, 2);
        productDao.add(tested);
        tested.setId(3);
        Assertions.assertTrue(tested.equals(productDao.getAll().get(2)));
    }

    @Test
    void find_alreadyContainingProductWithSpecifiedId_returnProductWithSpecifiedId() {
        Product tested = new Product(2, "Product b", 49.9f, "USD", "test2", 2, 2);
        Assertions.assertTrue(tested.equals(productDao.find(2)));
    }

    @Test
    void remove_alreadyContainingProductWithSpecifiedId_returnProductsWithoutOneWithSpecifiedId () {
        Product tested = new Product(2, "Product b", 49.9f, "USD", "test2", 2, 2);
        productDao.remove(2);
        Assertions.assertFalse(productDao.getAll().contains(tested));
    }

    @Test
    void getBySupplier_alreadyContainingProductWithSpecifiedSupplierId_returnProductWithSpecifiedSupplierId() {
        Product tested = new Product(2, "Product b", 49.9f, "USD", "test2", 2, 2);
        Assertions.assertTrue(productDao.getBySupplier(2).contains(tested));
    }

    @Test
    void getByCategory_alreadyContainingProductWithSpecifiedCategoryId_returnProductWithSpecifiedCategoryId() {
        Product tested = new Product(2, "Product b", 49.9f, "USD", "test2", 2, 2);
        Assertions.assertTrue(productDao.getByCategory(2).contains(tested));
    }
}
