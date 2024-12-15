package com.coffeeshop.backend.repository;

import com.coffeeshop.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = """
       SELECT cst.id AS customerId, cst.name AS name, cst.profile_image AS profileImage, COUNT(ord.product_id) as countOrders
       FROM customer cst
                JOIN orders ord ON ord.customer_id = cst.id
       GROUP BY cst.id, cst.name, cst.profile_image
       ORDER BY countOrders DESC
       LIMIT 5
    """, nativeQuery = true)
    public List<Object[]> findLoyalCustomers();
}
