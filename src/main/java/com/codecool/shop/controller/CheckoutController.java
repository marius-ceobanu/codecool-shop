package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.memory.CartDaoMem;
import com.codecool.shop.dao.implementation.memory.OrderDaoMem;
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

    private final OrderDao orderDao = OrderDaoMem.getInstance();
    private final CartDao cartDao = CartDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDao cartDataStore = CartDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("cart", cartDataStore.find(0)); // TODO Replace with actual userId

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

        orderDao.addUserDetails(0, userDetails); // TODO Replace with actual userId
        orderDao.addCart(0, cartDao.find(0)); // TODO Replace with actual userId

        resp.sendRedirect("/checkout/payment");
    }
}
