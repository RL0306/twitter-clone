package com.example.twitterclone.repository;

import com.example.twitterclone.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<TweetEntity,Long> {
}
