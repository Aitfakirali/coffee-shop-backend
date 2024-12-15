package com.coffeeshop.backend.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private Double price;

    private Integer points;

    private String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<CustomerProduct> customerProducts = new HashSet<>();

    public Product() {
    }


    public Product(String title, String description, Double price, Integer points, String imageUrl, Set<CustomerProduct> customerProducts) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.points = points;
        this.imageUrl = imageUrl;
        this.customerProducts = customerProducts;
    }

    public Set<CustomerProduct> getCustomerProducts() {
        return customerProducts;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getPoints() {
        return points;
    }

}
