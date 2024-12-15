package com.coffeeshop.backend.dto;

public record RewardDto(
        String title,
        String description,
        String imageUrl,
        Integer points,
        Long categoryId
) {
}
