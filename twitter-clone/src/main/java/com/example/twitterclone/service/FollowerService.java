package com.example.twitterclone.service;

import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.entity.FollowerEntity;
import com.example.twitterclone.entity.UserEntity;
import com.example.twitterclone.populator.UserPopulator;
import com.example.twitterclone.repository.FollowerRepository;
import com.example.twitterclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class FollowerService {
    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;
    private final UserPopulator userPopulator;

    public UserDTO createFollower(Long id){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity currentUser = userRepository.findByUsername(userName).orElseThrow(IllegalAccessError::new);

        UserEntity userEntityToFollow = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        FollowerEntity followerEntity = new FollowerEntity(userEntityToFollow, currentUser);

        followerRepository.save(followerEntity);

        return userPopulator.populate(currentUser);

    }

    /**
     * This gets all the ids for the current user on who they are following
     * We need this when we want to get current users following tweets
     *
     */
    public List<Long> getListOfFollowingId(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity currentUser = userRepository.findByUsername(username).orElseThrow(IllegalAccessError::new);
//        log.info("list {}",followerRepository.getFollowerList(currentUser.getId()));

        return followerRepository.getFollowerList(currentUser.getId());

    }
}
