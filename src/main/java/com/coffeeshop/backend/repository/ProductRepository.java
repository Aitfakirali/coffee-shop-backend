package com.coffeeshop.backend.repository;

import com.coffeeshop.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = """
        SELECT
            DATE(ord.created_at) AS date,
            SUM(pr.price) AS total_sales
        FROM
            orders ord JOIN product pr ON pr.product_id = ord.product_id
        WHERE
            ord.created_at >= DATE_TRUNC('month', CURRENT_DATE)
          AND ord.created_at < DATE_TRUNC('month', CURRENT_DATE) + INTERVAL '1 month'
        GROUP BY DATE(ord.created_at)
        ORDER BY date
        ;
    """, nativeQuery = true)
    public List<Object[]> productSales();
}
