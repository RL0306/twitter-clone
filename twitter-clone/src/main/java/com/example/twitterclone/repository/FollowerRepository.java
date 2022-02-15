package com.example.twitterclone.repository;

import com.example.twitterclone.entity.FollowerEntity;
import com.example.twitterclone.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface FollowerRepository extends JpaRepository<FollowerEntity, Long> {
    @Query(value = "SELECT follower_entity.following_id FROM follower_entity WHERE follower_entity.follower_id = :userId ", nativeQuery = true)
    List<Long> getFollowerList(@Param("userId") Long userId);
}
