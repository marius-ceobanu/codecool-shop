package com.codecool.shop.model;

public class Supplier extends BaseModel {

    public Supplier(String name, String description) {
        super(name, description);
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Supplier)) {
            return false;
        }

        Supplier supplier = (Supplier) obj;
        return supplier.getId() == this.getId() && supplier.getDescription().equals(this.getDescription()) && supplier.getName().equals(this.getName());
    }
}