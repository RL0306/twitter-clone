package com.example.twitterclone.repository;

import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByUsername(String username);
}
