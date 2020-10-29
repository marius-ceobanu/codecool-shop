package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout/payment/confirmation"})
public class OrderConfirmationController extends HttpServlet {
    private final OrderDao orderDao = OrderDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Order order = orderDao.getOrder(0); // TODO Replace with actual userId
        orderDao.deleteOrder(0); // TODO Replace with actual userId

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("order", order); // TODO Replace with actual userId

        engine.process("order_confirmation/index.html", context, resp.getWriter());
    }
}
