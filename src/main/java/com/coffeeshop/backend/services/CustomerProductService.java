package com.coffeeshop.backend.services;


import com.coffeeshop.backend.dto.CustomerProductDto;
import com.coffeeshop.backend.model.Customer;
import com.coffeeshop.backend.model.CustomerProduct;
import com.coffeeshop.backend.model.Product;
import com.coffeeshop.backend.repository.CustomerProductRepository;
import com.coffeeshop.backend.repository.CustomerRepository;
import com.coffeeshop.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerProductService {

    private final CustomerProductRepository customerProductRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public CustomerProductService(CustomerProductRepository customerProductRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerProductRepository = customerProductRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public CustomerProduct saveCustomerProduct(CustomerProductDto customerProductDto) {
        Optional<Customer> customerOpt = customerRepository.findById(customerProductDto.customerId());
        if (customerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        Optional<Product> productOpt = productRepository.findById(customerProductDto.productId());
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        Customer customer = customerOpt.get();
        Product product = productOpt.get();

        CustomerProduct customerProduct = new CustomerProduct();
        customerProduct.setProduct(product);
        customerProduct.setCustomer(customer);
        return customerProductRepository.save(customerProduct);
    }
}
