package com.coffeeshop.backend.repository;


import com.coffeeshop.backend.model.CustomerReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRewardRepository extends JpaRepository<CustomerReward, Long> {
}
