package com.coffeeshop.backend.services;


import com.coffeeshop.backend.model.Product;
import com.coffeeshop.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


    public List<Map<String, Object>> productsSalesCurrentMonth(){
        List<Object[]> productSalesResults = productRepository.productSales();
        return productSalesResults.stream().map(result -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date", result[0]);
            map.put("sales", result[1]);
            return map;
        }).collect(Collectors.toList());
    }
}
