package com.example.twitterclone.service;

import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.entity.FollowerEntity;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.repository.FollowerRepository;
import com.example.twitterclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    public UserDTO createFollower(Long id){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity currentUser = userRepository.findByUsername(userName).orElseThrow(IllegalAccessError::new);

        UserEntity userEntityToFollow = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        FollowerEntity followerEntity = new FollowerEntity(currentUser, userEntityToFollow);

        followerRepository.save(followerEntity);

        return mapper.convertValue(currentUser, UserDTO.class);

    }
}
