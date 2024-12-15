package com.coffeeshop.backend.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustomerProductId implements Serializable {
    private Long customerId;
    private Long productId;

    public CustomerProductId() {
    }

    public CustomerProductId(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerProductId that = (CustomerProductId) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId);
    }
}
