package com.codecool.shop.controller.account;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.UserDetailsDao;
import com.codecool.shop.manager.DaoManager;
import com.codecool.shop.model.Account;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.UserDetails;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/account/user"})
public class UserInfoController extends HttpServlet {
    private final OrderDao orderDataStore = DaoManager.getInstance().getOrderDao();
    private final UserDetailsDao userDetailsDao = DaoManager.getInstance().getUserDetailsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");

        if (account != null) {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            UserDetails userDetails = userDetailsDao.getById(account.getId());
            List<Order> orders = orderDataStore.getByUser(account.getId());
            context.setVariable("userDetails", userDetails);
            context.setVariable("orders", orders);
            engine.process("account/user_info.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/account/register");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        WebContext context = new WebContext(req, resp, req.getServletContext());

//        String fullName = context.getRequest().getParameter("name");
        String mobile = context.getRequest().getParameter("mobile");
//        String email = context.getRequest().getParameter("email");
        String address = context.getRequest().getParameter("address");
        String city = context.getRequest().getParameter("city");
        String county = context.getRequest().getParameter("county");
        String zipCode = context.getRequest().getParameter("zip");

        userDetailsDao.update(account.getId(), new UserDetails(account.getName(), mobile, account.getEmail(), address, city, county, zipCode, false, ""));
        resp.sendRedirect("/account/user");
    }
}
