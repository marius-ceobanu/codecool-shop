package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMemTest {
    private SupplierDao supplierDao = null;
    private final Supplier testSupplier = new Supplier("supplier a", "test supplier a");
    private final Supplier testSupplier2 = new Supplier("supplier b", "test supplier b");
    private final List<Supplier> testData = new ArrayList<>();

    protected void setUpEmpty() {
        supplierDao = new SupplierDaoMem(); // Modify DAO instance
    }

    protected void setUp() {
        testSupplier.setId(1);
        testSupplier2.setId(2);
        testData.add(testSupplier);
        testData.add(testSupplier2);
        supplierDao = new SupplierDaoMem(testData); // Modify DAO instance
    }

    @Test
    void getAll_alreadyContainingSuppliers_returnAllContainingSuppliers() {
        setUp();
        Assertions.assertEquals(2, supplierDao.getAll().size());
    }

    @Test
    void add_newSupplier_returnInsertedSupplier() {
        setUpEmpty();
        supplierDao.add(testSupplier);
        Assertions.assertEquals(testSupplier, supplierDao.getAll().get(0));
    }

    @Test
    void find_alreadyContainingSupplierWithSpecifiedId_returnSupplierWithSpecifiedId() {
        setUp();
        Assertions.assertEquals(testSupplier, supplierDao.find(1));
    }

    @Test
    void remove_alreadyContainingSupplierWithSpecifiedId_returnSuppliersWithoutOneWithSpecifiedId () {
        setUp();
        supplierDao.remove(2);
        Assertions.assertFalse(supplierDao.getAll().contains(testSupplier2));
    }
}
