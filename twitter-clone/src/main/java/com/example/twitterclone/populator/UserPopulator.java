package com.example.twitterclone.populator;

import com.example.twitterclone.dto.FollowerDTO;
import com.example.twitterclone.dto.IpDTO;
import com.example.twitterclone.dto.TweetDTO;
import com.example.twitterclone.dto.UserDTO;
import com.example.twitterclone.entity.FollowerEntity;
import com.example.twitterclone.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserPopulator {

    private final TweetPopulator tweetPopulator;

    public UserDTO populate(UserEntity userEntity){
        final UserDTO userDTO = new UserDTO();

        userDTO.setUsername(userEntity.getUsername());

        //FUCKING LOSING MY MIND, SHOULD IT NOT BE FOLLOWER DTO
        //UNDER MINDSET EVERY ENTITY SHOULD HAVE IT'S OWN DTO

        List<UserDTO> userFollowerDTO = getFollowerList(userEntity);
        List<UserDTO> userFollowingDTO = getFollowingList(userEntity);
        List<TweetDTO> userTweetDTO = tweetPopulator.createTweetListDTO(userEntity);

        userDTO.setFollowing(userFollowingDTO);
        userDTO.setFollower(userFollowerDTO);
        userDTO.setTweets(userTweetDTO);

        return userDTO;
    }
    public List<UserDTO> getFollowerList(UserEntity userEntity) {
        return userEntity.getFollowing()
                .stream().map(FollowerEntity::getFollower)
                .map(e -> new UserDTO(e.getUsername()))
                .collect(Collectors.toList());
    }

    public List<UserDTO> getFollowingList(UserEntity userEntity){
        return userEntity.getFollower()
                .stream().map(FollowerEntity::getFollowing)
                .map(e -> new UserDTO(e.getUsername()))
                .collect(Collectors.toList());
    }

}
