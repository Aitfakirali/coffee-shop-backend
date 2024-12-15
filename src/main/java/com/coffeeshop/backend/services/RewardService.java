package com.coffeeshop.backend.services;

import com.coffeeshop.backend.dto.RewardDto;
import com.coffeeshop.backend.model.Category;
import com.coffeeshop.backend.model.Reward;
import com.coffeeshop.backend.repository.CategoryRepository;
import com.coffeeshop.backend.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RewardService {
    private final RewardRepository rewardRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public RewardService(RewardRepository rewardRepository, CategoryRepository categoryRepository) {
        this.rewardRepository = rewardRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Reward saveReward(RewardDto reward) {
        Optional<Category> category = categoryRepository.findById(reward.categoryId());
        Reward rewardToSave = new Reward();
        rewardToSave.setCategory(category.get());
        rewardToSave.setDescription(reward.description());
        rewardToSave.setTitle(reward.title());
        rewardToSave.setImageUrl(reward.imageUrl());
        rewardToSave.setPoints(reward.points());
        return rewardRepository.save(rewardToSave);
    }


    public void deleteReward(Long id) {
        rewardRepository.deleteById(id);
    }


    public Reward updateReward(RewardDto rewardDto, Long id) {
        Optional<Reward> reward = rewardRepository.findById(id);

        if(reward.isEmpty()){
            throw new RuntimeException("Reward not found");
        }

        Reward rewardToModify = reward.get();
        rewardToModify.setDescription(rewardDto.description());
        rewardToModify.setTitle(rewardDto.title());
        rewardToModify.setImageUrl(rewardDto.imageUrl());
        rewardToModify.setPoints(rewardDto.points());
        return rewardRepository.save(rewardToModify);
    }

    public List<Map<String, Object>> findPopularRewards() {
        List<Object[]> results = rewardRepository.findTop5PopularRewards();
        return results.stream().map(result -> {
            Map<String, Object> map = new HashMap<>();
            map.put("rewardId", result[0]);
            map.put("title", result[1]);
            map.put("imageUrl", result[2]);
            map.put("count", result[3]);
            return map;
        }).collect(Collectors.toList());
    }


    public List<Map<String, Object>> getTotalPointForCurrentWeek() {
        List<Object[]> results = rewardRepository.getTotalPointsForCurrentWeek();
        return results.stream().map(result -> {
            Map<String, Object> map = new HashMap<>();
            map.put("points", result[0]);
            return map;
        }).collect(Collectors.toList());
    }
}
