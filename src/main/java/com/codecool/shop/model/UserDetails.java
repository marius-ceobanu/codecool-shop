package com.codecool.shop.model;

public class UserDetails {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private String city;
    private String county;
    private String zipCode;
    private boolean billingSameAsShipping;
    private String paymentMethod;

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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean getBillingSameAsShipping() {
        return billingSameAsShipping;
    }

    public void setBillingSameAsShipping(boolean billingSameAsShipping) {
        this.billingSameAsShipping = billingSameAsShipping;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
