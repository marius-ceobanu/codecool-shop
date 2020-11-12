package com.codecool.shop.controller.account;

import com.codecool.shop.dao.AccountDao;
import com.codecool.shop.manager.DaoManager;
import com.codecool.shop.model.Account;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/account/login"})
public class LoginController extends HttpServlet {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AccountDao accountDataStore = DaoManager.getInstance().getAccountDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (accountDataStore.getByEmail(email) != null) {
            Account account = accountDataStore.getByEmail(email);
            if(passwordEncoder.matches(password, account.getPassword())) {
                req.getSession().setAttribute("account", account);
            }
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/");
        }
    }
}
