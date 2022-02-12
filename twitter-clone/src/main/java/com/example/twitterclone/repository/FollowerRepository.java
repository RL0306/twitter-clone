package com.example.twitterclone.repository;

import com.example.twitterclone.entity.FollowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<FollowerEntity, Long> {
}
