package com.coffeeshop.backend.controller;


import com.coffeeshop.backend.dto.CustomerProductDto;
import com.coffeeshop.backend.dto.CustomerRewardDto;
import com.coffeeshop.backend.model.Customer;
import com.coffeeshop.backend.model.CustomerProduct;
import com.coffeeshop.backend.model.CustomerReward;
import com.coffeeshop.backend.services.CustomerProductService;
import com.coffeeshop.backend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {


    private final CustomerService customerService;
    private final CustomerProductService customerProductService;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerProductService customerProductService) {
        this.customerService = customerService;
        this.customerProductService = customerProductService;
    }

    @GetMapping()
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PostMapping("/award")
    public CustomerReward assignReward(@RequestBody CustomerRewardDto customerReward) {
        return customerService.assignReward(customerReward);
    }

    @PostMapping("/buy-product")
    public CustomerProduct buyProduct(@RequestBody CustomerProductDto customerProductDto) {
        return customerProductService.saveCustomerProduct(customerProductDto);
    }


    @GetMapping("/loyal")
    public List<Map<String, Object>> loyalCustomers() {
        return customerService.findLoyalCustomers();
    }

}
