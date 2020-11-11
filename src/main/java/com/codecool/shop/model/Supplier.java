package com.codecool.shop.model;

public class Supplier extends BaseModel {

    public Supplier(String name, String description) {
        super(name);
    }

    public Supplier(int id, String name, String description) {
        super(id, name, description);
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}