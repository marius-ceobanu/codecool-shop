package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Payment;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout/payment"})
public class PaymentController extends HttpServlet {
    private final CartDao cartDataStore = CartDaoMem.getInstance();
    private final OrderDao orderDao = OrderDaoMem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Order order = orderDao.getOrder(0); // TODO Replace with actual userId
        if(order.getUserDetails().getPaymentMethod().equals("card")) {
            Payment payment = new Payment(
                context.getRequest().getParameter("cardname"),
                context.getRequest().getParameter("cardnumber"),
                context.getRequest().getParameter("expmonth"),
                context.getRequest().getParameter("expyear"),
                context.getRequest().getParameter("cvv")
            );
            order.setPayment(payment);
        }
        JsonExporter.getInstance().exportOrder(order);
        MailController.getInstance().sendConfirmationMail(order);
        resp.sendRedirect("/");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Order order = orderDao.getOrder(0); // TODO Replace with actual userId

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cart", cartDataStore.find(0)); // TODO Replace with actual userId
        context.setVariable("method", order.getUserDetails().getPaymentMethod());
        engine.process("payment/index.html", context, resp.getWriter());
    }
}
