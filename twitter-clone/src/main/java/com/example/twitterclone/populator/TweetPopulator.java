package com.example.twitterclone.populator;

import com.example.twitterclone.dto.TweetDTO;
import com.example.twitterclone.entity.TweetEntity;
import com.example.twitterclone.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TweetPopulator {

    public TweetDTO createTweetToDTO(TweetEntity tweetEntity){
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setDescription(tweetEntity.getDescription());
        tweetDTO.setRetweets(tweetEntity.getRetweets());
        tweetDTO.setFavourites(tweetEntity.getFavourites());
        tweetDTO.setSentAt(tweetEntity.getSentAt());

        return tweetDTO;
    }

    public List<TweetDTO> createTweetListDTO(UserEntity userEntity){
        return userEntity.getTweets()
                .stream()
                .map(e -> new TweetDTO(e.getId(), e.getDescription(), e.getRetweets(), e.getFavourites(), e.getSentAt()))
                .collect(Collectors.toList());
    }

    public List<TweetDTO> createFollowerTweetListDTO(List<TweetEntity> tweetEntities){
        return tweetEntities.stream()
        .map(e -> new TweetDTO(e.getId(),e.getDescription(), e.getRetweets(), e.getFavourites(), e.getUserEntity().getUsername(), e.getSentAt()))
        .collect(Collectors.toList());

    }
}
