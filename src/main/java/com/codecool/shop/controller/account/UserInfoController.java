package com.codecool.shop.controller.account;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Account;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/account/user"})
public class UserInfoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");

        if (account != null) {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            engine.process("account/user_info.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/account/register");
        }
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
    }
}
