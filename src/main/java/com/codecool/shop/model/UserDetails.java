package com.codecool.shop.model;

public class UserDetails {
    private final String fullName;
    private final String phoneNumber;
    private final String email;
    private final String address;
    private final String city;
    private final String county;
    private final String zipCode;
    boolean billingSameAsShipping;
    private final String paymentMethod;

    public UserDetails(String fullName, String phoneNumber, String email, String address, String city, String county, String zipCode, boolean billingSameAsShipping, String paymentMethod) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.county =county;
        this.zipCode = zipCode;
        this.billingSameAsShipping = billingSameAsShipping;
        this.paymentMethod = paymentMethod;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCounty() {
        return county;
    }

    public String getCity() {
        return city;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
