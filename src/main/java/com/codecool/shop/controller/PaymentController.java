package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.memory.OrderDaoMem;
import com.codecool.shop.manager.DaoManager;
import com.codecool.shop.manager.JsonManager;
import com.codecool.shop.manager.MailManager;
import com.codecool.shop.model.Account;
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
    private final CartDao cartDataStore = DaoManager.getInstance().getCartDao();
    private final OrderDao orderDao = OrderDaoMem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Account account = (Account) req.getSession().getAttribute("account");

        Order order = orderDao.getOrder(account.getId());
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
        JsonManager.getInstance().exportOrder(order);
        MailManager.getInstance().sendConfirmationMail(order);
        resp.sendRedirect("/checkout/payment/confirmation");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account = (Account) req.getSession().getAttribute("account");

        if (account != null) {
            Order order = orderDao.getOrder(account.getId());

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());

            context.setVariable("cart", cartDataStore.find(account.getId()));
            context.setVariable("method", order.getUserDetails().getPaymentMethod());
            engine.process("payment/index.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/account/register"); // TODO Change to login
        }
    }
}
