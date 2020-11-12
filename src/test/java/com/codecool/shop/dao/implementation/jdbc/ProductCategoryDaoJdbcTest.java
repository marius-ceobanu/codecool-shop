package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.*;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductCategoryDaoJdbcTest {
    private ProductCategoryDao productCategoryDao = null;

    @BeforeEach
    protected void setUp() {
        productCategoryDao = ProductCategoryDaoJdbc.getInstance();
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
    void getAll_alreadyContainingProductCategories_returnAllContainingProductCategories() {
        Assertions.assertEquals(2, productCategoryDao.getAll().size());
    }

    @Test
    void add_newProductCategory_returnInsertedProductCategory() {
        ProductCategory tested = new ProductCategory("test1", "test1", "test1");
        productCategoryDao.add(tested);
        tested.setId(3);
        Assertions.assertTrue(tested.equals(productCategoryDao.getAll().get(2)));
    }

    @Test
    void find_alreadyContainingProductCategoryWithSpecifiedId_returnProductCategoryWithSpecifiedId() {
        ProductCategory tested = new ProductCategory(1, "category a", "test category a", "dep a");
        Assertions.assertTrue(tested.equals(productCategoryDao.find(1)));
    }

    @Test
    void remove_alreadyContainingProductCategoryWithSpecifiedId_returnProductCategoriesWithoutOneWithSpecifiedId () {
        ProductCategory tested = new ProductCategory(1, "category a", "test category a", "dep a");
        productCategoryDao.remove(1);
        Assertions.assertFalse(productCategoryDao.getAll().contains(tested));
    }
}
