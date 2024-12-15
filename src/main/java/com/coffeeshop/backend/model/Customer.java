package com.coffeeshop.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String telephone;

    private String profileImage;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<CustomerReward> customerRewards = new HashSet<>();


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<CustomerProduct> customerProducts = new HashSet<>();

    public Customer() {
    }

    public Customer(String name, String telephone, String profileImage, Set<CustomerReward> customerRewards, Set<CustomerProduct> customerProducts) {
        this.name = name;
        this.telephone = telephone;
        this.profileImage = profileImage;
        this.customerRewards = customerRewards;
        this.customerProducts = customerProducts;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public Set<CustomerReward> getCustomerRewards() {
        return customerRewards;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setCustomerRewards(Set<CustomerReward> customerRewards) {
        this.customerRewards = customerRewards;
    }

    public void setCustomerProducts(Set<CustomerProduct> customerProducts) {
        this.customerProducts = customerProducts;
    }

    public Set<CustomerProduct> getCustomerProducts() {
        return customerProducts;
    }
}
