package com.example.twitterclone.repository;

import com.example.twitterclone.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface TweetRepository extends JpaRepository<TweetEntity,Long> {
    @Query(value = "SELECT * FROM tweet_entity WHERE tweet_entity.user_entity_id IN :userIds ORDER BY tweet_entity.sent_at DESC", nativeQuery = true)
    List<TweetEntity> getTweetList(@Param("userIds") List<Long>userIds);
}
