package com.codecool.shop.controller.api;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.manager.CartManager;
import com.codecool.shop.manager.DaoManager;
import com.codecool.shop.model.Account;
import com.codecool.shop.model.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/cart/*"})
public class CartApiServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();

    private final CartDao cartDataStore = DaoManager.getInstance().getCartDao();
    private final ProductDao productDataStore = DaoManager.getInstance().getProductDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestData data = mapper.readValue(req.getReader(), RequestData.class);

        Account account = (Account) req.getSession().getAttribute("account");

        if (account != null) {
            try {
                switch (req.getPathInfo()) {
                    case "/add":
                        CartManager.addProduct(
                                account.getId(),
                                productDataStore.find(data.getId()),
                                data.getQuantity()
                        );
                        break;
                    case "/decrease":
                        CartManager.decreaseQuantity(
                                account.getId(),
                                productDataStore.find(data.getId()),
                                data.getQuantity()
                        );
                        break;
                    case "/remove":
                        CartManager.removeProduct(
                                account.getId(),
                                productDataStore.find(data.getId())
                        );
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Account account = (Account) req.getSession().getAttribute("account");

        if (account != null) {
            Cart cart = cartDataStore.find(account.getId());
            resp.getWriter().print(mapper.writeValueAsString(cart));
        }
        resp.getWriter().flush();
    }

    private static class RequestData {
        private int id;
        private int quantity;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
