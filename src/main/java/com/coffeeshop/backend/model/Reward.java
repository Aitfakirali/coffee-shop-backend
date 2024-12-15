package com.coffeeshop.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table
@Data
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;
    private String title;
    private String description;
    private String imageUrl;
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "reward", cascade = CascadeType.ALL)
    private Set<CustomerReward> customerRewards = new HashSet<>();

    public Reward() {
    }

    public Reward(String title, String description, String imageUrl, Integer points, Category category, Set<CustomerReward> customerRewards) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.points = points;
        this.category = category;
        this.customerRewards = customerRewards;
    }

    public void setCustomerRewards(Set<CustomerReward> customerRewards) {
        this.customerRewards = customerRewards;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public Set<CustomerReward> getCustomerRewards() {
        return customerRewards;
    }

    public Integer getPoints() {
        return points;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Category getCategory() {
        return category;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
