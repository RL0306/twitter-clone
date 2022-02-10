package com.example.twitterclone.repository;


import com.example.twitterclone.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TweetRepository extends JpaRepository<TweetEntity, Long> {
    List<TweetEntity> getAllByParentTweet(Long id);
}
