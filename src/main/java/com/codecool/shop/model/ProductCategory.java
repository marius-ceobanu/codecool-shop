package com.codecool.shop.model;

public class ProductCategory extends BaseModel {
    private String department;

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
    }

    public ProductCategory(int id, String name, String department, String description) {
        super(id, name, description);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductCategory)) {
            return false;
        }

        ProductCategory productCategory = (ProductCategory) obj;
        return productCategory.getId() == this.getId() && productCategory.getDepartment().equals(this.getDepartment()) && productCategory.getName().equals(this.getName());
    }
}