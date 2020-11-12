package com.codecool.shop.manager;

import com.codecool.shop.model.Account;
import com.codecool.shop.model.Order;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailManager {

    private static final String USER_NAME = "fa4acdf7d0038f";
    private static final String PASSWORD = "15e92d46c99eac";

    private static final String FROM_MAIL = "noreply@ccshop.com";

    private static MailManager instance = null;

    public static MailManager getInstance() {
        if (instance == null) {
            instance = new MailManager();
        }
        return instance;
    }

    private final Session session;

    MailManager() {
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
            e.printStackTrace();
        }
    }

    public void sendWelcomeMail(Account account) {
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM_MAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(account.getEmail()));
            message.setSubject("Welcome to Codecoolshop!");

            message.setText(String.format("Hello %s!%nWelcome to Codecoolshop tm, the only shop in the universe to sell the latest products* and guarantee delivery in the shortest time**.%nWe are happy to have you hare and good shopping!%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n*: The products may or may not be real and may or may not physically exist%n**: Shortest time possible could include never",
                    account.getName()
            ));

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
