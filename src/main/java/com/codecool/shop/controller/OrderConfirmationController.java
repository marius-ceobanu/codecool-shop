package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.memory.OrderDaoMem;
import com.codecool.shop.manager.DaoManager;
import com.codecool.shop.model.Account;
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

    private final OrderDao orderDao = DaoManager.getInstance().getOrderDao();
    private final CartDao cartDataStore = DaoManager.getInstance().getCartDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account = (Account) req.getSession().getAttribute("account");

        if (account != null) {
//            Order order = orderDao.getOrder(account.getId());
//            orderDao.deleteOrder(account.getId());
            req.getSession().removeAttribute("details");
            cartDataStore.delete(account.getId());

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());

            engine.process("order_confirmation/index.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/account/register"); // TODO Change to login
        }
    }
}
