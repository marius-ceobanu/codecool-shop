package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.memory.OrderDaoMem;
import com.codecool.shop.manager.DaoManager;
import com.codecool.shop.model.Account;
import com.codecool.shop.model.UserDetails;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    private final OrderDao orderDataStore = OrderDaoMem.getInstance();
    private final CartDao cartDataStore = DaoManager.getInstance().getCartDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Account account = (Account) req.getSession().getAttribute("account");
        context.setVariable("cart", cartDataStore.find(account.getId()));

        engine.process("checkout/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());
        String fullName = context.getRequest().getParameter("name");
        String mobile = context.getRequest().getParameter("mobile");
        String email = context.getRequest().getParameter("email");
        String address = context.getRequest().getParameter("address");
        String city = context.getRequest().getParameter("city");
        String county = context.getRequest().getParameter("county");
        String zipCode = context.getRequest().getParameter("zip");
        String paymentMethod = context.getRequest().getParameter("payment");
        boolean sameAddress = context.getRequest().getParameter("sameadr").equals("checked");

        UserDetails userDetails = new UserDetails(fullName, mobile, email, address, city, county, zipCode, sameAddress, paymentMethod);

        Account account = (Account) req.getSession().getAttribute("account");

        orderDataStore.addUserDetails(account.getId(), userDetails);
        orderDataStore.addCart(account.getId(), cartDataStore.find(account.getId()));

        resp.sendRedirect("/checkout/payment");
    }
}
