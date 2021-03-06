package com.codecool.shop.model;

import java.util.Currency;
import java.util.Objects;

public class Product extends BaseModel {

    private float defaultPrice;
    private Currency defaultCurrency;
    private int categoryId;
    private int supplierId;

    public Product(String name, float defaultPrice, String currencyString, String description, int categoryId, int supplierId) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.categoryId = categoryId;
        this.supplierId = supplierId;
    }

    public Product(int id, String name, float defaultPrice, String currencyString, String description, int categoryId, int supplierId) {
        super(id, name, description);
        this.setPrice(defaultPrice, currencyString);
        this.categoryId = categoryId;
        this.supplierId = supplierId;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.categoryId,
                this.supplierId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                name.equals(product.name) &&
                description.equals(product.description) &&
                Float.compare(product.defaultPrice, defaultPrice) == 0 &&
                categoryId == product.categoryId &&
                supplierId == product.supplierId &&
                Objects.equals(defaultCurrency, product.defaultCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, defaultPrice, defaultCurrency, categoryId, supplierId);
    }
}
