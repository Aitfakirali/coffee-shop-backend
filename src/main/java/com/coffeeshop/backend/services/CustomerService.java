package com.coffeeshop.backend.services;

import com.coffeeshop.backend.dto.CustomerRewardDto;
import com.coffeeshop.backend.model.Customer;
import com.coffeeshop.backend.model.CustomerReward;
import com.coffeeshop.backend.model.Reward;
import com.coffeeshop.backend.repository.CustomerRepository;
import com.coffeeshop.backend.repository.CustomerRewardRepository;
import com.coffeeshop.backend.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RewardRepository rewardRepository;
    private final CustomerRewardRepository customerRewardRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, RewardRepository rewardRepository, CustomerRewardRepository customerRewardRepository) {
        this.customerRepository = customerRepository;
        this.rewardRepository = rewardRepository;
        this.customerRewardRepository = customerRewardRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }


    public CustomerReward assignReward(CustomerRewardDto customerRewardDto){
        Optional<Customer> customerOpt = customerRepository.findById(customerRewardDto.customerId());
        if(customerOpt.isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        Optional<Reward> reward = rewardRepository.findById(customerRewardDto.rewardId());
        if(reward.isEmpty()){
            throw new RuntimeException("Reward not found");
        }
        CustomerReward customerReward = new CustomerReward();
        customerReward.setCustomer(customerOpt.get());
        customerReward.setReward(reward.get());
        return customerRewardRepository.save(customerReward);
    }


    public List<Map<String, Object>> findLoyalCustomers(){
        List<Object[]> results = customerRepository.findLoyalCustomers();
        return results.stream().map(result -> {
            Map<String, Object> map = new HashMap<>();
            map.put("customerId", result[0]);
            map.put("name", result[1]);
            map.put("profileImage", result[2]);
            map.put("productsCount", result[3]);
            return map;
        }).collect(Collectors.toList());
    }
}
