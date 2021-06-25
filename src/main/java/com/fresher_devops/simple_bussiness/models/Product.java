package com.fresher_devops.simple_bussiness.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String productName;
    private float price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ReceiptDetail> recepitDetail;

    public Product() {
    }

    public Product(Long id, String productName, float price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }

    public Product(String productName, float price) {
        this.productName = productName;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id= " + id + ", name='" + productName + '\'' + ", price='" + price + '\'' + '}';
    }
}
