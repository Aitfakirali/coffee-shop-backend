package com.coffeeshop.backend.repository;

import com.coffeeshop.backend.model.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    @Query(value = """
        SELECT p.reward_id AS rewardId, p.title AS title, p.image_url AS imageUrl, COUNT(cp.customer_id) AS assignedRewards
        FROM reward p 
                JOIN awarded_rewards cp ON p.reward_id = cp.reward_id
        GROUP BY p.reward_id, p.title, p.image_url
        ORDER BY assignedRewards DESC
        LIMIT 5
    """, nativeQuery = true)
    List<Object[]> findTop5PopularRewards();


    @Query(value = """
        SELECT
            SUM(r.points) AS totalPoints
        FROM
            awarded_rewards cr
                JOIN
            reward r
            ON
                cr.reward_id = r.reward_id
        WHERE
            cr.created_at >= DATE_TRUNC('week', CURRENT_DATE)
          AND
            cr.created_at < DATE_TRUNC('week', CURRENT_DATE) + INTERVAL '1 week'
        """, nativeQuery = true)
    public List<Object[]> getTotalPointsForCurrentWeek();
}
