package com.codecool.shop.controller.api;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/cart/*"})
public class CartApiServlet extends HttpServlet {

    CartDao cartDataStore = CartDaoMem.getInstance();
    ProductDao productDataStore = ProductDaoMem.getInstance();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestData data = mapper.readValue(req.getReader(), RequestData.class);

        try {
            switch (req.getPathInfo()) {
                case "/add":
                    cartDataStore.add(
                            0, // TODO Replace with actual userId
                            productDataStore.find(data.getId()),
                            data.getQuantity()
                    );
                    break;
                case "/decrease":
                    cartDataStore.removeCount(
                            0, // TODO Replace with actual userId
                            productDataStore.find(data.getId()),
                            data.getQuantity()
                    );
                    break;
                case "/remove":
                    cartDataStore.remove(
                            0, // TODO Replace with actual userId
                            productDataStore.find(data.getId())
                    );
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(mapper.writeValueAsString(cartDataStore.find(0))); // TODO Replace with actual userId
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
