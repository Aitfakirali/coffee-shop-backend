package com.coffeeshop.backend.services;


import com.coffeeshop.backend.model.Product;
import com.coffeeshop.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
