package com.coffeeshop.backend.controller;


import com.coffeeshop.backend.dto.RewardDto;
import com.coffeeshop.backend.model.Reward;
import com.coffeeshop.backend.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/rewards")
public class RewardController {

    private final RewardService rewardService;

    @Autowired
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping()
    public List<Reward> getRewardService() {
        return rewardService.getAllRewards();
    }

    @PostMapping
    public Reward addReward(@RequestBody RewardDto reward) {
        return rewardService.saveReward(reward);
    }

    @PutMapping("{id}")
    public Reward updateReward(@RequestBody RewardDto reward, @PathVariable Long id) {
        return rewardService.updateReward(reward, id);
    }

    @DeleteMapping("{rewardId}")
    public void deleteReward(@PathVariable Long rewardId) {
        rewardService.deleteReward(rewardId);
    }


    @GetMapping("popular")
    public List<Map<String, Object>> popularRewards() {
        return rewardService.findPopularRewards();
    }


   @GetMapping("rewarded_points")
   public  List<Map<String, Object>> getPointsForCurrentWeek(){
        return rewardService.getTotalPointForCurrentWeek();
   }
}
