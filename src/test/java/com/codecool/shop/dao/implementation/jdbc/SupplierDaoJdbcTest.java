package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.manager.DatabaseManager;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.*;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SupplierDaoJdbcTest {
    private SupplierDao supplierDao = null;

    @BeforeEach
    protected void setUp() {
        supplierDao = SupplierDaoJdbc.getInstance();
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
    void getAll_alreadyContainingSuppliers_returnAllContainingSuppliers() {
        Assertions.assertEquals(2, supplierDao.getAll().size());
    }

    @Test
    void add_newSupplier_returnInsertedSupplier() {
        Supplier tested = new Supplier("test1", "test1");
        supplierDao.add(tested);
        tested.setId(3);
        Assertions.assertTrue(tested.equals(supplierDao.getAll().get(2)));
    }

    @Test
    void find_alreadyContainingSupplierWithSpecifiedId_returnSupplierWithSpecifiedId() {
        Supplier tested = new Supplier(1, "supplier a", "test supplier a");
        Assertions.assertTrue(tested.equals(supplierDao.find(1)));
    }

    @Test
    void remove_alreadyContainingSupplierWithSpecifiedId_returnSuppliersWithoutOneWithSpecifiedId () {
        Supplier tested = new Supplier(1, "supplier a", "test supplier a");
        supplierDao.remove(1);
        Assertions.assertFalse(supplierDao.getAll().contains(tested));
    }
}
