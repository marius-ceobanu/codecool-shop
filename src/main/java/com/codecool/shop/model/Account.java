package com.codecool.shop.model;

import java.util.Objects;

public class Account {
    int id;
    String name;
    String email;
    String password;

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Account(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                name.equals(account.name) &&
                email.equals(account.email) &&
                password.equals(account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }
}
