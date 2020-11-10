package com.codecool.shop.manager;

import com.codecool.shop.model.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonManager {

    private static final String SAVE_PATH = "orders/";

    private static JsonManager instance = null;

    public static JsonManager getInstance() {
        if (instance == null) {
            instance = new JsonManager();
        }
        return instance;
    }

    public JsonManager() {
        this.mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private final ObjectMapper mapper;

    public void exportOrder(Order order) {
        try {
            if (!Files.exists(Path.of(SAVE_PATH))) {
                if (!new File(SAVE_PATH).mkdir()) {
                    System.out.println("Failed to create saves folder.");
                    return;
                }
            }
            String name = String.format("%s %td-%<tm-%<tY %<tH-%<tM-%<tS-%<tL",
                    order.getUserDetails().getFullName(),
                    order.getOrderStart()
            );
            mapper.writeValue(new File(String.format("%s%s.json", SAVE_PATH, name)), order);
        } catch (IOException e) {
            System.out.println("Failed to save order!");
            e.printStackTrace();
        }
    }
}
