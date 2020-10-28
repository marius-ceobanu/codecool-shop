package com.codecool.shop.controller;

import com.codecool.shop.model.Order;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailController {

    private static final String USER_NAME = "e2891148cc6876";
    private static final String PASSWORD = "8233eb4dc1514c";

    private static final String FROM_MAIL = "noreply@ccshop.com";

    private static MailController instance = null;

    public static MailController getInstance() {
        if (instance == null) {
            instance = new MailController();
        }
        return instance;
    }

    private final Session session;

    MailController() {
//        Properties properties = System.getProperties();
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.mailtrap.io");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });
    }

    public void sendConfirmationMail(Order order) {
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM_MAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(order.getUserDetails().getEmail()));
            message.setSubject("Order Confirmation");

            message.setText(String.format("Hello %s!%n%nThank you for your order to Codecool Shop srl tm enterprises from %td-%<tm-%<tY %<tH:%<tM!%n%nYour order should arrive in about 1 to 97 years.",
                    order.getUserDetails().getFullName(),
                    order.getOrderStart()
            ));

            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
