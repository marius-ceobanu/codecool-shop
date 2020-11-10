package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.manager.DaoManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    ProductDao productDataStore = DaoManager.getInstance().getProductDao();
    ProductCategoryDao productCategoryDataStore = DaoManager.getInstance().getProductCategoryDao();
    SupplierDao supplierDataStore = DaoManager.getInstance().getSupplierDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", "Select All");
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        context.setVariable("products", productDataStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        int category = Integer.parseInt(String.valueOf(context.getRequest().getParameter("category").charAt(0)));
        int id = Integer.parseInt(context.getRequest().getParameter("category").substring(1));
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        if(category==0) {
            context.setVariable("category", "Select All");
            context.setVariable("products", productDataStore.getAll());
        } else if(category==1) {
            context.setVariable("category", productCategoryDataStore.find(id).getName());
            context.setVariable("products", productDataStore.getByCategory(id));
        } else {
            context.setVariable("category", supplierDataStore.find(id).getName());
            context.setVariable("products", productDataStore.getBySupplier(id));
        }
        engine.process("product/index.html", context, resp.getWriter());
    }

}
