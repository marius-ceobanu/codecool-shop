package com.codecool.shop.config;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.jdbc.*;
import com.codecool.shop.dao.implementation.memory.*;
import com.codecool.shop.manager.DaoManager;
import com.codecool.shop.manager.DatabaseManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        DatabaseManager.setProperties("connection.properties");

        try {
            DatabaseManager.connect().close();
            System.out.println("connection ok");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        DaoManager daoManager = DaoManager.getInstance();

        daoManager.setProductDao(ProductDaoJdbc.getInstance());
        daoManager.setProductCategoryDao(ProductCategoryDaoJdbc.getInstance());
        daoManager.setSupplierDao(SupplierDaoJdbc.getInstance());
        daoManager.setCartDao(CartDaoJdbc.getInstance());

//        daoManager.setProductDao(ProductDaoMem.getInstance());
//        daoManager.setProductCategoryDao(ProductCategoryDaoMem.getInstance());
//        daoManager.setSupplierDao(SupplierDaoMem.getInstance());
//        daoManager.setCartDao(CartDaoMem.getInstance());

        daoManager.setAccountDao(AccountDaoJdbc.getInstance());

        ProductDao productDataStore = daoManager.getProductDao();
        ProductCategoryDao productCategoryDataStore = daoManager.getProductCategoryDao();
        SupplierDao supplierDataStore = daoManager.getSupplierDao();

        CartDao cartDataStore = daoManager.getCartDao();

//        //setting up a new supplier
//        Supplier amazon = new Supplier("Amazon", "Digital content and services");
//        supplierDataStore.add(amazon);
//        Supplier lenovo = new Supplier("Lenovo", "Computers");
//        supplierDataStore.add(lenovo);
//        Supplier apple = new Supplier("Apple", "Phones");
//        supplierDataStore.add(apple);
//        Supplier sony = new Supplier("Sony", "Hardware");
//        supplierDataStore.add(sony);
//        Supplier microsoft = new Supplier("Microsoft", "Hardware/Software");
//        supplierDataStore.add(microsoft);
//        Supplier razer = new Supplier("Razer", "Gaming hardware");
//        supplierDataStore.add(razer);
//
//        //setting up a new product category
//        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
//        productCategoryDataStore.add(tablet);
//        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A smart phone, commonly shortened to phone, is a thin, flat mobile phone with a touchscreen display.");
//        productCategoryDataStore.add(phone);
//        ProductCategory gameConsole = new ProductCategory("Game console", "Hardware", "A smart gaming console, piece of hardware dedicated for gaming only that connects to your smart tv");
//        productCategoryDataStore.add(gameConsole);
//        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A laptop is a smart computer, that it gives you mobility");
//        productCategoryDataStore.add(laptop);
//        ProductCategory mouse = new ProductCategory("Mouse", "Hardware", "A mouse is a device that helps you control your desktop applications and background.");
//        productCategoryDataStore.add(mouse);
//
//        //setting up products and printing it
//        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet.getId(), amazon.getId()));
//        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet.getId(), lenovo.getId()));
//        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet.getId(), amazon.getId()));
//        productDataStore.add(new Product("Iphone 12", 230, "USD", "Apple's latest Iphone 12 smart phone is a great value for media consumption.", phone.getId(), apple.getId()));
//        productDataStore.add(new Product("Playstation 5", 530, "USD", "Sony launches the brand new gaming console, that support 8K", gameConsole.getId(), sony.getId()));
//        productDataStore.add(new Product("Xbox One", 560, "USD", "Xbox launches the brand new gaming console, that support 8K", gameConsole.getId(), microsoft.getId()));
//        productDataStore.add(new Product("Razer Blade 15", 760, "USD", "Perfect laptop for gaming, stronger than ever", laptop.getId(), razer.getId()));
//        productDataStore.add(new Product("MacBook Pro 15", 1650, "USD", "Apple laptop, newest apple technology in a 15 inch case", laptop.getId(), apple.getId()));
//        productDataStore.add(new Product("Razer gaming mouse", 76, "USD", "Best mouse for gaming, Razer guarantees highest accuracy for shooting your opponents, Yeeei, Die!", mouse.getId(), razer.getId()));

//        cartDataStore.add(0, productDataStore.find(1), 1);
//        cartDataStore.add(0, productDataStore.find(2), 1);
//        cartDataStore.add(0, productDataStore.find(3), 3);
//        cartDataStore.add(0, productDataStore.find(2), 1);
    }
}
