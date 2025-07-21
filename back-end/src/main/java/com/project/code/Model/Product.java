package com.project.code.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
public class Product {

// 1. Add 'id' field:
//    - Type: private long 
//    - This field will be auto-incremented.
//    - Use @Id to mark it as the primary key.
//    - Use @GeneratedValue(strategy = GenerationType.IDENTITY) to auto-increment it.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
// 2. Add 'name' field:
//    - Type: private String
//    - This field cannot be empty, use the @NotNull annotation to enforce this rule.
    @NotNull
    private String name;
// 3. Add 'category' field:
//    - Type: private String
//    - This field cannot be empty, use the @NotNull annotation to enforce this rule.
    @NotNull
    private String category;
// 4. Add 'price' field:
//    - Type: private Double
//    - This field cannot be empty, use the @NotNull annotation to enforce this rule.
    @NotNull
    private Double price;
// 5. Add 'sku' field:
//    - Type: private String
//    - This field cannot be empty, must be unique, use the @NotNull annotation to enforce this rule.
//    - Use the @Table annotation with uniqueConstraints to ensure the 'sku' column is unique.

//    Example: @Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = "sku"))
    @NotNull
    private String sku;
// 6. Add relationships:
//    - **Inventory**: A product can have multiple inventory entries.
//    - Use @OneToMany(mappedBy = "product") to reflect the one-to-many relationship with Inventory.
//    - Use @JsonManagedReference("inventory-product") to manage bidirectional relationships and avoid circular references.
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference("inventory-product")
    private List<Inventory> inventoryProductList;
// 7. Add @Entity annotation:
//    - Use @Entity above the class name to mark it as a JPA entity.

// 8. Add Getters and Setters:
//    - Add getter and setter methods for all fields (id, name, category, price, sku).
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public List<Inventory> getInventoryProductList() {
        return inventoryList;
    }
    public void setInventoryList(List<Inventory> inventoryProductList) {
        this.inventoryProductList = inventoryProductList;
    }
}


