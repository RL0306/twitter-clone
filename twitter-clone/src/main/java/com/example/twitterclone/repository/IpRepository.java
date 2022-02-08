package com.example.twitterclone.repository;

import com.example.twitterclone.entity.IpEntity;
import com.example.twitterclone.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IpRepository extends JpaRepository<IpEntity, Long> {
   List<IpEntity> getIpEntityByUserEntity(UserEntity userEntity);
}
