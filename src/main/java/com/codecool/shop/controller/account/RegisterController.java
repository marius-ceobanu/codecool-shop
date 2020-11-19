package com.codecool.shop.controller.account;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.AccountDao;
import com.codecool.shop.dao.UserDetailsDao;
import com.codecool.shop.manager.DaoManager;
import com.codecool.shop.manager.MailManager;
import com.codecool.shop.model.Account;
import com.codecool.shop.model.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/account/register"})
public class RegisterController extends HttpServlet {
    Map<String, String> errors = new HashMap<>();

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AccountDao accountDataStore = DaoManager.getInstance().getAccountDao();
    private final UserDetailsDao userDetailsDao = DaoManager.getInstance().getUserDetailsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("errors", errors);
        engine.process("account/register.html", context, resp.getWriter());
        errors.clear();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (accountDataStore.getByEmail(email) == null) {
            String encodedPassword = passwordEncoder.encode(password);
            Account account = new Account(name, email, encodedPassword);
            UserDetails userDetails = new UserDetails(name, "", email, "", "", "", "", false, "");
            int id = accountDataStore.register(account);
            userDetailsDao.add(id, userDetails);

            req.getSession().setAttribute("account", account);

            MailManager.getInstance().sendWelcomeMail(account);

            resp.sendRedirect("/");
        } else {
            errors.put("email", "Email already registered!");
            resp.sendRedirect("/account/register");
        }
    }
}
